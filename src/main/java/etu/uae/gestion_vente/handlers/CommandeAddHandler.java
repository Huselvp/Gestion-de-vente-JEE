package etu.uae.gestion_vente.handlers;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import etu.uae.gestion_vente.dtos.CommandeDTO;
import etu.uae.gestion_vente.entities.Article;
import etu.uae.gestion_vente.entities.ArticleStock;
import etu.uae.gestion_vente.entities.Commande;
import etu.uae.gestion_vente.repositories.ArticleRepository;
import etu.uae.gestion_vente.repositories.ArticleStockRepository;
import etu.uae.gestion_vente.repositories.CommandeRepository;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContext;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class CommandeAddHandler extends ActionSupport implements SessionAware, ModelDriven, ServletContextAware {
    private CommandeDTO commandeDTO = new CommandeDTO();
    private ServletContext ctx;

    private Map<String, Object> sessionMap;

    public String execute() {
        return SUCCESS;
    }

    public String addCommande() {
        if (sessionMap.get("user") == null) {
            return "unauthorized";
        }

        SessionFactory session = (SessionFactory) ctx.getAttribute("SessionFactory");
        Configuration stockCfg = new Configuration();
        stockCfg.configure("hibernate_g_stock.cfg.xml");
        SessionFactory stockSession = stockCfg.buildSessionFactory();

        CommandeRepository commandeRepository = new CommandeRepository(session);
        ArticleStockRepository articleStockRepository = new ArticleStockRepository(stockSession);
        ArticleRepository articleRepository = new ArticleRepository(session);

        Optional<ArticleStock> artOpt = articleStockRepository.findById(commandeDTO.getCode());

        if (artOpt.isPresent()) {
            ArticleStock art = artOpt.get();

            art.setQte(art.getQte() - commandeDTO.getQte());
            articleStockRepository.update(art);
            articleRepository.findByName(art.getNom()).ifPresent(a -> {
                Commande commande = new Commande();
                commande.setArticle(a);
                commande.setQte(commandeDTO.getQte());
                commande.setDate(Date.from(Instant.now()));
                commande.setClient(commandeDTO.getClient());
                commandeRepository.save(commande);
            });
        }
        return SUCCESS;
    }

    public CommandeDTO getCommandeDTO() {
        return commandeDTO;
    }

    public void setCommandeDTO(CommandeDTO commandeDTO) {
        this.commandeDTO = commandeDTO;
    }

    @Override
    public CommandeDTO getModel() {
        return commandeDTO;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.ctx = servletContext;
    }

    public void setSession(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }
}
