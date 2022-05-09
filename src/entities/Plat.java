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
    private int categorie_id;
    
    public Plat(int id,String nom , String desc,int categorie_id, Double prix, String img) {
        this.id = id;
        this.nom = nom;
        this.desc = desc;
        this.img = img;
        this.prix = prix;
        this.categorie_id = categorie_id;
    }
    public Plat(String nom , String desc,int categorie_id, Double prix, String img) {
        
        this.nom = nom;
        this.desc = desc;
        this.prix = prix;
        this.img = img;
        this.categorie_id = categorie_id;
        
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
    
    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", nom=" + nom + ", img=" + img + ", desc=" + desc + ", categorie=" + categorie_id + ", prix=" + prix +'}';
    }

  
   
    
}
