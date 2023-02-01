package etu.uae.gestion_vente.dtos;

public class CommandeDTO {
    // Code Article
    private int code;
    private String client;
    private int qte;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
