package etu.uae.gestion_vente.dtos;

public class ClientDTO {
    private String client;

    public ClientDTO(String client) {
        this.client = client;
    }

    public ClientDTO() {}

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
