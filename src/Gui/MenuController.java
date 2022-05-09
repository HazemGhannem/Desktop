/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

/**
 * FXML Controller class
 *
 * @author alaed
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private BorderPane body;
    @FXML
    private VBox sideArea;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private BorderPane contentPane;
    @FXML
    private VBox componentBox;
    @FXML
    private Pane handPaneMac;
    @FXML
    private VBox sideNav;
    @FXML
    private Region navHome;
    @FXML
    private Region navCart;
    @FXML
    private HBox sideControls;
    @FXML
    private Label closeButton;
    @FXML
    private SVGPath btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            // TODO
            contentPane.setCenter(new HomeView().getView());
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
     public void showHomeView() throws IOException {
       contentPane.setCenter(new HomeView().getView());
    }    
    @FXML
      public void showCartView() throws IOException {
       contentPane.setCenter(new CartView().getView());
    }    

    @FXML
    private void goToMenu(MouseEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root = loader.load();
            
            navHome.getScene().setRoot(root);
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
