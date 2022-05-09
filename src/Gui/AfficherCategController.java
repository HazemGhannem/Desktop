/*
 * Click nbfs://nbhost/SystemFileSystem/TemCategoriees/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/TemCategoriees/javafx/FXMLController.java to edit this temCategoriee
 */
package Gui;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.CategorieService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AfficherCategController implements Initializable {

    @FXML
    private TableView<Categorie> tableview;
    @FXML
    private TableColumn<Categorie, String> nom;
    @FXML
    private TableColumn<Categorie, String> description;
    CategorieService Sp = new CategorieService();
    ObservableList list ;
    @FXML
    private Button Edp;
    @FXML
    private TextField nnom;
    @FXML
    private TextField ndesc;
    @FXML
    private Button Delp;
    @FXML
    private Button non;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieService ps = new CategorieService();
        List<Categorie> Categories = ps.recuperer();
        list = FXCollections.observableList(Categories);
        tableview.setItems(list);
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("desc"));
        
    }    

    @FXML
    private void EdP(ActionEvent event) {
        final Categorie selectedItem = tableview.getSelectionModel().getSelectedItem();
        Categorie prod = Sp.GetById(selectedItem.getId());
        prod.setNom(nnom.getText());
        prod.setDesc(ndesc.getText());
        Sp.modifier(prod);
        tableview.setItems(FXCollections.observableArrayList(Sp.recuperer()));
        tableview.refresh();
    }

    @FXML
    private void DelP(ActionEvent event) {
        final Categorie selectedItem = tableview.getSelectionModel().getSelectedItem();
        Categorie prod = Sp.GetById(selectedItem.getId());
        Sp.supprimer(prod.getId());
        
        list.remove(selectedItem);
        tableview.setItems(FXCollections.observableArrayList(Sp.recuperer()));
        tableview.refresh();
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutCateg.fxml"));
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
    
}
