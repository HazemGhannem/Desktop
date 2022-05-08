/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaapplication3;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.EventService;

/**
 *
 * @author user01
 */
public class EventDetails extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        EventService es = new EventService();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/EventDetails.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Event details");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
