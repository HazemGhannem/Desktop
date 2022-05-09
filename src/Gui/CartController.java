/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Commande;
import Services.CartEntry;
import Services.CommandeService;
import Services.ShoppingCart;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author alaed
 */
public class CartController  {

   
    
    
    @FXML
    private VBox cartPane;
    
    private Label TotalPrice;
    
    private String CommandeTot;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() throws FileNotFoundException{
        // TODO
        
        List <CartEntry> entries = ShoppingCart.getInstance().getEntries();
        cartPane.getChildren().clear();
        
        
        if (entries.isEmpty()){
            Label emptyLabel = new Label("panier Vide");
            cartPane.getChildren().add(emptyLabel);
        }else {
            Label shoppingCartTitle = new Label("Panier");
            cartPane.getChildren().add(shoppingCartTitle);
             for (CartEntry cartEntry:entries){    // kimaa haka chekout normalement
                
                HBox productView =  cartEntryView(cartEntry);
                cartPane.getChildren().add(productView);
             }
             
             Separator separator = new Separator();
             separator.setOrientation(Orientation.HORIZONTAL);
             cartPane.getChildren().add(separator);
             
            HBox totalView =  totalView(ShoppingCart.getInstance().CalculateTotal());
            cartPane.getChildren().add(totalView);
            
             Button checkoutButton = new Button ("Commander");
             checkoutButton.setOnAction(e -> {
             this.Commander(e,this.CommandeTot);
         });
             cartPane.getChildren().add(checkoutButton);
             
             
             
        }        
    }  
    
    private HBox totalView(float totalPrice){
        
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        
        Label totalLabel = new Label ("Totale: ");
        totalLabel.setStyle("-fx-font-size-:15pt;");
        this.TotalPrice= new Label(String.valueOf(totalPrice));
       
        
        layout.getChildren().addAll(totalLabel,this.TotalPrice);
        
        
        
        return layout;
        
            }
    
    private HBox cartEntryView(CartEntry cartEntry) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);
      FileInputStream input = new FileInputStream("C:\\Users\\Hazem\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\images\\"+cartEntry.getProduit().getImage());
        
       Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        
        Label productName = new Label (cartEntry.getProduit().name());
        productName.setPrefWidth(100);
        productName.setStyle("-fx-font-size:15pt; -fx-padding:5px");
        
         Label quantity = new Label (String.valueOf(cartEntry.getQuantity()));
         quantity.setStyle("-fx-padding:5px");
         
         Button plusButton = new Button ("+");
         plusButton.setStyle("-fx-padding:5px");
         plusButton.setUserData(cartEntry.getProduit().name());
         plusButton.setOnAction(e -> {
             String name = (String) ((Node) e.getSource()).getUserData();
             ShoppingCart.getInstance().addProduct(name);
             quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
             this.TotalPrice.setText(String.valueOf(ShoppingCart.getInstance().CalculateTotal()));
             this.CommandeTot = ShoppingCart.getInstance().totalCommandeString();
         });
         
         Button minusButton = new Button ("-");
         minusButton.setStyle("-fx-padding:5px");
         minusButton.setUserData(cartEntry.getProduit().name());
         minusButton.setOnAction(e -> {
             String name = (String) ((Node) e.getSource()).getUserData();
             ShoppingCart.getInstance().removeProduct(name);
             quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
             this.TotalPrice.setText(String.valueOf(ShoppingCart.getInstance().CalculateTotal()));
             this.CommandeTot = ShoppingCart.getInstance().totalCommandeString();
             
         });
         
         Label price = new Label(String.valueOf(cartEntry.getProduit().getPrix()+" DT"));
         price.setStyle("-fx-padding:5px");
         
         layout.getChildren().addAll(imageView,productName,quantity,plusButton,minusButton,price);
         
        
        
        return layout;
    }
    
    
    
    
    
    
     @FXML
    private void Commander(ActionEvent even,String totalString) {
       
         Commande c = new Commande();
         CommandeService cs = new CommandeService();
         
         
        c.setNum(cs.recupererMax()+1);
        c.setDate_commande( new SimpleDateFormat("dd-MM-yyyy").format(new Date()));  
       //c.setDate_commande((tfDate.getText()));
        c.setEtat(totalString);
        
       
        cs.ajouter(c);
        //Alert a = new Alert(Alert.AlertType.CONFIRMATION);
       // a.setTitle("Commqnde ajoutée");
       // a.show();
       JOptionPane.showMessageDialog(null, "commande ajouté succes");
     
    
    }
    
}
