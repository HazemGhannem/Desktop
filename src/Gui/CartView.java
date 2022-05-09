/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author alaed
 */
public class CartView {
    Parent View ;
    public  CartView() throws  IOException {
    
    URL uiFile = new File("C:\\Users\\Hazem\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\Gui\\Cart.fxml").toURI().toURL();
    Parent root = FXMLLoader.load(uiFile);
    this.View=root;
}
    public Parent getView(){
        
        return this.View;
    }
   
}
