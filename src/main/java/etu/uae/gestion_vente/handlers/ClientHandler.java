package etu.uae.gestion_vente.handlers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import etu.uae.gestion_vente.dtos.ClientDTO;
import etu.uae.gestion_vente.entities.Commande;
import etu.uae.gestion_vente.repositories.CommandeRepository;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ClientHandler  extends ActionSupport implements SessionAware, ModelDriven, ServletContextAware {

    private ClientDTO clientDto = new ClientDTO();
    private List<ClientDTO> clients;
    private Map<String, Object> session;
    private ServletContext ctx;
    private FileInputStream fis;
    private String id;

    @Override
    public Object getModel() {
        return clientDto;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.ctx = servletContext;
    }

    public ServletContext getCtx() {
        return ctx;
    }

    public void setCtx(ServletContext ctx) {
        this.ctx = ctx;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public String listClients() {
        if (session.get("user") == null) {
            return "login";
        }

        SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        CommandeRepository cr = new CommandeRepository(sf);

        clients = cr.findAll().stream()
                .map(Commande::getClient).distinct()
                .map(ClientDTO::new)
                .collect(java.util.stream.Collectors.toList());

        return SUCCESS;
    }

    public String generateClientPdf() throws DocumentException, IOException {
        if (session.get("user") == null) {
            return "login";
        }

        SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        CommandeRepository cr = new CommandeRepository(sf);

        clientDto.setClient(id);

        generatePdf(cr.findByClient(clientDto.getClient()));

        fis = new FileInputStream("facture-" + clientDto.getClient() + ".pdf");

        return SUCCESS;
    }

    private void generatePdf(List<Commande> cmds) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("facture-" + clientDto.getClient() + ".pdf")));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Total du client " + clientDto.getClient(), font);
        Paragraph p = new Paragraph();
        p.add(chunk);
        p.add(Chunk.NEWLINE);
        p.add(Chunk.NEWLINE);
        p.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);

        PdfPCell cell11 = new PdfPCell(new Paragraph("Article"));
        PdfPCell cell12 = new PdfPCell(new Paragraph("Date"));
        PdfPCell cell13 = new PdfPCell(new Paragraph("Quantite"));
        PdfPCell cell14 = new PdfPCell(new Paragraph("Prix"));

        table.addCell(cell11);
        table.addCell(cell12);
        table.addCell(cell13);
        table.addCell(cell14);

        cmds.forEach(cmd -> {
            PdfPCell cell21 = new PdfPCell(new Paragraph(cmd.getArticle().getNom()));
            PdfPCell cell22 = new PdfPCell(new Paragraph(cmd.getDate().getDay() + "/" + cmd.getDate().getMonth() + "/" + cmd.getDate().getYear()));
            PdfPCell cell23 = new PdfPCell(new Paragraph(cmd.getQte().toString()));
            PdfPCell cell24 = new PdfPCell(new Paragraph(cmd.getArticle().getPrix() * cmd.getQte() + "DH"));

            table.addCell(cell21);
            table.addCell(cell22);
            table.addCell(cell23);
            table.addCell(cell24);
        });

        Paragraph total = new Paragraph();
        total.add(Chunk.NEWLINE);
        total.add(Chunk.NEWLINE);
        total.add(new Chunk("Total: " + cmds.stream().map(c -> c.getArticle().getPrix() * c.getQte()).reduce(0, Integer::sum) + "DH", font));

        document.add(p);
        document.add(table);
        document.add(total);
        document.close();
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }


    public void setClientDto(ClientDTO clientDto) {
        this.clientDto = clientDto;
    }

    public List<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(List<ClientDTO> clients) {
        this.clients = clients;
    }

    public void setId(String id) {
        this.id = id;
    }
}
