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
public class Coupon {
    private int id ;
    private String nom ;
    private String code ;
    private double pourcentage ;
    private Date datedebut ;
    private Date datefin ;

    public Coupon(int id, String nom, String code, double pourcentage, Date datedebut, Date datefin) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.pourcentage = pourcentage;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Coupon(String nom, String code, double pourcentage, Date datedebut, Date datefin) {
        this.nom = nom;
        this.code = code;
        this.pourcentage = pourcentage;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }



    public Coupon() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
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
        return "Coupon{" + "id=" + id + ", nom=" + nom + ", code=" + code + ", pourcentage=" + pourcentage + ", datedebut=" + datedebut + ", datefin=" + datefin + '}';
    }

  

}


