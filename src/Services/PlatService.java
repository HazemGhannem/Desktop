/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
import Utils.DataSource;

/**
 *
 * @author Pc
 */
public class PlatService implements Iservice<Plat> {

    Connection connection;
    private PreparedStatement pst;
    private Statement ste;
    private ResultSet rste;

    public PlatService() {
        connection = DataSource.getInstance().getCnx();
    }

    public void ajouter(Plat t) {
        try {
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);

            String req1 = "insert into Plat (categorie_id,nom,description,prix,img) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);

            ps.setInt(1, t.getCategorie_id());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getDesc());
            ps.setDouble(4, t.getPrix());
            ps.setString(5, t.getImg());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Plat t) {
        try {
            connection = DataSource.getInstance().getCnx();//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);

            String req1 = "UPDATE plat SET categorie_id=?, nom=?, description=? , prix=?, img=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, t.getCategorie_id());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getDesc());
            ps.setDouble(4, t.getPrix());
            ps.setString(5, t.getImg());
            ps.setInt(6, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(int id) {
        try {
            connection = DataSource.getInstance().getCnx();//            String req = "insert into personne(nom,prenom,age) "
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

    public List<Plat> recuperer() {
        List<Plat> list = new ArrayList<>();
        try {
            String req = "select * from Plat";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Plat p = new Plat();
                p.setId(rs.getInt("id"));
                p.setCategorie_id(rs.getInt("categorie_id"));
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

    public Plat GetById(int id) {
        return recuperer().stream().filter(e -> e.getId() == id).findFirst().get();

    }

    public List<Plat> rechercheCoach(String nom) {
        List<Plat> p = new ArrayList<>();
        String query = "SELECT * FROM plat WHERE `nom`='" + nom + "'";
        try {
            ste = connection.createStatement();
            rste = ste.executeQuery(query);
            rste.last();
            int nbrRow = rste.getRow();
            if (nbrRow != 0) {

                try {
                    pst = connection.prepareStatement(query);
                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        Plat c = new Plat();
                        c.setId(rs.getInt("id_plat"));
                        c.setNom(rs.getString(2));
                        c.setDesc(rs.getString(3));
                        c.setPrix(rs.getDouble(4));
                        p.add(c);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                System.out.println("Plat trouvé");

            } else {
                System.out.println("Plat non trouvé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public List<Plat> TreePlat() {
        List<Plat> list = new ArrayList<>();
        String req = "SELECT * FROM plat ORDER BY `nom` ASC";
        try {
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
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

    public List<Plat> TreePrix() {
        List<Plat> list = new ArrayList<>();
        String req = "SELECT * FROM plat ORDER BY `prix` ASC";
        try {
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
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

    public List<Plat> Treepd() {
        List<Plat> list = new ArrayList<>();
        String req = "SELECT * FROM plat ORDER BY `categorie_id` ASC";
        try {
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
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

    @Override
    public List<Plat> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Plat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
