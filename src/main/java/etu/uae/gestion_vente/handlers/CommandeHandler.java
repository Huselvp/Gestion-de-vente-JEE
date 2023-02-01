package etu.uae.gestion_vente.handlers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import etu.uae.gestion_vente.dtos.CommandeDTO;
import etu.uae.gestion_vente.entities.Article;
import etu.uae.gestion_vente.entities.Commande;
import etu.uae.gestion_vente.repositories.ArticleRepository;
import etu.uae.gestion_vente.repositories.ArticleStockRepository;
import etu.uae.gestion_vente.repositories.CommandeRepository;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.List;

public class CommandeHandler extends ActionSupport implements SessionAware, ModelDriven, ServletContextAware {

    private Commande commande = new Commande();

    private int id;
    private List<Commande> commandes = new ArrayList<>();

    private FileInputStream fis;

    private ServletContext ctx;
    private Map<String, Object> sessionMap;

    @Override
    public Commande getModel() {
        return commande;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.ctx = servletContext;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String execute() {
        return SUCCESS;
    }

    public String getPdf() throws FileNotFoundException {
        if (sessionMap.get("user") == null) {
            return "unauthorized";
        }

        SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        CommandeRepository cmdRepo = new CommandeRepository(sf);

        Optional<Commande> cmdOpt = cmdRepo.findById(id);

        cmdOpt.ifPresent(cmd -> {
            try {
                generatePdf(cmd);
            } catch (DocumentException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        fis = new FileInputStream("facture" + id + ".pdf");

        return SUCCESS;
    }

    private void generatePdf(Commande cmd) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("facture" + cmd.getCodeCmd() + ".pdf")));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Facture de la commande " + cmd.getCodeCmd(), font);
        Paragraph p = new Paragraph();
        p.add(chunk);
        p.add(Chunk.NEWLINE);
        p.add(new Chunk("Client : " + cmd.getClient()));
        p.add(Chunk.NEWLINE);
        p.add(new Chunk("Date : " + cmd.getDate().toString()));
        p.add(Chunk.NEWLINE);
        p.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(3);

        PdfPCell cell11 = new PdfPCell(new Paragraph("Article"));
        PdfPCell cell13 = new PdfPCell(new Paragraph("Quantite"));
        PdfPCell cell14 = new PdfPCell(new Paragraph("Prix"));

        PdfPCell cell21 = new PdfPCell(new Paragraph(cmd.getArticle().getNom()));
        PdfPCell cell23 = new PdfPCell(new Paragraph(cmd.getQte().toString()));
        PdfPCell cell24 = new PdfPCell(new Paragraph(cmd.getArticle().getPrix() * cmd.getQte() + "DH"));

        table.addCell(cell11);
        table.addCell(cell13);
        table.addCell(cell14);

        table.addCell(cell21);
        table.addCell(cell23);
        table.addCell(cell24);

        document.add(p);
        document.add(table);
        document.close();
    }

    public String listCommandes() {
        if (sessionMap.get("user") == null) {
            return "unauthorized";
        }

        SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
        CommandeRepository commandeRepository = new CommandeRepository(sf);
        commandes = commandeRepository.findAll();
        return SUCCESS;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

    public int getId() {
        return id;
    }
    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

}
