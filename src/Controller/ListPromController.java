/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;


import entities.Pack;
import entities.Promotion;
import gui.PromotionController;
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
import services.PromotionService;
import test.MyListener1;
import test.NewFXMain;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ListPromController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private VBox chosenpromotion;
    @FXML
    private ImageView articleImg;
    @FXML
    private HBox articleNameLable;
   
    @FXML
    private Button but;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private Image image;
    private List<Promotion> listArt = new ArrayList<>();
    private MyListener1 myListener1;
    Promotion promotion = new Promotion();
    @FXML
    private Label TFNom;
    @FXML
    private Label TFPrixpromotion;
    @FXML
    private Label TFDatefin;
    @FXML
    private ComboBox<String> idcombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             showScreen();
              idcombo.getItems().add("Par Nom");
        idcombo.getItems().add("Par Pourcentage");
        idcombo.getItems().add("Par Prix");
    }   
    private void setChosenPromotion(Promotion promotion) {
        articleImg.setImage(new Image("/image/" + promotion.getImage()));
                System.out.println("test");
        TFNom.setText(promotion.getNom());
        TFPrixpromotion.setText(NewFXMain.CURRENCY +promotion.getPrixpromotion());
        TFDatefin.setText( String.valueOf(promotion.getDatefin()));
        chosenpromotion.setStyle("-fx-background-radius: 30;");
    }

    @FXML
    private void searchButton(ActionEvent event) {
        listArt.clear();
        String marque= search.getText();
        PromotionService promotionService = new PromotionService();
        System.out.println("recherche" + promotionService.recherche(marque));
        List<Promotion> list=new ArrayList();
        for (Promotion c:promotionService.recuperer()){
            if (c.getNom().toUpperCase().contains(marque.toUpperCase())   ){
                list.add(c);
            }
        }
        listArt.addAll(list);


        if (listArt.size() > 0) {
            setChosenPromotion(listArt.get(0));
            myListener1 = new MyListener1() {
                public void onClickListener(Promotion nomp) {
                  setChosenPromotion(nomp);
                    promotion.setId(nomp.getId());
                    promotion.setNom(nomp.getNom());
                    promotion.setPourcentage(nomp.getPourcentage());
                    promotion.setDescription(nomp.getDescription());
                    promotion.setPrixoriginal(nomp.getPrixoriginal());
                    promotion.setPrixpromotion(nomp.getPrixpromotion());
                    promotion.setDatedebut(nomp.getDatedebut());
                    promotion.setDatefin(nomp.getDatefin());
                    promotion.setImage(nomp.getImage());
                    System.out.println(promotion);
                    

                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Promotion.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                PromotionController promotionController = fxmlLoader.getController();
                promotionController.setData(listArt.get(i), myListener1);

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
    public void showScreen() {
        PromotionService promotionService = new PromotionService();
        listArt.addAll(promotionService.recuperer());

        if (listArt.size() > 0) {
            setChosenPromotion(listArt.get(0));
            System.out.println(listArt.get(0).getNom());
            myListener1 = new MyListener1() {
                @Override
                public void onClickListener(Promotion nomp) {
                    setChosenPromotion(nomp);
                    promotion.setId(nomp.getId());
                    promotion.setNom(nomp.getNom());
                    promotion.setPourcentage(nomp.getPourcentage());
                    promotion.setDescription(nomp.getDescription());
                    promotion.setPrixoriginal(nomp.getPrixoriginal());
                    promotion.setPrixpromotion(nomp.getPrixpromotion());
                    promotion.setDatedebut(nomp.getDatedebut());
                    promotion.setDatefin(nomp.getDatefin());
                    promotion.setImage(nomp.getImage());
                    System.out.println(promotion);
                    

                }

              

            
            };
        }
                    
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Promotion.fxml"));
                AnchorPane abc = fxmlLoader.load();
                PromotionController platController = fxmlLoader.getController();
                platController.setData(listArt.get(i), myListener1);

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
    private void trierarticle(ActionEvent event) {
        
        listArt.clear();
        PromotionService promotionService = new PromotionService();

        if (idcombo.getValue().equals("Par Nom")){
            listArt.addAll(promotionService.TreePromotion());
        }
        else if (idcombo.getValue().equals("Par Pourcentage")){
            listArt.addAll(promotionService.Treepc());
            
        }
        else if (idcombo.getValue().equals("Par Prix")){
            listArt.addAll(promotionService.TreePrix());
            
        }
        
        if (listArt.size() > 0) {
            setChosenPromotion(listArt.get(0));
            System.out.println(listArt.get(0).getNom());
            myListener1 = new MyListener1() {
                @Override
                public void onClickListener(Promotion nomp) {
                    setChosenPromotion(nomp);
                    promotion.setId(nomp.getId());
                    promotion.setNom(nomp.getNom());
                    promotion.setPourcentage(nomp.getPourcentage());
                    promotion.setDescription(nomp.getDescription());
                    promotion.setPrixoriginal(nomp.getPrixoriginal());
                    promotion.setPrixpromotion(nomp.getPrixpromotion());
                    promotion.setDatedebut(nomp.getDatedebut());
                    promotion.setDatefin(nomp.getDatefin());
                    promotion.setImage(nomp.getImage());
                    System.out.println(promotion);
                    

                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Promotion.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                PromotionController promotionController = fxmlLoader.getController();
                promotionController.setData(listArt.get(i), myListener1);

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

}
    
    
