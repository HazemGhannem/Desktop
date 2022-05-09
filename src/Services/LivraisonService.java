package Services;

import entities.Commande;
import entities.Livraison;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aladinrezgani
 */
public class LivraisonService implements Iservice<Livraison> {

    Connection connection;

    public LivraisonService() {
        connection = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Livraison t) {
        try {
            /*  String req = "insert into personne(nom,prenom,age) "
                + "values('"+t.getNom()+"','"+t.getPrenom()+"',"+t.getAge()+")";
        Statement st = connection.createStatement();
       st.executeUpdate(req);*/
            String req1 = "insert into Livraison (longitude,latitude) values(?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, t.getLongitude());
            ps.setString(2, t.getLatitude());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     *
     * @param t
     */
    @Override
    public void modifier(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livraison> recuperer() {
        List<Livraison> list = new ArrayList<>();
        try {
            String req = "select * from livraison";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Livraison l = new Livraison();
                l.setLongitude(rs.getString("longitude"));
                l.setLatitude(rs.getString("latitude"));

                list.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Livraison> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
