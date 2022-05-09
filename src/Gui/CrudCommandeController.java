/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Commande;
import Services.CommandeService;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author alaed
 */
public class CrudCommandeController implements Initializable {
    Integer id; 

    @FXML
    private TextField tfNum;
    @FXML
    private TextField tfEtat;
    @FXML
    private CheckBox ckPaye;
    @FXML
    private DatePicker dDate;
    @FXML
    private TableView<Commande> tvCommandes;
    @FXML
    private TableColumn<Commande, Integer> colID;
    @FXML
    private TableColumn<Commande, Integer> colNUM;
    @FXML
    private TableColumn<Commande, String> colDATE;
    @FXML
    private TableColumn<Commande, String> colETAT;
    @FXML
    private TableColumn<Commande, Boolean> colPAYE;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    
    
      ObservableList list ;
      ObservableList<Commande> dataList;
    @FXML
    private Button btnImprimer;
    @FXML
    private ToggleButton btnClose;
    @FXML
    private TextField tfFilter;
    @FXML
    private Button btnSupprimer1;

    /**
     * Initializes the controller class.
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
        CommandeService cs = new CommandeService(); 
       showCommande();
       
       //heeeree supp
         tvCommandes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id = cs.recuperer()
                        .get(tvCommandes.getSelectionModel().getSelectedIndex())
                        .getId();
                //System.out.println(iddd);
                tfNum.setText( String.valueOf(cs.recuperer()
                        .get(tvCommandes.getSelectionModel().getSelectedIndex())
                        .getNum()));
                
               // tfDate.setText(cs.recuperer()
                    //    .get(tvCommandes.getSelectionModel()
                             //   .getSelectedIndex())
                     //   .getDate_commande());
                
                tfEtat.setText(cs.recuperer()
                        .get(tvCommandes.getSelectionModel()
                                .getSelectedIndex())
                        .getEtat()
                );
                
                
             
                
  
              
                
               
                };
            
               
         });
       
    }    
    
    private void showCommande() {
        
        CommandeService cs = new CommandeService();
        List<Commande> Commande = cs.recuperer();
        list = FXCollections.observableList(Commande);
        tvCommandes.setItems(list);
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNUM.setCellValueFactory(new PropertyValueFactory<>("num"));
        colDATE.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
        colETAT.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colPAYE.setCellValueFactory(new PropertyValueFactory<>("est_payee"));
        
    }
    
    

    @FXML
    private void ajouterCommande(ActionEvent event) {
        
         Commande c = new Commande();
        c.setNum(Integer.parseInt(tfNum.getText()));
        c.setDate_commande(dDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));  
       //c.setDate_commande((tfDate.getText()));
       
        c.setEtat(tfEtat.getText());
        c.setEst_payee(ckPaye.isSelected());
        CommandeService cs = new CommandeService();
        cs.ajouter(c);
        //Alert a = new Alert(Alert.AlertType.CONFIRMATION);
       // a.setTitle("Commqnde ajoutée");
       // a.show();
       JOptionPane.showMessageDialog(null, "commande ajouté succes");
       showCommande();
    
    }

    @FXML
    private void modifierCommande(ActionEvent event) {
        
        
      
        
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++
         Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
        alert3.setTitle("Confirmationu");
       alert3.setHeaderText("voulez vous modifier cet commande  ?");
        Optional<ButtonType> result = alert3.showAndWait();
        
         Commande c = new Commande();
               c.setNum(Integer.parseInt(tfNum.getText()));
              c.setDate_commande(dDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
              c.setEtat(tfEtat.getText());
               
      if (result.get() == ButtonType.OK) {
            
              
               c.setEst_payee(ckPaye.isSelected());
               
            CommandeService cs = new CommandeService();
            cs.modifier(c);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("modifier");
           alert.setHeaderText(null);
            alert.setContentText(" commande modifié");
            alert.show();
            showCommande();

      } else {
            alert3.close();
        }
        
    }

    @FXML
    private void supprimerCommande(ActionEvent event) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer cet commande  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            CommandeService cs = new CommandeService();
            cs.supprimer(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("supprimer");
            alert.setHeaderText(null);
            alert.setContentText(" commande supprimé");
            alert.show();
            showCommande();

        } else {
            alert2.close();
        }
    }
    
    
    
    
    
    private void goToLivraison(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudLivraison.fxml"));
            Parent root = loader.load();
            
            btnAjouter.getScene().setRoot(root);
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
    
    
    
    @FXML
    private void imprimer(ActionEvent event) {
      Connection  cnx = DataSource.getInstance().getCnx();
        try{
            
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\Hazem\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\Reports\\CommandeReports.jrxml");  
            
        String sql="SELECT\n" +
"     commande.`num` AS num,\n" +
"     commande.`date_commande` AS date,\n" +
"     commande.`etat` AS items,\n" +
"     commande.`est_payee` AS est_payee\n" +
"FROM\n" +
"     `commande` commande";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasperDesign.setQuery(newQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, cnx);
            JasperViewer.viewReport(jasperPrint,false);           
        } catch (JRException ex) {
             System.out.println(ex.getMessage());
        }
      
    }

    @FXML
    private void close(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root = loader.load();
            
            btnAjouter.getScene().setRoot(root);
            
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Filtrer(ActionEvent event) {
    }
    
    
    
     public void searchProduit(){
         
     }
    
    
    
}
