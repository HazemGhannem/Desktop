/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Pc
 */
public class Plat {
    private int id;
    private String nom,img,desc;
    private Double prix;
    
    public Plat(int id,String nom , String desc, String img, Double prix) {
        this.id = id;
        this.nom = nom;
        this.desc = desc;
        this.img = img;
        this.prix = prix;
    }
    public Plat(String nom , String desc, Double prix, String img) {
        
        this.nom = nom;
        this.desc = desc;
        this.prix = prix;
        this.img = img;
    }
    
    public Plat() {
        
     
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
   
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", nom=" + nom + ", img=" + img + ", desc=" + desc + ", prix=" + prix +'}';
    }

  
   
    
}
