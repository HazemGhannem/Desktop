/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import entities.Plat;
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
public class CategorieService implements Iservice<Categorie>{

    Connection connection;
    public CategorieService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Categorie t) {
        try {
            connection = MyDB.getInstance().getConnection();
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into Categorie (nom,description,image) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getDesc());
            ps.setString(3, t.getImg());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categorie t) {
         try {
            connection = MyDB.getInstance().getConnection();
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "UPDATE categorie SET nom=?, description=? , image=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getDesc());
            ps.setString(3, t.getImg());
            ps.setInt(4, t.getId());
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
            
            
            String req1 = "DELETE FROM categorie WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> recuperer() {
        List<Categorie> list =new ArrayList<>();
        try {
            String req = "select * from categorie";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Categorie p = new Categorie();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDesc(rs.getString("description"));
                p.setImg(rs.getString("image"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public  Categorie GetById(int id) {
        return recuperer().stream().filter(e -> e.getId() == id).findFirst().get();

    }
    
}
