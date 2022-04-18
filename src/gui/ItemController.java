/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Plat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import test.MyListener;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    private Plat plat;
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void click(MouseEvent event) {
    }
    public void setData(Plat plat, MyListener myListener) {
        
        
        this.myListener = myListener;
        nameLabel.setText(plat.getNom());
        priceLable.setText(""+plat.getPrix());
        //Image image = new Image(getClass().getResourceAsStream(plat.getImg()));
        //img.setImage(image);
        
    }   
    
}
