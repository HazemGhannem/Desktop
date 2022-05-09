/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.Produit;
import Services.ShoppingCart;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author alaed
 */
public class HomeViewController  {

    @FXML
    private GridPane productGridPane;

    /**
     * Initializes the controller class.
     
     * @throws java.io.FileNotFoundException
     */
    @FXML
    public void initialize () throws FileNotFoundException {
        
        // TODO
        
        productGridPane.getChildren().clear(); // fassekht el grid ken m3ebya
        
        VBox productView1 = productView(Produit.PIZZA); 
       // Label label1 = new Label(Produit.PIZZA.name());
       // label1.setPadding(new Insets(5,5,5,5));
        productGridPane.add(productView1,0,0);
        
         VBox productView2 = productView(Produit.BURGER);
       // Label label2 = new Label(Produit.MAKLOUB.name());
       // label2.setPadding(new Insets(5,5,5,5));
        productGridPane.add(productView2,1,0);
        
         VBox productView3 = productView(Produit.MAKLOUB);
      //  Label label3 = new Label(Produit.BURGER.name());
      //  label3.setPadding(new Insets(5,5,5,5));
        productGridPane.add(productView3,2,0);
        
         VBox productView4 = productView(Produit.KOUSKOUS);
       // Label label4 = new Label(Produit.KOUSKOUS.name());
      //  label4.setPadding(new Insets(5,5,5,5));
        productGridPane.add(productView4,0,1);
        
    }    
    
   private VBox productView(Produit produit) throws FileNotFoundException{
       VBox layout = new VBox();
       layout.setAlignment(Pos.CENTER);
       FileInputStream input = new FileInputStream("C:\\Users\\Hazem\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\images\\"+produit.getImage());
       Image image = new Image(input);
       ImageView imageView = new ImageView (image);
       imageView.setFitWidth(100);
       imageView.setFitHeight(100);
        
        Label productName= new Label(produit.name());
        Label prix= new Label(Float.toString(produit.getPrix())+" DT");
        
        Button addButton=new Button("Add panier");
        addButton.setUserData(produit.name());
        addButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               // ajouter produit to panier
               Node sourceComponent = (Node)event.getSource();
               String productName = (String)sourceComponent.getUserData();
               ShoppingCart sc = ShoppingCart.getInstance();
               sc.addProduct(productName);
           }
       });
            
        layout.getChildren().addAll(imageView,productName,prix,addButton);  // n7ottou fehom ala layout 
        
        return layout;
       
       
   }

    
}
