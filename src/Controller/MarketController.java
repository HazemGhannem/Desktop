/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import entities.Plat;
import gui.ItemController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import services.PlatService;
import test.MyListener;
import test.NewFXMain;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class MarketController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private VBox chosenplat;
    @FXML
    private ImageView articleImg;
    @FXML
    private HBox articleNameLable;
    @FXML
    private Label nom1;
    @FXML
    private Label desc1;
    @FXML
    private Label prix1;
    @FXML
    private Button but;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private Image image;
    private List<Plat> listArt = new ArrayList<>();
    private MyListener myListener;
    Plat plat = new Plat();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             showScreen();
    }   
    private void setChosenPlat(Plat plat) {
        nom1.setText(plat.getNom());
        desc1.setText(plat.getDesc());
        prix1.setText(NewFXMain.CURRENCY + plat.getPrix());
        chosenplat.setStyle("-fx-background-radius: 30;");
    }

    @FXML
    private void searchButton(ActionEvent event) {
    }
    public void showScreen() {
        PlatService platService = new PlatService();
        listArt.addAll(platService.recuperer());

        if (listArt.size() > 0) {
            setChosenPlat(listArt.get(0));
            System.out.println(listArt.get(0).getNom());
            myListener = new MyListener() {
                @Override
                public void onClickListener(Plat nomp) {
                    setChosenPlat(nomp);
                    plat.setId(nomp.getId());
                    plat.setNom(nomp.getNom());
                    plat.setDesc(nomp.getDesc());
                    plat.setPrix(nomp.getPrix());
                    plat.setImg(nomp.getImg());
                    System.out.println("Coach get"+plat);
                    

                }

            };
        }
                    
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Plat.fxml"));
                AnchorPane abc = fxmlLoader.load();
                ItemController platController = fxmlLoader.getController();
                platController.setData(listArt.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(abc, column++, row); //(child,column,row)
                //set grid width

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(abc, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
    
    
