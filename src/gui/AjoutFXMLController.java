/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.PlatService;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AjoutFXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private Button non;
    @FXML
    private ImageView img;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.img = new ImageView("file:/assets/image/non.png");
        
    }

   
    @FXML
    private void Ajouter(ActionEvent event) {
        Plat p = new Plat();
        if (((nom.getText().length() != 0) && (isAlpha(nom.getText()))) && ((description.getText().length() != 0) && (isAlpha(description.getText())))
                && (prix.getText().length() != 0 && (isNumeric(prix.getText())))){
        p.setNom(nom.getText());
        p.setImg("nananna");
        p.setDesc(description.getText());
        p.setPrix(Double.parseDouble(prix.getText()));
        PlatService ps = new PlatService();
        ps.ajouter(p);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Plat ajoutée");
        a.show();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            non.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
    }

    @FXML
    private void Afficher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            non.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void accueil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
