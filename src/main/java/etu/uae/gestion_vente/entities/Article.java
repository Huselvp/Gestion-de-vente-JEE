package etu.uae.gestion_vente.entities;

public class Article {
    private Integer codeArt;
    private String nom;
    private String description;
    private Integer prix;

    public Integer getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(Integer id) {
        this.codeArt = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomArt) {
        this.nom = nomArt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descArt) {
        this.description = descArt;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prixArt) {
        this.prix = prixArt;
    }
}