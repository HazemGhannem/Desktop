/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author pc
 */
public class Pack {
    private int id  ;
    private String type   ;
    private double prix;
    private String description   ;

    public Pack(int id, String type, double prix, String description) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.description = description;
    }

    public Pack(String type, double prix, String description) {
        this.type = type;
        this.prix = prix;
        this.description = description;
    }
    
    public Pack(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pack{" + "id=" + id + ", type=" + type +  ", prix=" + prix +", description=" + description + '}';
    }



    
}
