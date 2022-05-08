/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Coupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Skander
 */
public class CouponService implements Iservice<Coupon>{

    Connection connection;
    public CouponService() {
        connection = MyDB.getInstance().getConnection();
    }

    public void ajouter(Coupon c) {
        try {
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into coupon(nom,code,pourcentage,datedebut,datefin) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getCode());
            ps.setDouble(3, c.getPourcentage());
            ps.setDate(4, c.getDatedebut());
            ps.setDate(5, c.getDatefin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Coupon c) {
       try {
             String req1 = "UPDATE coupon SET nom= ? , code= ?,pourcentage= ?,datedebut= ?,datefin= ?  where id= ?;";
              PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getCode());
            ps.setDouble(3, c.getPourcentage());
            ps.setDate(4, c.getDatedebut());
            ps.setDate(5, c.getDatefin());
            ps.setInt(6, c.getId());
             if (ps.executeUpdate() != 0) {
                System.out.println(" coupon updated");
                 } else {
                System.out.println("non merci");
            }
            
                 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(int id) {
         try {
        String req1 ="Delete from coupon where id=? ;";
        PreparedStatement ps = connection.prepareStatement(req1);
    
        ps.setInt(1, id);
        if (ps.executeUpdate() != 0) {
            System.out.println("coupon Deleted");

            
        }else
        System.out.println("id coupon not found!!!");
        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

            
        }
    }
    public void supprimerTout() {
        String query = "DELETE FROM Coupon";
        try{
            Statement ste = connection.createStatement();
            ste.executeUpdate(query);
            System.out.println("Toute les Coupons sont supprimer");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void reset(){
        String query = "ALTER TABLE Coupon AUTO_INCREMENT =1";
        try{
            Statement ste = connection.createStatement();
            ste.executeUpdate(query);
            System.out.println("Ids reset");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Coupon> recuperer() {
        ObservableList<Coupon> list = FXCollections.observableArrayList() ;
        try {
            String req = "select * from coupon";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Coupon c = new Coupon();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setCode(rs.getString("code"));
                c.setPourcentage(rs.getDouble("pourcentage"));
                c.setDatedebut(rs.getDate("datedebut"));
                c.setDatefin(rs.getDate("datefin"));  
                list.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
}
