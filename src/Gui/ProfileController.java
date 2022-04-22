/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.User;
import Services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import static javafixauth.JavaFixAuth.loggedInID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new ServiceUser();
        String path = "/Image/"+userService.getById().getImage();
        Image image = new Image(getClass().getResourceAsStream(path));
       pic.setImage(image);
System.out.println(loggedInID);
System.out.println("yo");

    }    
    
}
