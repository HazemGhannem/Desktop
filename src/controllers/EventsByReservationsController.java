/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import entities.*;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author user01
 */
public class EventsByReservationsController implements Initializable {

    @FXML
    BarChart barChart;
    @FXML
    CategoryAxis events;
    @FXML
    NumberAxis nb_reservations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loadData(List<Event> l) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < l.size(); i++) {
            series.getData().add(new XYChart.Data<>(l.get(i).getName(), l.get(i).getReservations().size()));
        }
        barChart.getData().addAll(series);

    }

}
