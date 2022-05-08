/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import entities.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.EventService;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author user01
 */
public class EventDetailsController implements Initializable {

    @FXML
    ImageView img_event;
    @FXML
    Label label_name;

    @FXML
    Label label_description;

    @FXML
    Label label_total;

    @FXML
    TableView tableview_reservations;

    @FXML
    TableColumn tableview_user;

    @FXML
    TableColumn tableview_code;

    @FXML
    TableColumn tableview_isPaid;

    List<Reservation> reservationsList;
    ObservableList<Reservation> reservationsObs = FXCollections.observableArrayList();
    Event event;
    EventService es;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        es = new EventService();

    }

    public void loadData(Event e) {
        Image image = new Image("http://127.0.0.1:8000/upload/images/evennement/" + e.getImage());
        event = e;
        label_name.setText(e.getName());
        label_description.setText(e.getDescription());
        img_event.setImage(image);
        reservationsList = e.getReservations();
        label_total.setText("(Tot: " + reservationsList.size() + " )");

        TableColumn<Reservation, Void> actionsCol = new TableColumn("Actions");

        Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>> cellFactory = new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
            @Override
            public TableCell<Reservation, Void> call(final TableColumn<Reservation, Void> param) {
                final TableCell<Reservation, Void> cell = new TableCell<Reservation, Void>() {
                    Button btn = new Button("Make as paid");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Reservation r = getTableView().getItems().get(getIndex());
                            r.setIsPaid(true);
                            ReservationService rs = new ReservationService();
                            rs.modifier(r);
                            refresh();

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);

                        }
                    }
                };
                return cell;
            }
        };

        actionsCol.setCellFactory(cellFactory);

        tableview_reservations.getColumns().add(actionsCol);

        for (int i = 0; i < reservationsList.size(); i++) {
            reservationsObs.add(reservationsList.get(i));
        }

        tableview_user.setCellValueFactory(
                new PropertyValueFactory<Reservation, String>("email")
        );

        tableview_code.setCellValueFactory(
                new PropertyValueFactory<Reservation, String>("code")
        );

        tableview_isPaid.setCellValueFactory(
                new PropertyValueFactory<Reservation, Integer>("isPaid")
        );

        tableview_reservations.setItems(reservationsObs);

    }

    public void refresh() {
        reservationsObs.clear();
        Event event2 = es.recuperer(event.getId());
        List<Reservation> reservationsList2 = event2.getReservations();

        for (int i = 0; i < reservationsList2.size(); i++) {
            reservationsObs.add(reservationsList2.get(i));
        }
        tableview_reservations.setItems(reservationsObs);

    }

}
