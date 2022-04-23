/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.User;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafixauth.JavaFixAuth.loggedInID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class ProfileController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private ImageView pic;
      private ServiceUser userService;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new ServiceUser();
        String path = "/Image/"+userService.getById().getImage();
        Image image = new Image(getClass().getResourceAsStream(path));
       pic.setImage(image);
       // System.out.println(loggedInID);
        //System.out.println("yo");

    }    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(580);
        stage.setHeight(490);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
}
