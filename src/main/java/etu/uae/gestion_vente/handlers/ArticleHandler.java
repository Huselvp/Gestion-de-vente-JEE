package etu.uae.gestion_vente.handlers;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import etu.uae.gestion_vente.dtos.ArticleDTO;
import etu.uae.gestion_vente.entities.Article;
import etu.uae.gestion_vente.entities.ArticleStock;
import etu.uae.gestion_vente.repositories.ArticleRepository;
import etu.uae.gestion_vente.repositories.ArticleStockRepository;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleHandler extends ActionSupport implements SessionAware, ModelDriven, ServletContextAware {

    private Article article = new Article();

    private List<ArticleDTO> articleDAOs = new ArrayList<>();
    private Map<String, Object> sessionMap;

    public List<ArticleDTO> getArticleDAOs() {
        return articleDAOs;
    }

    public void setArticleDAOs(List<ArticleDTO> articleDAOs) {
        this.articleDAOs = articleDAOs;
    }

    private ServletContext ctx;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public void setServletContext(ServletContext sc) {
        this.ctx = sc;
    }

    public Article getModel() {
        return article;
    }

    public String listArticles() {
        if (sessionMap.get("user") == null) {
            return "unauthorized";
        }

        SessionFactory session = (SessionFactory) ctx.getAttribute("SessionFactory");

        Configuration stockCfg = new Configuration();
        stockCfg.configure("hibernate_g_stock.cfg.xml");
        SessionFactory stockSession = stockCfg.buildSessionFactory();

        ArticleRepository articleRepository = new ArticleRepository(session);

        ArticleStockRepository articleStockRepository = new ArticleStockRepository(stockSession);
        List<ArticleStock> articleStocks = articleStockRepository.findAll();

        // Iterate over the articleStocks and create a new ArticleDTO
        // to be displayed on articles.jsp
        articleStocks.forEach(as -> {
                    if (!articleRepository.containsNom(as.getNom())) {
                        Article tmpArticle = new Article();
                        tmpArticle.setNom(as.getNom());
                        tmpArticle.setPrix(as.getPrix());
                        tmpArticle.setDescription(as.getDesc());
                        articleRepository.save(tmpArticle);
                    }

                    ArticleDTO articleDAO = new ArticleDTO(
                            as.getCodeArt(),
                            as.getNom(),
                            as.getPrix(),
                            as.getQte());
                    articleDAOs.add(articleDAO);
            }
        );

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = map;
    }
}