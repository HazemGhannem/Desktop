/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Plat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;

/**
 *
 * @author Pc
 */
public class PlatService implements Iservice<Plat>{

    Connection connection;
    public PlatService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Plat t) {
        try {
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into Plat (nom,description,prix,img) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getDesc());
            ps.setDouble(3, t.getPrix());
            ps.setString(4, t.getImg());
    
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Plat t) {
        try {
            connection = MyDB.getInstance().getConnection();
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "UPDATE plat SET nom=?, description=? , prix=?, img=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getDesc());
            ps.setDouble(3, t.getPrix());
            ps.setString(4, t.getImg());
            ps.setInt(5, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
        try {
            connection = MyDB.getInstance().getConnection();
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "DELETE FROM plat WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Plat Deleted");
        }
    }

    @Override
    public List<Plat> recuperer() {
        List<Plat> list =new ArrayList<>();
        try {
            String req = "select * from Plat";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Plat p = new Plat();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDesc(rs.getString("description"));
                p.setImg(rs.getString("img"));  
                p.setPrix(rs.getDouble("prix"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public  Plat GetById(int id) {
        return recuperer().stream().filter(e -> e.getId() == id).findFirst().get();

    }

    

    
    
}
