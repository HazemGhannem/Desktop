/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Models.Event;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.EventService;

/**
 * FXML Controller class
 *
 * @author user01
 */
public class EventUpdateController implements Initializable {

    Event e;

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
    Button btn_update;
    @FXML
    EventsListController evtsController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void loadData(Event e, EventsListController evtsController) {
        this.evtsController = evtsController;
        this.e = e;
        name.setText(e.getName());
        description.setText(e.getDescription());
        price.setText(String.valueOf(e.getPrice()));
        max_reservations.setText(String.valueOf(e.getMaxReservations()));
        date.setValue(LocalDate.parse(e.getDate().toString()));

    }

    public void onDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Delete");
        alert.setHeaderText("Are you sure want to delete this event?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {
            System.out.println("No selection!");
        } else if (option.get() == ButtonType.OK) {
            EventService es = new EventService();
            es.supprimer(e.getId());
            evtsController.refresh();

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("event not updated!");
        } else {
            System.out.println("----");
        }

    }

    public void onUpdate(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Event");
        alert.setHeaderText("Are you sure want to update this event?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            System.out.println("No selection!");
        } else if (option.get() == ButtonType.OK) {
            EventService es = new EventService();
            e.setName(name.getText());
            e.setDescription(description.getText());
            e.setDate(Date.valueOf(date.getValue()));
            e.setTime(new Time(10, 00, 00));
            e.setRestaurant(1);
            e.setImage("image");
            e.setMaxReservations(Integer.parseInt(max_reservations.getText()));
            e.setPrice(Float.parseFloat(price.getText()));
            e.setNbReservation(0);
            es.modifier(e);
            evtsController.refresh();

        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("event not updated!");
        } else {
            System.out.println("----");
        }
    }

}
