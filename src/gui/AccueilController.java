/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AccueilController implements Initializable {

    @FXML
    private Button a;
    @FXML
    private Button c;
    @FXML
    private Button b;
    @FXML
    private Button d;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.img = new ImageView("file:/assets/image/logo.png");
    }    

    @FXML
    private void affplat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            a.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void affcateg(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherCateg.fxml"));
            b.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajoutplat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutFXML.fxml"));
            c.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajoutcateg(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutCateg.fxml"));
            d.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
