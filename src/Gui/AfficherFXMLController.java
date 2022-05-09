/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import entities.Plat;
import entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.CategorieService;
import Services.PlatService;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AfficherFXMLController implements Initializable {

    @FXML
    private TableView<Plat> tableview;
    @FXML
    private TableColumn<Plat, String> nom;
    private TableColumn<Plat, String> prenom;
    private TableColumn<Plat, Integer> age;
    PlatService Sp = new PlatService();

    ObservableList list ;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private Button non;
    @FXML
    private TextField nnom;
    @FXML
    private TextField ndesc;
    @FXML
    private TextField nprix;
    @FXML
    private Button Edp;
    @FXML
    private Button Delp;
    @FXML
    private Button back;
    @FXML
    private ComboBox<String> combCat;
    CategorieService servCat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        PlatService ps = new PlatService();
        List<Plat> plats = ps.recuperer();
        list = FXCollections.observableList(plats);
        tableview.setItems(list);
        servCat = new CategorieService();
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("desc"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ObservableList<String> catNames = FXCollections
                .observableArrayList(
                        servCat.recuperer().stream().map(c -> c.getNom()).collect(Collectors.toList())
                );
        
        System.out.println(catNames);

        combCat.setItems(catNames);
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutFXML.fxml"));
            non.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void DelP(ActionEvent event) {
       final Plat selectedItem = tableview.getSelectionModel().getSelectedItem();
        Plat prod = Sp.GetById(selectedItem.getId());
        Sp.supprimer(prod.getId());
        
        list.remove(selectedItem);
        tableview.setItems(FXCollections.observableArrayList(Sp.recuperer()));
        tableview.refresh();
    }

    @FXML
    private void EdP(ActionEvent event) {
        
        final int idcategorie = servCat.getIdByCategoryName(combCat.getValue());
        final Plat selectedItem = tableview.getSelectionModel().getSelectedItem();
        
        Plat prod = Sp.GetById(selectedItem.getId());
        
        prod.setCategorie_id(idcategorie);
        prod.setNom(nnom.getText());
        prod.setPrix(Double.parseDouble(nprix.getText()));
        prod.setDesc(ndesc.getText());
        Sp.modifier(prod);
        tableview.setItems(FXCollections.observableArrayList(Sp.recuperer()));
        tableview.refresh();
        
        
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
    
}
