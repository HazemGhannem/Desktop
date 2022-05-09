package Gui;

import entities.Pack;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Services.PackService;

public class CrudPackController implements Initializable {
    PackService pk = new PackService();

    @FXML
    private TextField TFType;

    @FXML
    private TextField TFPrix;

    @FXML
    private TextField TFDescription;

    @FXML
    private Button BTNAdd;

    @FXML
    private Button BTNReset;

    @FXML
    private TextField TFSearch;

    @FXML
    private Button BTNUpdate;

    @FXML
    private Button BTNDelete;

    @FXML
    private TextField TFId;

    @FXML
    private TableView<Pack> TVPack;
    @FXML
    private TableColumn<Pack, String> COLType;

    @FXML
    private TableColumn<Pack, Float> COLPrix;

    @FXML
    private TableColumn<Pack, String> COLDescription;
     @FXML
    private Button back;
    @FXML
    private Button statistique;
     public boolean controlNotEmpty() {
        if (TFType.getText().isEmpty() || TFPrix.getText().isEmpty() || TFDescription.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Please Verify Your Input Fields");
            alert.showAndWait();
            return false;
        }else if (!Pattern.matches("[-+]?[0-100]*\\.?[0-9]+([eE][-+]?[1-9]+)?", TFPrix.getText())||Double.valueOf(TFPrix.getText())<0.01) {
            TFPrix.requestFocus();
            TFPrix.selectEnd();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Prix Invalid");
            alert.showAndWait();
            return false;
        }
       
        return true;
    }

    @FXML
    void GetSelectPack(MouseEvent event) {
        if((TVPack.getSelectionModel().isEmpty())==false){
            Pack p =  TVPack.getSelectionModel().getSelectedItem();
            TFId.setText(String.valueOf(p.getId()));
            TFType.setText(String.valueOf(p.getType()));
            TFPrix.setText(String.valueOf(p.getPrix()));
            TFDescription.setText(String.valueOf(p.getDescription()));
           
    }

    }

    @FXML
    void HundilButtonAdd(ActionEvent event) {
        if (controlNotEmpty()){
            Pack p = new Pack(TFType.getText(), Float.parseFloat(TFPrix.getText()),TFDescription.getText());
            pk.ajouter(p);
            TVPack.getItems().add(p);
            TVPack.refresh();
            Notifications notification = Notifications.create()
                .title("Confirmation")
                .text("Votre Pack est ajoutée ")
                 //.graphic(new ImageView(img))
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked on notification");
                    }
                });
                notification.showConfirm();
                notification.darkStyle();
                
            TVPack.refresh();
        
           
        }

    }

    @FXML
    void HundilButtonDelete(ActionEvent event) {
          if(controlNotEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Suppression");
                alert.setContentText("Voulez vous supprimer ce champ?");
                Optional<ButtonType> result=alert.showAndWait();
                if (result.isPresent()&& result.get()==ButtonType.OK){
                    Pack p= TVPack.getSelectionModel().getSelectedItem();
                    TVPack.getItems().remove(p);
                    pk.supprimer(p.getId());
                    TVPack.refresh();
                    Notifications notification = Notifications.create()
                        .title("Pack Deleted")
                        .text("Saved in your DATABASE")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Clicked on notification");
                            }
                        });

                notification.showConfirm();
                notification.darkStyle();
            }}

    }

    @FXML
    void HundilButtonReset(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Suppression");
         alert.setContentText("Voulez vous supprimer tous les Packs?");
         Optional<ButtonType> result=alert.showAndWait();
         if (result.isPresent()&& result.get()==ButtonType.OK){pk.supprimerTout();pk.reset();}

    }

    @FXML
    void HundilButtonUpdate(ActionEvent event) {
         if(controlNotEmpty()){
                if(!TVPack.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification");
                alert.setContentText("Voulez vous modifiez ce champ?");
                Optional<ButtonType> result=alert.showAndWait();
                if (result.isPresent()&& result.get()==ButtonType.OK){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm");

                        Pack p = new Pack(Integer.parseInt(TFId.getText()),TFType.getText(), Float.parseFloat(TFPrix.getText()),TFDescription.getText());
                        pk.modifier(p);
                        TVPack.getItems().remove(TVPack.getSelectionModel().getSelectedItem());
                        TVPack.getItems().add(p);
                        TVPack.refresh();
                        Notifications notification = Notifications.create()
                            .title("Pack Modifier")
                            .text("Saved in your DATABASE")
                            // .graphic(new ImageView(img))
                            .graphic(null)
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.CENTER)
                            .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Clicked on notification");
                            }
                        });

                notification.showConfirm();
                notification.darkStyle();}
                    }else{
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Modification Fail");
                    alert1.setContentText("Voulez vous selectionner un champ?");
                }}

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        COLType.setCellValueFactory(new PropertyValueFactory<Pack, String>("Type"));
        COLPrix.setCellValueFactory(new PropertyValueFactory<Pack, Float>("Prix"));
        COLDescription.setCellValueFactory(new PropertyValueFactory<Pack, String>("Description"));
             ObservableList<Pack> list = (FXCollections.observableArrayList(pk.recuperer())) ;
       
        TVPack.setItems(list);
        TVPack.refresh();
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
     @FXML
    private void statistique(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) statistique.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Chart_2.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
