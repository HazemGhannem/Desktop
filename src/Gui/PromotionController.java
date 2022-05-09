/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Promotion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafixauth.MyListener1;
import javafixauth.JavaFixAuth;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class PromotionController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Label TFPourcentage;
    @FXML
    private Label TFNom;
    @FXML
    private Label TFPrixoriginal;
    @FXML
    private ImageView img;
    private Promotion promotion ;
     private MyListener1 myListener1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
     public void setData(Promotion promotion, MyListener1 myListener1) {
        
        
        this.myListener1 = myListener1;
        TFNom.setText(promotion.getNom());
        
        TFPrixoriginal.setText(""+promotion.getPrixoriginal());
        TFPourcentage.setText(JavaFixAuth.CURRENCY1 +promotion.getPourcentage());
        img.setImage(new Image("/Image/" + promotion.getImage()));
        this.promotion = promotion;
         
        
    } 

    @FXML
    private void details(MouseEvent event) {
        myListener1.onClickListener(promotion);
    }

    @FXML
    private void click(MouseEvent event) {
        
    }
    
}
