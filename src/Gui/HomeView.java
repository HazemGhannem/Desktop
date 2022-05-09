/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



/**
 *
 * @author alaed
 */
public class HomeView {
    
private Parent view;


    public HomeView() throws IOException {
        
        // to change with pc 
        
        URL url =  new File("C:\\Users\\Hazem\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\Gui\\HomeView.fxml").toURI().toURL();
       Parent root =  FXMLLoader.load(url);
       
       this.view=root;
    }
    
    public Parent getView(){
    
    return view;
    }
    
}
