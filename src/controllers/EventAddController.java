/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.EventService;
import entities.Event;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user01
 */
public class EventAddController implements Initializable {

    @FXML
    TextField name;
    @FXML
    Label label_name;

    @FXML
    TextArea description;
    @FXML
    Label label_description;

    @FXML
    TextField price;
    @FXML
    Label label_price;

    @FXML
    TextField max_reservations;
    @FXML
    Label label_maxReservations;

    @FXML
    ComboBox restaurant;
    @FXML
    Label label_restaurant;

    @FXML
    DatePicker date;
    @FXML
    Label label_date;

    @FXML
    Button btn_add;
    EventsListController eventListcontroller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(name.getText());
    }

    public void loadData(EventsListController eventListcontroller) {
        this.eventListcontroller = eventListcontroller;
    }

    public void onSubmit(ActionEvent event) {
        Event e = new Event(-1);
        EventService es = new EventService();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Event");
        alert.setHeaderText("Are you sure want to add this event?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            System.out.println("No selection!");
        } else if (option.get() == ButtonType.OK) {
            e.setName(name.getText());
            e.setDescription(description.getText());
            e.setDate(Date.valueOf(date.getValue()));
            e.setTime(new Time(10, 00, 00));
            e.setRestaurant(1);
            e.setImage("image");
            e.setMaxReservations(Integer.parseInt(max_reservations.getText()));
            e.setPrice(Integer.parseInt(price.getText()));
            e.setNbReservation(0);
            es.ajouter(e);
            eventListcontroller.refresh();
            Stage stage = (Stage) btn_add.getScene().getWindow();
            stage.close();

        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("event not updated!");
        } else {
            System.out.println("----");
        }

    }

}
