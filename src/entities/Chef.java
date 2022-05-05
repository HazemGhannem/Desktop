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
public class Chef {
    private int id;
    private String nom,specialitee;
    
    public Chef(int id,String nom , String specialitee) {
        this.id = id;
        this.nom = nom;
        this.specialitee = specialitee;
    }
    public Chef(String nom , String specialitee) {
        
        this.nom = nom;
        this.specialitee = specialitee;
    }
    
    public Chef() {
        
     
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
   
   

    public String getSpecialitee() {
        return specialitee;
    }

    public void setSpecialitee(String desc) {
        this.specialitee = desc;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", nom=" + nom + ", specialitee=" + specialitee + '}';
    }

  
   
    
}
