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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class Controller implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;

     ServiceUser userService = new ServiceUser();;
      ArrayList<User> user =new ArrayList<>();
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user= (ArrayList<User>) userService.getAll();
        for (int i = 0; i < user.size(); i++) {
            try {
                //System.out.println(user.get(i));
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Item.fxml"));
                HBox hbox= fxmlloader.load();
                ItemController itemcontroller = fxmlloader.getController();
                itemcontroller.Setdata(user.get(i));
                itemcontroller.getbtn().setOnAction(new EventHandler() {
                     @Override
                    public void handle(Event event) {
                        //userService.Bann(user.get(i));
                    }
                });
                
                
                
                
                pnItems.getChildren().add(hbox);
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
}
