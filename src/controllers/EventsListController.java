/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.EventService;
import entities.Event;
import java.awt.Container;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import services.EmailService;

/**
 * FXML Controller class
 *
 * @author user01
 */
public class EventsListController implements Initializable {

    private Stage stage;
    private Scene scene;
    private EventsListController self = this;
    @FXML
    private TableView<Event> eventsTableView;

    @FXML
    private TableColumn<Event, String> name;

    @FXML
    private TableColumn<Event, String> restaurant;

    @FXML
    private TableColumn<Event, Date> date;

    @FXML
    private TableColumn<Event, Time> time;

    @FXML
    private TableColumn<Event, Integer> reservation_max;

    @FXML
    private TableColumn<Event, Integer> reservation_nb;

    @FXML
    private TableColumn<Event, Integer> av_places;

    @FXML
    private TableColumn<Event, Integer> price;

    @FXML
    private TableColumn<Event, Integer> actions;

    @FXML
    private TextField input_search;

    @FXML
    private Button btn_add;

    EventService es = new EventService();
    List<Event> eventsList;
    ObservableList<Event> eventsObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Event> eventsList = es.recuperer();
        for (int i = 0; i < eventsList.size(); i++) {
            eventsObs.add(eventsList.get(i));
        }

        TableColumn<Event, Void> actionsCol = new TableColumn("Actions");
        Callback<TableColumn<Event, Void>, TableCell<Event, Void>> cellFactory = new Callback<TableColumn<Event, Void>, TableCell<Event, Void>>() {
            @Override
            public TableCell<Event, Void> call(final TableColumn<Event, Void> param) {
                final TableCell<Event, Void> cell = new TableCell<Event, Void>() {

                    Button btn_view = new Button("View");
                    Button btn_edit = new Button("Edit");
                    Button btn_cancel = new Button("Cancel");

                    private HBox pane = new HBox(5, btn_view, btn_edit, btn_cancel);

                    {
                        btn_view.setOnAction((ActionEvent event) -> {

                            Stage stage1 = new Stage();
                            Event e = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/EventDetails.fxml"));
                                Parent root2 = fxmlLoader.load();
                                EventDetailsController eventDetailsContoller = (EventDetailsController) fxmlLoader.getController();
                                eventDetailsContoller.loadData(e);
                                Scene scene1 = new Scene(root2);
                                stage1.setTitle("Event Details");
                                stage1.setScene(scene1);
                                stage1.show();
                            } catch (IOException ex) {
                                Logger.getLogger(EventsListController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        btn_edit.setOnAction((ActionEvent event) -> {
                            Event e = getTableView().getItems().get(getIndex());
                            Stage stage1 = new Stage();
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/EventUpdate.fxml"));
                                Parent root2 = fxmlLoader.load();
                                Scene scene1 = new Scene(root2);
                                EventUpdateController eventUpdateContoller = (EventUpdateController) fxmlLoader.getController();
                                eventUpdateContoller.loadData(e, self);

                                stage1.setTitle("Edit Event");
                                stage1.setScene(scene1);
                                stage1.show();

                            } catch (IOException ex) {
                                Logger.getLogger(EventsListController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        btn_cancel.setOnAction((ActionEvent event) -> {
                            Event e = getTableView().getItems().get(getIndex());

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Cancel Event");
                            alert.setHeaderText("Are you sure want to cancel this event?");
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == null) {
                                System.out.println("No selection!");
                            } else if (option.get() == ButtonType.OK) {
                                EventService es = new EventService();
                                e.setAnnule(true);
                                es.modifier(e);
                                EmailService.send("marwen.kouidhi@esprit.tn", "XDgnJ@?559", e);
                                refresh();

                                Alert info = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("An email sent to all participants. ");

                            } else if (option.get() == ButtonType.CANCEL) {
                                System.out.println("event not updated!");
                            } else {
                                System.out.println("----");
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);

                        }
                    }
                };
                return cell;
            }
        };
        actionsCol.setCellFactory(cellFactory);
        eventsTableView.getColumns().add(actionsCol);

        name.setCellValueFactory(
                new PropertyValueFactory<Event, String>("name")
        );

        restaurant.setCellValueFactory(
                new PropertyValueFactory<Event, String>("restaurant")
        );
        date.setCellValueFactory(
                new PropertyValueFactory<Event, Date>("date")
        );
        time.setCellValueFactory(
                new PropertyValueFactory<Event, Time>("time")
        );
        reservation_max.setCellValueFactory(
                new PropertyValueFactory<Event, Integer>("maxReservations")
        );
        reservation_nb.setCellValueFactory(
                new PropertyValueFactory<Event, Integer>("nbReservation")
        );

        price.setCellValueFactory(
                new PropertyValueFactory<Event, Integer>("price")
        );

        eventsTableView.setItems(eventsObs);

    }

    public void OnAddEvent(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/EventAdd.fxml"));
        Parent root = fxmlLoader.load();
        EventAddController eventUpdateContoller = (EventAddController) fxmlLoader.getController();
        eventUpdateContoller.loadData(self);

        Scene addScene = new Scene(root);
        Stage addWindow = new Stage();
        addWindow.setTitle("Add Event");
        addWindow.setScene(addScene);
        addWindow.show();
        System.out.println("add event clicked");
    }

    public void refresh() {
        eventsObs.clear();
        List<Event> eventsList = es.recuperer();
        for (int i = 0; i < eventsList.size(); i++) {
            eventsObs.add(eventsList.get(i));
        }
        eventsTableView.setItems(eventsObs);
    }

    public void showStats() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/EventsByReservations.fxml"));
        Parent root = fxmlLoader.load();
        EventsByReservationsController stats = (EventsByReservationsController) fxmlLoader.getController();
        stats.loadData(es.recuperer());
        Scene addScene = new Scene(root);
        Stage addWindow = new Stage();
        addWindow.setTitle("Stats");
        addWindow.setScene(addScene);
        addWindow.show();

    }

    public void switchToDetails(ActionEvent event) throws IOException {

    }
}
