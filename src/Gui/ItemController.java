/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.Stock;
import Models.User;
import java.net.URL;
import java.util.ResourceBundle;
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
        tfemail.setText(u.getEmail());
        tfusername.setText(u.getUsername());
        tftelephone.setText(u.getTelephone());
        tfroles.setText(u.getRoles());
        //tfis_expired.setOnAction(u.setIs_expired(true));
       //u.setIs_expired(Boolean.parseBoolean(tfis_expired.getText()));

    }
    public void SetdataStock(Stock u) {
        tfemail.setText(String.valueOf(u.getQuantity()));
        tfusername.setText(u.getName());
        tftelephone.setText(String.valueOf(u.getRest_q()));

    }
   
   

}
