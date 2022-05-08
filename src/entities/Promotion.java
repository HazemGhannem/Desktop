/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class Promotion {
    private int id;
    private String nom;
    private int pourcentage;
    private String Description;
    private double prixoriginal;
    private double prixpromotion;
    private String image ;
    private Date datedebut ;
    private Date datefin ;

    public Promotion(int id, String nom, int pourcentage, String Description, double prixoriginal, double prixpromotion, Date datedebut, Date datefin, String image) {
        this.id = id;
        this.nom = nom;
        this.pourcentage = pourcentage;
        this.Description = Description;
        this.prixoriginal = prixoriginal;
        this.prixpromotion = prixpromotion;
        
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.image = image;
    }
    public Promotion(int id, String nom, int pourcentage, String Description, double prixoriginal, Date datedebut, Date datefin, String image) {
        this.id = id;
        this.nom = nom;
        this.pourcentage = pourcentage;
        this.Description = Description;
        this.prixoriginal = prixoriginal;
        
        this.image = image;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Promotion(String nom, int pourcentage, String Description, double prixoriginal, double prixpromotion, Date datedebut, Date datefin, String image) {
        this.nom = nom;
        this.pourcentage = pourcentage;
        this.Description = Description;
        this.prixoriginal = prixoriginal;
        this.prixpromotion = prixpromotion;
        this.image = image;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }
     public Promotion(String nom, int pourcentage, String Description, double prixoriginal, Date datedebut, Date datefin, String image) {
        this.nom = nom;
        this.pourcentage = pourcentage;
        this.Description = Description;
        this.prixoriginal = prixoriginal;
        this.prixpromotion = ( this.prixoriginal -(this.prixoriginal * this.pourcentage/100));
        this.image = image;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Promotion() {
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

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getPrixoriginal() {
        return prixoriginal;
    }

    public void setPrixoriginal(double prixoriginal) {
        this.prixoriginal = prixoriginal;
    }

    public double getPrixpromotion() {
        return prixpromotion;
    }

    public void setPrixpromotion(double prixpromotion) {
        this.prixpromotion = prixpromotion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", nom=" + nom + ", pourcentage=" + pourcentage + ", Description=" + Description + ", prixoriginal=" + prixoriginal + ", prixpromotion=" + prixpromotion +  ", datedebut=" + datedebut + ", datefin=" + datefin +", image=" + image + '}';
    }
    
}
