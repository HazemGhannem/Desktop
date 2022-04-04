/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafixauth;

import Models.User;
import Services.ServiceUser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Hazem
 */
public class JavaFixAuth extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        User d =new User("ay","+21627300252","hazem@e.e","haze123","image");
        User f =new User(4,"dude","+21627300252","sdfghj","haze123","image");
        ServiceUser serv = new ServiceUser();
        //serv.ajouter(d);
        //System.out.println(serv.getAll());
        //System.out.println(serv.getById(4));
        //serv.modifier(f);
        serv.supprimer(f);
        
        
    }
    
}
