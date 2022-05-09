/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private Button a2;
    @FXML
    private Button c2;
    @FXML
    private Button b2;
    @FXML
    private Button b21;
    @FXML
    private Button alaPanier;
    @FXML
    private Button alaLivraison;
    @FXML
    private Button alaCommande;
    @FXML
    private Button home;
    @FXML
    private Button home1;
    @FXML
    private Button home11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.img = new ImageView("file:/assets/Image/logo.png");
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

    @FXML
    private void coupon(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CrudCouponFXML.fxml"));
            a2.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void promotion(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CrudPromotionFXML.fxml"));
            c2.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void pack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CrudPackFXML.fxml"));
            b2.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void packs(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("packsFXML.fxml"));
            b21.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //aaaaaalaaaaaa
    @FXML
    private void commandes(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CrudCommande.fxml"));
            alaCommande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void livraisons(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CrudLivraison.fxml"));
            alaLivraison.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void alaPanier(ActionEvent event) {

    }

    @FXML
    private void affichePanier(ActionEvent event) {
        System.out.println("aze");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            alaPanier.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    private void gestionem(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/MainPane.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    @FXML
    private void gresto(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/MainPane_1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
