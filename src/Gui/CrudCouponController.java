package Gui;

import Services.EnvoyerMail1;
import entities.Coupon;
import entities.Promotion;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import Services.CouponService;

public class CrudCouponController implements Initializable{
    CouponService cs = new CouponService();

    @FXML
    private TextField TFId;

    @FXML
    private TextField TFNom;

    @FXML
    private TextField TFCode;

    @FXML
    private TextField TFPourcentage;

    @FXML
    private DatePicker DPDatedebut;

    @FXML
    private DatePicker DPDatefin;

    @FXML
    private Button BTNAdd;

    @FXML
    private Button BTNReset;

    @FXML
    private TableView<Coupon> TVCoupon;

    @FXML
    private TextField TFSearch;

    @FXML
    private Button BTNUpdate;

    @FXML
    private Button BTNDelete;
     @FXML
    private TableColumn<Coupon, String> COLNom;

    @FXML
    private TableColumn<Coupon, String> COLCode;

    @FXML
    private TableColumn<Coupon, Double> COLPourcentage;

    @FXML
    private TableColumn<Coupon, Date> COLDatedebut;

    @FXML
    private TableColumn<Coupon, Date> COLDatefin;
     @FXML
    private Button back;
    @FXML
    private Button statistique;

    
    
        public boolean controlNotEmpty() {
        if (TFNom.getText().isEmpty() || TFCode.getText().isEmpty() || TFPourcentage.getText().isEmpty()|| DPDatedebut.getValue()==null ||DPDatefin.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Please Verify Your Input Fields");
            alert.showAndWait();
            return false;
        }else if (!Pattern.matches("[-+]?[0-70]*\\.?[0-9]+([eE][-+]?[1-9]+)?", TFPourcentage.getText())||Double.valueOf(TFPourcentage.getText())<0.01) {
            TFPourcentage.requestFocus();
            TFPourcentage.selectEnd();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Pourcentage Invalid");
            alert.showAndWait();
            return false;
        }
       
        return true;
    }
        
        
        
      @FXML
    void GetSelectCoupon(MouseEvent event) {
         if((TVCoupon.getSelectionModel().isEmpty())==false){
            Coupon c =  TVCoupon.getSelectionModel().getSelectedItem();
            TFId.setText(String.valueOf(c.getId()));
            TFNom.setText(String.valueOf(c.getNom()));
            TFCode.setText(String.valueOf(c.getCode()));
            TFPourcentage.setText(String.valueOf(c.getPourcentage()));
            DPDatedebut.setValue(LocalDate.parse(String.valueOf(c.getDatedebut())));
            DPDatefin.setValue(LocalDate.parse(String.valueOf(c.getDatefin())));
            
    }
    }

    @FXML
    void HundilButtonAdd(ActionEvent event) throws Exception {
         if (controlNotEmpty()){
            Coupon c = new Coupon(TFNom.getText(),TFCode.getText(), Double.parseDouble(TFPourcentage.getText()),Date.valueOf(DPDatedebut.getValue()),Date.valueOf(DPDatefin.getValue()));
            cs.ajouter(c);
            TVCoupon.getItems().add(c);
            EnvoyerMail1.sendMail("mohamedyassine.ghazouani@esprit.tn","Hoya! You win a Coupon with FlyFood!\n"+
                    "\n"+
"We want to offre you this Coupon : '"+ TFNom.getText() +"' valid from" + Date.valueOf(DPDatedebut.getValue()) + " to " + Date.valueOf(DPDatefin.getValue()) + "for being active on our website FlyFood\n"+
        "\n"+
        "Code:" +TFCode.getText()+
                    "\n"+
                            "\n"+
                    
        "Enjoy Your Coupon and be Tuned For more 😉\n"+
        "\n"+
        
        "Hope Your Like it 🙂\n"+
        "\n"+
        
        "Have a good meal,\n"+
        "\n"+
        
        "Your friendly FlyFood Team\n"+
        "\n"+
        
        "For any questions do not hesitate to contact us on this number: +216 29 805 005 sent with ❤️ from FlyFood\n");
            TVCoupon.refresh();
            Notifications notification = Notifications.create()
                .title("Confirmation")
                .text("Votre Coupon est ajoutée ")
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
                
            TVCoupon.refresh();
        
           
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
                    Coupon c= TVCoupon.getSelectionModel().getSelectedItem();
                    TVCoupon.getItems().remove(c);
                    cs.supprimer(c.getId());
                    TVCoupon.refresh();
                    Notifications notification = Notifications.create()
                        .title("Coupon Deleted")
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
         alert.setContentText("Voulez vous supprimer tous les Coupons?");
         Optional<ButtonType> result=alert.showAndWait();
         if (result.isPresent()&& result.get()==ButtonType.OK){cs.supprimerTout();cs.reset();}
    }

    @FXML
    void HundilButtonUpdate(ActionEvent event) {
         if(controlNotEmpty()){
                if(!TVCoupon.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification");
                alert.setContentText("Voulez vous modifiez ce champ?");
                Optional<ButtonType> result=alert.showAndWait();
                if (result.isPresent()&& result.get()==ButtonType.OK){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm");

                        Coupon c = new Coupon(Integer.parseInt(TFId.getText()),TFNom.getText(),TFCode.getText(), Double.parseDouble(TFPourcentage.getText()),Date.valueOf(DPDatedebut.getValue()),Date.valueOf(DPDatefin.getValue()));
                        cs.modifier(c);
                        TVCoupon.getItems().remove(TVCoupon.getSelectionModel().getSelectedItem());
                        TVCoupon.getItems().add(c);
                        TVCoupon.refresh();
                        Notifications notification = Notifications.create()
                            .title("Coupon Modifier")
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
        COLNom.setCellValueFactory(new PropertyValueFactory<Coupon, String>("Nom"));
        COLCode.setCellValueFactory(new PropertyValueFactory<Coupon, String>("Code"));
        COLPourcentage.setCellValueFactory(new PropertyValueFactory<Coupon, Double>("Pourcentage"));
        COLDatedebut.setCellValueFactory(new PropertyValueFactory<Coupon, Date>("Datedebut"));
        COLDatefin.setCellValueFactory(new PropertyValueFactory<Coupon, Date>("Datefin"));
        
         ObservableList<Coupon> list = (FXCollections.observableArrayList(cs.recuperer())) ;
        
        TVCoupon.setItems(list);
        TVCoupon.refresh();
     
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
        Parent root = FXMLLoader.load(getClass().getResource("Chart_1.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


   
}
