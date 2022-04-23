/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.User;
import Services.ServiceUser;
import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.sql.ResultSet;
import javafx.scene.control.Button;
import java.io.IOException;
import static javafixauth.JavaFixAuth.loggedInID;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class LoginController implements Initializable {

    Connection cnx = DataSource.getInstance().getCnx();
    ResultSet ResultSet = null;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnlogin;
    private ServiceUser userService;
    @FXML
    private Button register;
    @FXML
    private Button forgetpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userService = new ServiceUser();
    }

    @FXML
    private void btnlogin(ActionEvent event) throws IOException {
        ServiceUser co = new ServiceUser();
        System.out.println(co.getById().getRoles());
        String password= DigestUtils.md5Hex(tfpassword.getText());
        
//         String index=co.getById().getRoles();
//
//        for (String number: index) {
//      System.out.println(number);
//    }
//        
       
       
       if ( co.getUserBy(tfemail.getText(), password)){
           System.out.println(co.getById().getRoles());
           System.out.println(loggedInID);
           if (co.getById().getRoles().equals("[\"ROLE_ADMIN\"]") ){
           
         AnchorPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setWidth(1050);
                stage.setHeight(576);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
                System.out.println("jawik behi");
        }else{
        AnchorPane root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(580);
        stage.setHeight(490);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
           }}
       
       
       
        
       else {
           System.out.println("nooo");
       }
        
        
        
        
        
        
        
        
        
        
        
        
/*amirr
        User userVerif = null;

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please Correct invalid fields");
        if (tfpassword.getText().split(" ").length == 0 || tfemail.getText().length() == 0) {
            alert.setContentText("Invalide credential");
            alert.showAndWait();
        } else {
            userVerif = userService.login(tfemail.getText(), tfpassword.getText());

            System.out.println(tfemail.getText());
            System.out.println(tfpassword.getText());
              AnchorPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setWidth(1050);
                stage.setHeight(576);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
                }
          
        amirr */

//        if(userVerif == null){
//            alert.setContentText("User with email:"+tfemail.getText()+" notFound");
//            alert.showAndWait();}
////          }else{
////            HomeController.getInstance().setIdUser(userVerif.getId());
////            HomeController.getInstance().idUserLb.setText(String.valueOf(userVerif.getId()));
////            HomeController.getInstance().setUser();
////            HomeController.getInstance().welcomeMsg.setVisible(true);
////            HomeController.getInstance().signInLabel.setText("MyProfile");
////            HomeController.getInstance().signUpLabel.setText("Log-out");
//
//            if(userVerif.getRoles().toString().equals("ADMIN")){
//                BorderPane root = FXMLLoader.load(getClass().getResource("Register.fxml"));
//                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.setMaximized(true);
//                stage.show();}
////            }else{
////                HomeController.getInstance().loadHome();
////            }
    }

//    private String logIn() {
//       String status = "Success";
//        String email = tfemail.getText();
//        String password = tfpassword.getText();
//        if(email.isEmpty() || password.isEmpty()) {
//            //setLblError(Color.TOMATO, "Empty credentials");
//            status = "Error";
//        } else {
//            //query
//            String sql = "SELECT * FROM user Where email = ? and password = ?";
//            try {
//                PreparedStatement stmt;
//                stmt = cnx.prepareStatement(sql);
//                stmt.setString(1, tfemail.getText());
//                stmt.setString(2, tfpassword.getText());
//                ResultSet = stmt.executeQuery();                 
//                if (!ResultSet.next()) {
//                   // setLblError(Color.TOMATO, "Enter Correct Email/Password");
//                    status = "Error";
//              } 
//                //else {
////                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
////                }
//            } catch (SQLException ex) {
//                System.err.println(ex.getMessage());
//                status = "Exception";
//            }
//        }
//        
//        return status;
//    }
//public boolean Login(){
//        try {
//            PreparedStatement stmt;
//            
//            String req="SELECT * from user where email= ? AND password= ?";
//            stmt = cnx.prepareStatement(req);
//            stmt.setString(1, tfemail.getText());
//            stmt.setString(2, tfpassword.getText());
//            stmt.executeUpdate();
//            
//            if(user!=null)
//                try {
//                    Statement st = cnx.createStatement();
//                    st.executeUpdate(req);
//                } catch (SQLException ex) {
//                    System.out.println(ex);
//                }else System.out.println("n'existe pas");
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
    private void btnsignup(KeyEvent event) {
    }

    @FXML
    private void register(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(580);
        stage.setHeight(490);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void restpassword(KeyEvent event) {
    }

    @FXML
    private void forgetpass(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("RestPassword.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(580);
        stage.setHeight(490);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
