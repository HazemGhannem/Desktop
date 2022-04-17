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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tftelephone;
    @FXML
    private TextField tfpasswordvisible;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnadd;
    @FXML
    private CheckBox check_af;
    @FXML
    private Label lab_email;
    @FXML
    private Label lab_cin;
    @FXML
    private PasswordField tfcpassword;
    private ServiceUser userService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new ServiceUser();

    }

    @FXML
    private void btnadd(ActionEvent event) throws IOException {
        User user = new User(tfusername.getText(), tftelephone.getText(), tfemail.getText(), tfpassword.getText());
        if (tfusername.getText().length() != 0 || tfemail.getText().length() != 0) {

            userService.ajouter(user);
            AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(800);
            stage.setHeight(400);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

        } else {
            System.out.println("wrong");
        }
    }
}
