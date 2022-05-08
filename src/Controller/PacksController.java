package Controller;

import entities.Pack;

import gui.PackrestoController;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import services.PackService;
import test.MyListener;

import test.NewFXMain;

public class PacksController implements Initializable{

    @FXML
    private TextField search;
     
      @FXML
    private VBox chosenpack;

    @FXML
    private Label TFType;

    @FXML
    private Label TFPrix;

    @FXML
    private Label TFDescription;

    @FXML
    private Button BTNAcheter;


    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    
    private List<Pack> listArt = new ArrayList<>();
    private MyListener myListener;
    Pack pack = new Pack();
    @FXML
    private HBox articleNameLable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             showScreen();
    }   
    private void setChosenPack(Pack pack) {
        TFType.setText(pack.getType());
        TFPrix.setText(String.valueOf(pack.getPrix()));
        TFDescription.setText(pack.getDescription());
        chosenpack.setStyle("-fx-background-radius: 30;");
    }

    @FXML
    void searchButton(ActionEvent event) {

    }
     public void showScreen() {
        PackService packService = new PackService();
        listArt.addAll(packService.recuperer());

        if (listArt.size() > 0) {
            setChosenPack(listArt.get(0));
            System.out.println(listArt.get(0).getType());
            myListener = new MyListener() {
              

               

                @Override
                public void onClickListener(Pack nomp) {
                     setChosenPack(nomp);
                    pack.setId(nomp.getId());
                    pack.setType(nomp.getType());
                    pack.setPrix(nomp.getPrix());
                    pack.setDescription(nomp.getDescription());
                    System.out.println(pack);
                }

               

            };
        }
                    
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/packrestoFXML.fxml"));
                AnchorPane abc = fxmlLoader.load();
                PackrestoController packController = fxmlLoader.getController();
                packController.setData(listArt.get(i), myListener);

                if (column == 2) {
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
