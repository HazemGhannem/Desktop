/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Pack;
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
public class PackService implements Iservice<Pack>{

    Connection connection;
    public PackService() {
        connection = MyDB.getInstance().getConnection();
    }

    public void ajouter(Pack p) {
        try {
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into pack(type,prix,description) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, p.getType());
            ps.setDouble(2, p.getPrix());
            ps.setString(3, p.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Pack p) {
       try {
             String req1 = "UPDATE pack SET type= ? , prix= ?,description= ?  where id= ?;";
            PreparedStatement ps = connection.prepareStatement(req1);
             ps.setString(1, p.getType());
            ps.setDouble(2, p.getPrix());
            ps.setString(3, p.getDescription());
            ps.setInt(4, p.getId());
            
            if (ps.executeUpdate() != 0) {
                System.out.println(" pack updated");
                 } else {
                System.out.println("non merci");
            }
            
                 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(int id) {
        try {
        String req1 ="Delete from pack where id=? ;";
        PreparedStatement ps = connection.prepareStatement(req1);
    
        ps.setInt(1, id);
        if (ps.executeUpdate() != 0) {
            System.out.println("pack Deleted");

            
        }else
        System.out.println("id pack not found!!!");
        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

            
        }
    }
 public void supprimerTout() {
        String query = "DELETE FROM Pack";
        try{
            Statement ste = connection.createStatement();
            ste.executeUpdate(query);
            System.out.println("Toute les Packs sont supprimer");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void reset(){
        String query = "ALTER TABLE Pack AUTO_INCREMENT =1";
        try{
            Statement ste = connection.createStatement();
            ste.executeUpdate(query);
            System.out.println("Ids reset");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Pack> recuperer() {
        ObservableList<Pack> list = FXCollections.observableArrayList() ;
        try {
            String req = "select * from pack";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Pack p = new Pack();
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
     public List<Pack> rr() {
        ObservableList<Pack> list = FXCollections.observableArrayList() ;
        try {
            String req = "select * from pack WHERE id=1";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            
                Pack p = new Pack();
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));  
                list.add(p);
          
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
}
