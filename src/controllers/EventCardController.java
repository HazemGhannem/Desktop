/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import entities.Event;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author user01
 */
public class EventCardController implements Initializable {

    @FXML
    ImageView img;

    @FXML
    Label title;

    @FXML
    Label description;

    @FXML
    Button btn_details;

    @FXML
    Button btn_edit;

    @FXML
    Button btn_delete;

    public void setData(Event e) {
        Image image = new Image("http://127.0.0.1:8000/upload/images/evennement/" + e.getImage());
        this.title.setText(e.getName());
        this.description.setText(e.getDescription());
        this.img.setImage(image);

    }

    public void viewEvent() {

    }

    public void editEvent() {

    }

    public void deleteEvent() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setText("fjdlksfjklsd");
        description.setText("felmjflmdfjml");

    }

}
