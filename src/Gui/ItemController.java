/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.Stock;
import Models.User;
import Services.ServiceUser;
import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class ItemController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label tfusername;
    @FXML
    private Label tfemail;
    @FXML
    private Label tftelephone;
    @FXML
    private Label tfroles;
    @FXML
    private Button tfis_expired;
    
    private User user;
    @FXML
    private Label tfid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
   public Button getbtn(){
   return tfis_expired;
   }
   
   public User getu(){
   return user;
   }
   
           
    public void Setdata(User u) {
        this.user = u;
        tfid.setText(""+u.getId());
        tfemail.setText(u.getEmail());
        tfusername.setText(u.getUsername());
        tftelephone.setText(u.getTelephone());
        tfroles.setText(u.getRoles());
        //tfis_expired.setOnAction(u.setIs_expired(true));
       //u.setIs_expired(Boolean.parseBoolean(tfis_expired.getText()));

    }
    public void SetdataStock(Stock u) {
        tfid.setText(String.valueOf(u.getId()));
        tfemail.setText(String.valueOf(u.getQuantity()));
        tfusername.setText(u.getName());
        tftelephone.setText(String.valueOf(u.getRest_q()));

    }
    private void executeQuery(String req) {
        Connection cnx = DataSource.getInstance().getCnx();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
      public void DeleteStock() {
        String req = "delete from Stock where id=" + tfid.getText() + "";
        executeQuery(req);
        
    }
        public void Delete() {
        String req = "delete from user where id=" + tfid.getText() + "";
        executeQuery(req);
        
    }


//    @FXML
//    private void Delete(ActionEvent event) {
//        ServiceUser co = new ServiceUser();
//        User u = new User();
//        co.supprimer1(tfid.getT);
//    }
   
   

}
