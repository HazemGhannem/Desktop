package gui;

import entities.Promotion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.controlsfx.control.Notifications;
import services.PromotionService;

public class CrudPromotionController implements Initializable {
    PromotionService pr = new PromotionService();

    @FXML
    private TextField TFNom;

    @FXML
    private TextField TFPourcentage;

    @FXML
    private TextField TFDescription;

    @FXML
    private DatePicker DPDatedebut;

    @FXML
    private DatePicker DPDatefin;

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
    private TableView<Promotion> TVPromotion;

    @FXML
    private TableColumn<Promotion, String> COLNom;

    @FXML
    private TableColumn<Promotion, Integer> COLPourcentage;

    @FXML
    private TableColumn<Promotion, String> COLDescription;

    @FXML
    private TableColumn<Promotion, Double> COLPrixoriginal;

    @FXML
    private TableColumn<Promotion, Double> COLPrixpromotion;

    @FXML
    private TableColumn<Promotion, Date> COLDatedebut;

    @FXML
    private TableColumn<Promotion, Date> COLDatefin;
     File file; 

    

    @FXML
    private TextField TFPrixoriginal;
     

    @FXML
    private Button TFImage;
    @FXML
    private Button back;
    @FXML
    private Button statistique;
        public boolean controlNotEmpty() {
        if (TFNom.getText().isEmpty() || TFPourcentage.getText().isEmpty() || TFDescription.getText().isEmpty()|| TFPrixoriginal.getText().isEmpty() || DPDatedebut.getValue()==null ||DPDatefin.getValue()==null|| TFImage.getText().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Please Verify Your Input Fields");
            alert.showAndWait();
            return false;
        }else if (!Pattern.matches("\\d+", TFPourcentage.getText())||Integer.valueOf(TFPourcentage.getText())<1) {
            TFPourcentage.requestFocus();
            TFPourcentage.selectEnd();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Pourcentage Invalid");
            alert.showAndWait();
            return false;
        
        }else if (!Pattern.matches("[-+]?[0-100]*\\.?[0-9]+([eE][-+]?[1-9]+)?", TFPrixoriginal.getText())||Double.valueOf(TFPrixoriginal.getText())<0.01) {
            TFPrixoriginal.requestFocus();
            TFPrixoriginal.selectEnd();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Prixoriginal Invalid");
            alert.showAndWait();
            return false;
        }
       
        return true;
    }

    @FXML
    void GetSelectPromotion(MouseEvent event) {
         if((TVPromotion.getSelectionModel().isEmpty())==false){
            Promotion p =  TVPromotion.getSelectionModel().getSelectedItem();
            TFId.setText(String.valueOf(p.getId()));
            TFNom.setText(String.valueOf(p.getNom()));
            TFPourcentage.setText(String.valueOf(p.getPourcentage()));
            TFDescription.setText(String.valueOf(p.getDescription()));
            TFPrixoriginal.setText(String.valueOf(p.getPrixoriginal()));

            DPDatedebut.setValue(LocalDate.parse(String.valueOf(p.getDatedebut())));
            DPDatefin.setValue(LocalDate.parse(String.valueOf(p.getDatefin())));
            TFImage.setText(String.valueOf(p.getImage()));
    }

    }

    @FXML
    void HundilButtonAdd(ActionEvent event) throws Exception {
         if (controlNotEmpty()){
             FileInputStream fl = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            String path = fileName;
            fl.read(data);
            fl.close();
            Promotion p = new Promotion(TFNom.getText(), Integer.parseInt(TFPourcentage.getText()),TFDescription.getText(), Double.parseDouble(TFPrixoriginal.getText()),Date.valueOf(DPDatedebut.getValue()), Date.valueOf(DPDatefin.getValue()),path);
            pr.ajouter(p);
            TVPromotion.refresh();
            TVPromotion.getItems().add(p);
            
            Notifications notification = Notifications.create()
                .title("Confirmation")
                .text("Votre Promotion est ajoutée ")
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
                
            TVPromotion.refresh();

    }}

    @FXML
    void HundilButtonDelete(ActionEvent event) {
         if(controlNotEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Suppression");
                alert.setContentText("Voulez vous supprimer ce champ?");
                Optional<ButtonType> result=alert.showAndWait();
                if (result.isPresent()&& result.get()==ButtonType.OK){
                    Promotion p= TVPromotion.getSelectionModel().getSelectedItem();
                    TVPromotion.getItems().remove(p);
                    pr.supprimer(p.getId());
                    TVPromotion.refresh();
                    Notifications notification = Notifications.create()
                        .title("Promotion Deleted")
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
         alert.setContentText("Voulez vous supprimer tous les Promotions?");
         Optional<ButtonType> result=alert.showAndWait();
         if (result.isPresent()&& result.get()==ButtonType.OK){pr.supprimerTout();pr.reset();}

    }

    @FXML
    void HundilButtonUpdate(ActionEvent event) {
        if(controlNotEmpty()){
                if(!TVPromotion.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification");
                alert.setContentText("Voulez vous modifiez ce champ?");
                Optional<ButtonType> result=alert.showAndWait();
                if (result.isPresent()&& result.get()==ButtonType.OK){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm");

                        Promotion p = new Promotion(Integer.parseInt(TFId.getText()),TFNom.getText(), Integer.parseInt(TFPourcentage.getText()),TFDescription.getText(), Double.parseDouble(TFPrixoriginal.getText()),Date.valueOf(DPDatedebut.getValue()), Date.valueOf(DPDatefin.getValue()),TFImage.getText());
                        pr.modifier(p);
                        TVPromotion.getItems().remove(TVPromotion.getSelectionModel().getSelectedItem());
                        TVPromotion.getItems().add(p);
                        TVPromotion.refresh();
                        Notifications notification = Notifications.create()
                            .title("Promotion Modifier")
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
        COLNom.setCellValueFactory(new PropertyValueFactory<Promotion, String>("Nom"));
        COLPourcentage.setCellValueFactory(new PropertyValueFactory<Promotion, Integer>("Pourcentage"));
        COLDescription.setCellValueFactory(new PropertyValueFactory<Promotion, String>("Description"));
        COLPrixoriginal.setCellValueFactory(new PropertyValueFactory<Promotion, Double>("Prixoriginal"));
        COLPrixpromotion.setCellValueFactory(new PropertyValueFactory<Promotion, Double>("Prixpromotion"));
        COLDatedebut.setCellValueFactory(new PropertyValueFactory<Promotion, Date>("Datedebut"));
        COLDatefin.setCellValueFactory(new PropertyValueFactory<Promotion, Date>("Datefin"));
        
        ObservableList<Promotion> list = (FXCollections.observableArrayList(pr.recuperer())) ;
        
        TVPromotion.getItems().addAll(list);
        TVPromotion.refresh();  }

    @FXML
    private void accueil(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("AccueilFXML.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void statistique(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) statistique.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Chart.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private File chooseImage(ActionEvent event) {
         Path to1 = null;
        String m = null;
        String path = "C:\\Users\\pc\\Desktop\\3A47\\src\\image";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
         System.out.println(file.getPath());
        return file;
    }

}
