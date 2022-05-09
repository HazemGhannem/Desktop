package Services;

import entities.Commande;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author EXTRA
 */
public class CommandeService implements Iservice<Commande> {

    Connection connection;
    private static CommandeService instance;

    public CommandeService() {
        connection = DataSource.getInstance().getCnx();
        
    }

     public static CommandeService getInstance(){
        if(instance==null)
            instance=new CommandeService();
        return instance;
    }
    @Override
    public void ajouter(Commande t) {
        try {
            /*  String req = "insert into personne(nom,prenom,age) "
                + "values('"+t.getNom()+"','"+t.getPrenom()+"',"+t.getAge()+")";
        Statement st = connection.createStatement();
       st.executeUpdate(req);*/
            String req1 = "insert into Commande (num,date_commande,etat) values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, t.getNum());
            ps.setString(2, t.getDate_commande());
            ps.setString(3, t.getEtat());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String requete = "DELETE FROM commande WHERE id=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("commande supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Commande t) {
        try {
            String requete = "UPDATE commande SET num=?,date_commande=?,etat=? WHERE id=?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(4, t.getId());
            pst.setInt(1, t.getNum());
            pst.setString(2, t.getDate_commande());
            pst.setString(3, t.getEtat());
            pst.executeUpdate();
            System.out.println("commande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List <Commande> recuperer() {
        List<Commande> list = new ArrayList<>();
        try {
            String req = "select * from commande";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commande c = new Commande();
                c.setId(rs.getInt("id"));
                c.setNum(rs.getInt("num"));
                c.setEtat(rs.getString("etat"));
                c.setDate_commande(rs.getString("date_commande"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
   
    public int recupererMax() {
        int m = 0;
        try {
         
			String req = "SELECT MAX(num) FROM commande";
                        Statement st = connection.createStatement();
			 ResultSet rs = st.executeQuery(req);
                         while(rs.next()){
			    m= rs.getInt("MAX(num)");
			  }
			// close ResultSet rs
			rs.close();
                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    } 
    
    

    @Override
    public void supprimer(Commande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
