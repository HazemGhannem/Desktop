/*
 * Click nbfs://nbhost/SystemFileSystem/TemCategoriees/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/TemCategoriees/javafx/FXMLController.java to edit this temCategoriee
 */
package gui;

import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AjoutCategController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField image;
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
        //this.img = new ImageView("file:/assets/image/Logo.png");
        
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
      Categorie p = new Categorie();
      
      if (((nom.getText().length() != 0) && (isAlpha(nom.getText()))) && ((description.getText().length() != 0) && (isAlpha(description.getText())))
                ){
        p.setNom(nom.getText());
        p.setImg("nananna");
        p.setDesc(description.getText());
        CategorieService ps = new CategorieService();
        ps.ajouter(p);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Categorie ajoutée");
        a.show();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherCateg.fxml"));
            non.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setContentText("Non Merci!");
            alert.show();
        }
    }

    @FXML
    private void Afficher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherCateg.fxml"));
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
    
}
