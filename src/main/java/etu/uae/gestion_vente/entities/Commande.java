package etu.uae.gestion_vente.entities;

import java.util.Date;

public class Commande {
    private Integer codeCmd;
    private String client;
    private Integer qte;
    private Date date;
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getCodeCmd() {
        return codeCmd;
    }

    public void setCodeCmd(Integer id) {
        this.codeCmd = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}