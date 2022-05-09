/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;



import entities.Livraison;


import Services.LivraisonService;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author alaed
 */
public class CrudLivraisonController implements Initializable {
    Integer id; 

    @FXML
    private TextField tfLatitude;
    @FXML
    private TextField tfLongitude;
  
    @FXML
    private TableView<Livraison> tvLivraisons;
    @FXML
    private TableColumn<Livraison, Integer> colID;
    @FXML
    private TableColumn<Livraison, Integer> colLONG;
    @FXML
    private TableColumn<Livraison, String> colLAT;
   
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    
    
      ObservableList list ;
    @FXML
    private ToggleButton btnClose;
    @FXML
    private Button btnAjouter1;
    @FXML
    private Button btnSupprimer1;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnModifier1;
    @FXML
    private Button btnMap1;
    @FXML
    private TextField tfFilter;
  
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       // CommandeService cs = new CommandeService();
       // List<Commande> Commande = cs.recuperer();
      //  list = FXCollections.observableList(Commande);
       // tvCommandes.setItems(list);
        
       // colID.setCellValueFactory(new PropertyValueFactory<>("id"));
       // colNUM.setCellValueFactory(new PropertyValueFactory<>("num"));
       // colDATE.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
       // colETAT.setCellValueFactory(new PropertyValueFactory<>("etat"));
       // colPAYE.setCellValueFactory(new PropertyValueFactory<>("est_payee"));
        LivraisonService ls = new LivraisonService(); 
       showLivraison();
       
       //heeeree supp
         tvLivraisons.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id = ls.recuperer()
                        .get(tvLivraisons.getSelectionModel().getSelectedIndex())
                        .getId();
                //System.out.println(iddd);
               
                 tfLongitude.setText(ls.recuperer()
                        .get(tvLivraisons.getSelectionModel()
                                .getSelectedIndex())
                        .getLatitude());
                  tfLongitude.setText(ls.recuperer()
                        .get(tvLivraisons.getSelectionModel()
                                .getSelectedIndex())
                        .getLongitude());
                
               
                
                
             
                
  
              
                
               
                };
            
               
         });
       
    }    
    
    private void showLivraison() {
        
         LivraisonService ls = new LivraisonService();
        List<Livraison> Livraison = ls.recuperer();
        list = FXCollections.observableList(Livraison);
        tvLivraisons.setItems(list);
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLAT.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        colLONG.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        
        
    }
    
    

    

   
    @FXML
    private void ajouterLivraison(ActionEvent event) {
          Livraison l = new Livraison();
       
        l.setLongitude(tfLongitude.getText());
        l.setLatitude(tfLatitude.getText());
      
        LivraisonService ls = new LivraisonService();
        ls.ajouter(l);
        //Alert a = new Alert(Alert.AlertType.CONFIRMATION);
       // a.setTitle("Commqnde ajoutée");
       // a.show();
       JOptionPane.showMessageDialog(null, "livraison ajouté avec succes");
       showLivraison();
        
    }

    @FXML
    private void modifierLivraison(ActionEvent event) {
    }

    @FXML   
 private void supprimerLivraison(ActionEvent event) {
       Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer cet commande  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            LivraisonService ls = new LivraisonService();
            ls.supprimer(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("supprimer");
            alert.setHeaderText(null);
            alert.setContentText(" livraison supprimé");
            alert.show();
            showLivraison();

        } else {
            alert2.close();
        }
    }
 
 
    
    private void goToCommande(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudCommande.fxml"));
            Parent root = loader.load();
            
            btnAjouter.getScene().setRoot(root);
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
}

    @FXML
    private void close(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();
            
            btnAjouter.getScene().setRoot(root);
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToMap(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../mapALA/MAP.fxml"));
            Parent root = loader.load();
            
            btnAjouter.getScene().setRoot(root);
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void imprimer(ActionEvent event) {
    }

    @FXML
    private void Filtrer(ActionEvent event) {
    }
    
    
    
}
