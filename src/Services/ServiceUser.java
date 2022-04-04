/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.User;
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
 * @author Hazem
 */
public class ServiceUser implements Iservice<User> {

    
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(User t) {
        try {
            String req = "INSERT INTO `user` (`username`, `email`,`password`,`telephone`,`image`) VALUES ('" +t.getUsername() + "', '" 
                    + t.getEmail()+"', '" + t.getPassword()+"', '"+ t.getTelephone()+"', '" +t.getImage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public User getById(int id) {
         String req="select * from user where id ="+id;
        User p=new User();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                 p.setUsername(rs.getString("username"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRoles(rs.getString("roles"));
                p.setTelephone(rs.getString("telephone"));
                p.setImage(rs.getString("image"));
                p.setIs_verified(rs.getBoolean("is_verified"));
                p.setIs_expired(rs.getBoolean("is_expired"));
               
                //}  
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    return p;
    }

    @Override
     public List<User> getAll() {
     List<User> list = new ArrayList<>();
     try {
         String req = "Select * from user";
         Statement st = cnx.createStatement();
         ResultSet rs = st.executeQuery(req);
         while(rs.next()){
             User u = new User (rs.getInt("id"),rs.getString("username"),rs.getString("telephone"),
                     rs.getString("email"),rs.getString("password"),rs.getString("image"),
                     rs.getString("roles"),rs.getBoolean("is_verified"),rs.getBoolean("is_expired"));
             list.add(u);
         }
     } catch (SQLException ex) {
         System.err.println(ex.getMessage());
     }
     return list;
 }

    @Override
    public void modifier(User t) {
       PreparedStatement stmt;
	try {

	    String sql = "UPDATE user SET username=? ,telephone=?,email=?,password=?,image=? WHERE id=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, t.getUsername());
	    stmt.setString(2, t.getTelephone());
	    stmt.setString(3, t.getEmail());
            stmt.setString(4, t.getPassword());
            stmt.setString(5, t.getImage());
            stmt.setInt(6, t.getId());
	    stmt.executeUpdate();
              
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    @Override
    public void supprimer(User u) {
       
        String req="delete from user where id="+u.getId();
        User user =getById(u.getId());
       
          if(user!=null)
              try {
             Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
                  System.out.println(ex);
        }else System.out.println("n'existe pas");
    }
    
}
