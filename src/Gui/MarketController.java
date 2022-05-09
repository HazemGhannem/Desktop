/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import entities.Plat;
import Gui.plController;
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
import javafx.scene.control.ComboBox;
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
import Services.PlatService;
import javafixauth.MyListener;
import javafixauth.JavaFixAuth;
import javafx.scene.Parent;

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
    @FXML
    private ComboBox<String> idcombo;
    @FXML
    private Button profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             showScreen();
             idcombo.getItems().add("Par Nom");
        idcombo.getItems().add("Par Categorie");
        idcombo.getItems().add("Par Prix");
             
    }   
    private void setChosenPlat(Plat plat) {
//        System.out.println("test");
//        String path = "../Image/" + plat.getImg();
//                System.out.println(path);
//
//        Image image = new Image(getClass().getResourceAsStream(path));
//                System.out.println("test");

        articleImg.setImage(new Image("/Image/" + plat.getImg()));
                System.out.println("test");

        nom1.setText(plat.getNom());
        desc1.setText(plat.getDesc());
        prix1.setText(JavaFixAuth.CURRENCY + plat.getPrix());
        chosenplat.setStyle("-fx-background-radius: 30;");
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
//                    plat.setImg(nomp.getImg());
                    System.out.println("az get"+plat);
                    

                }

            };
        }
                    
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Gui/Plat.fxml"));
                AnchorPane abc = fxmlLoader.load();
                plController itemController = fxmlLoader.getController();
                itemController.setData(listArt.get(i), myListener);

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
    
    
    @FXML
    private void searchButton(ActionEvent event) {
        listArt.clear();
        String marque= search.getText();
        PlatService platService = new PlatService();
        System.out.println("recherche" + platService.rechercheCoach(marque));
        List<Plat> list=new ArrayList();
        for (Plat c:platService.recuperer()){
            if (c.getNom().toUpperCase().contains(marque.toUpperCase()) || c.getDesc().toUpperCase().contains(marque.toUpperCase())  ){
                list.add(c);
            }
        }
        listArt.addAll(list);


        if (listArt.size() > 0) {
            setChosenPlat(listArt.get(0));
            myListener = new MyListener() {
                public void onClickListener(Plat plat) {
                    setChosenPlat(plat);
                    plat.setId(plat.getId());
                    plat.setNom(plat.getNom());
                    plat.setDesc(plat.getDesc());
                    plat.setPrix(plat.getPrix());
//                    plat.setImg(plat.getImg());
                    

                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Gui/Plat.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                plController itemController = fxmlLoader.getController();
                itemController.setData(listArt.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void trierarticle(ActionEvent event) {
        listArt.clear();
        PlatService platService = new PlatService();

        if (idcombo.getValue().equals("Par Nom")){
            listArt.addAll(platService.TreePlat());
        }
        else if (idcombo.getValue().equals("Par Categorie")){
            listArt.addAll(platService.Treepd());
            
        }
        else if (idcombo.getValue().equals("Par Prix")){
            listArt.addAll(platService.TreePrix());
            
        }
        
        if (listArt.size() > 0) {
            setChosenPlat(listArt.get(0));
            myListener = new MyListener() {
                public void onClickListener(Plat plat) {
                     setChosenPlat(plat);
                    plat.setId(plat.getId());
                    plat.setNom(plat.getNom());
                    plat.setDesc(plat.getDesc());
                    plat.setPrix(plat.getPrix());

                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Gui/Plat.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                plController itemController = fxmlLoader.getController();
                itemController.setData(listArt.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void refreshButton(ActionEvent event) {
         listArt.clear();
        showScreen();
    }

    @FXML
    private void profile(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            profile.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
    
    
