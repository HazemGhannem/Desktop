/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Promotion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Skander
 */
public class PromotionService implements Iservice<Promotion>{

    Connection connection;
     
    private PreparedStatement ps;
    private Statement ste;
    private ResultSet rs;
    public PromotionService() {
        connection = DataSource.getInstance().getCnx();
    }

    public void ajouter(Promotion p) {
        try {
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into promotion(nom,pourcentage,description,prixoriginal,prixpromotion,image,datedebut,datefin) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, p.getNom());
            ps.setInt(2, p.getPourcentage());
            ps.setString(3, p.getDescription());
             ps.setDouble(4, p.getPrixoriginal());
              ps.setDouble(5,( p.getPrixoriginal() -(p.getPrixoriginal() * p.getPourcentage()/100)));
               ps.setString(6, p.getImage());
               ps.setDate(7, p.getDatedebut());
            ps.setDate(8, p.getDatefin());
              
               
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Promotion p) {
         try {
             String req1 = "UPDATE promotion SET nom= ? , pourcentage= ?,description= ?,prixoriginal= ?,prixpromotion= ?,image= ?,datedebut= ?,datefin= ?  where id= ?;";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, p.getNom());
            ps.setInt(2, p.getPourcentage());
            ps.setString(3, p.getDescription());
             ps.setDouble(4, p.getPrixoriginal());
              ps.setDouble(5, (p.getPrixoriginal() -(p.getPrixoriginal() * p.getPourcentage()/100)));
               ps.setString(6, p.getImage());
               ps.setDate(7, p.getDatedebut());
            ps.setDate(8, p.getDatefin());
            ps.setInt(9, p.getId());

            if (ps.executeUpdate() != 0) {
                System.out.println(" promotion updated");
                 } else {
                System.out.println("non merci");
            }
            
                 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(int id ) {
        
    try {
        String req1 ="Delete from promotion where id=? ;";
        PreparedStatement ps = connection.prepareStatement(req1);
    
        ps.setInt(1, id);
        
        if (ps.executeUpdate() != 0) {
            System.out.println("promotion Deleted");

            
        }else
        System.out.println("id promotion not found!!!");
        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

            
        }
        
    }
 public void supprimerTout() {
        String query = "DELETE FROM Promotion";
        try{
            Statement ste = connection.createStatement();
            ste.executeUpdate(query);
            System.out.println("Toute les Promotions sont supprimer");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void reset(){
        String query = "ALTER TABLE Promotion AUTO_INCREMENT =1";
        try{
            Statement ste = connection.createStatement();
            ste.executeUpdate(query);
            System.out.println("Ids reset");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Promotion> recuperer() {
       ObservableList<Promotion> list = FXCollections.observableArrayList() ;
        try {
            String req = "select * from promotion";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Promotion p = new Promotion();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPourcentage(rs.getInt("pourcentage"));
                p.setDescription(rs.getString("description"));
                p.setPrixoriginal(rs.getDouble("prixoriginal"));
                p.setPrixpromotion(rs.getDouble("prixpromotion"));
                p.setImage(rs.getString("image"));
                p.setDatedebut(rs.getDate("datedebut"));
                p.setDatefin(rs.getDate("datefin")); 
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
     public List<Promotion> recherche(String nom) {
        List<Promotion> p = new ArrayList<>();
        
        try {
            String req = "select * from promotion WHERE `nom`='" + nom + "'";
            Statement st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs =st.executeQuery(req);
            int nbrRow = rs.getRow();
            if (nbrRow != 0) {

                try {
                    ps = connection.prepareStatement(req);
                     rs = ps.executeQuery(req);

                    while (rs.next()) {
                        Promotion c = new Promotion();
                         c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPourcentage(rs.getInt("pourcentage"));
                c.setDescription(rs.getString("description"));
                c.setPrixoriginal(rs.getDouble("prixoriginal"));
                c.setPrixpromotion(rs.getDouble("prixpromotion"));
                c.setImage(rs.getString("image"));
                c.setDatedebut(rs.getDate("datedebut"));
                c.setDatefin(rs.getDate("datefin")); 
                        p.add(c);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                System.out.println("Promotion trouvé");

            } else {
                System.out.println("Promotion non trouvé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
     public List<Promotion> TreePromotion() {
        List<Promotion> list =new ArrayList<>();
        String req = "SELECT * FROM Promotion ORDER BY `nom` ASC";
        try {
            ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Promotion p = new Promotion();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPourcentage(rs.getInt("pourcentage"));
                p.setDescription(rs.getString("description"));
                p.setPrixoriginal(rs.getDouble("prixoriginal"));
                p.setPrixpromotion(rs.getDouble("prixpromotion"));
                p.setImage(rs.getString("image"));
                p.setDatedebut(rs.getDate("datedebut"));
                p.setDatefin(rs.getDate("datefin")); 
                list.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return list;
    }
     public List<Promotion> TreePrix() {
        List<Promotion> list =new ArrayList<>();
        String req = "SELECT * FROM Promotion ORDER BY `prixpromotion` ASC";
        try {
            ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Promotion p = new Promotion();
                 p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPourcentage(rs.getInt("pourcentage"));
                p.setDescription(rs.getString("description"));
                p.setPrixoriginal(rs.getDouble("prixoriginal"));
                p.setPrixpromotion(rs.getDouble("prixpromotion"));
                p.setImage(rs.getString("image"));
                p.setDatedebut(rs.getDate("datedebut"));
                p.setDatefin(rs.getDate("datefin")); 
                list.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return list;
    }
     public List<Promotion> Treepc() {
        List<Promotion> list =new ArrayList<>();
        String req = "SELECT * FROM Promotion ORDER BY `pourcentage` ASC";
        try {
            ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Promotion p = new Promotion();
                 p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPourcentage(rs.getInt("pourcentage"));
                p.setDescription(rs.getString("description"));
                p.setPrixoriginal(rs.getDouble("prixoriginal"));
                p.setPrixpromotion(rs.getDouble("prixpromotion"));
                p.setImage(rs.getString("image"));
                p.setDatedebut(rs.getDate("datedebut"));
                p.setDatefin(rs.getDate("datefin")); 
                list.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return list;
    }

    @Override
    public List<Promotion> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Promotion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
