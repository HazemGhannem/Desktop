/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author aladinrezgani
 */
public class Livraison {

    private int id;
    private Commande commande_id;
    private int estLivree;
    private String longitude;
    private String latitude;

    public Livraison() {
    }

    public Livraison(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Livraison(int estLivree, String longitude, String latitude) {
        this.estLivree = estLivree;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Livraison(Commande commande_id, int estLivree, String longitude, String latitude) {
        this.commande_id = commande_id;
        this.estLivree = estLivree;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Livraison(int id, Commande commande_id, int estLivree, String longitude, String latitude) {
        this.id = id;
        this.commande_id = commande_id;
        this.estLivree = estLivree;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(Commande commande_id) {
        this.commande_id = commande_id;
    }

    public int getEstLivree() {
        return estLivree;
    }

    public void setEstLivree(int estLivree) {
        this.estLivree = estLivree;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
