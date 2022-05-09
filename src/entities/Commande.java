/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.logging.Logger;

/**
 *
 * @author aladinrezgani
 */
public class Commande {

    private int id;
    private int num;
    private String date_commande;
    private String etat;
    private boolean est_payee;

    public Commande() {
    }

    public Commande(int num) {
        this.num = num;
    }

    public Commande(int num, String date_commande, String etat) {
        this.num = num;
        this.date_commande = date_commande;
        this.etat = etat;
    }

    public Commande(int num, String date_commande, String etat, boolean est_payee) {
        this.num = num;
        this.date_commande = date_commande;
        this.etat = etat;
        this.est_payee = est_payee;
    }

    public Commande(int id, int num, String date_commande, String etat, boolean est_payee) {
        this.id = id;
        this.num = num;
        this.date_commande = date_commande;
        this.etat = etat;
        this.est_payee = est_payee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public boolean isEst_payee() {
        return est_payee;
    }

    public void setEst_payee(boolean est_payee) {
        this.est_payee = est_payee;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", num=" + num + ", date_commande=" + date_commande + ", etat=" + etat + ", est_payee=" + est_payee + '}';
    }

}
