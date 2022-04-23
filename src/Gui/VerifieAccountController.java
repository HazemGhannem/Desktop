/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.Codes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class VerifieAccountController implements Initializable {

    @FXML
    private TextField verif;
    @FXML
    private Button done;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void done(ActionEvent event) throws IOException {
        Codes co = new Codes();
        
        String verific=co.getByI().getMailcode();
        String v=verif.getText();
        if(v.equals(verific)){
            if (co.getByI().getRoles().equals("[\"ROLE_ADMIN\"]")) {
                co.Nullcodemail();
                System.out.println("set code null");
                co.Verif();
                 System.out.println("set verif true");
                    AnchorPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setWidth(1050);
                    stage.setHeight(576);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                    
                    System.out.println("jawik behi");
                } else {
                co.Nullcodemail();
                System.out.println("set code null");
                co.Verif();
                System.out.println("set verif true");

                    AnchorPane root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setWidth(580);
                    stage.setHeight(490);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                }
        }
    }
    
}
