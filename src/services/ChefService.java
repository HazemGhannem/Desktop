/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chef;
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

/**
 *
 * @author Pc
 */
public class ChefService implements Iservice<Chef>{

    Connection connection;
    public ChefService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Chef t) {
        try {
            connection = MyDB.getInstance().getConnection();
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into Chef (nom,specialitee) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getSpecialitee());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Chef t ) {
        try {
            connection = MyDB.getInstance().getConnection();
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "UPDATE chef SET nom=?, specialitee=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getSpecialitee());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public void supprimer(int id) {
        try {
            connection = MyDB.getInstance().getConnection();
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "DELETE FROM chef WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Chef> recuperer() {
        List<Chef> list =new ArrayList<>();
        try {
            String req = "select * from Chef";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Chef p = new Chef();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setSpecialitee(rs.getString("specialitee")); 
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
}