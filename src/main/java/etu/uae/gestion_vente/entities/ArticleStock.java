package etu.uae.gestion_vente.entities;

public class ArticleStock {
    private int codeArt;
    private String nom;
    private String desc;
    private int prix;
    private int qte;

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(int codeArt) {
        this.codeArt = codeArt;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
