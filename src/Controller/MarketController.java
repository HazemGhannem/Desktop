package Controller;

import Models.MyListener;
import Utils.DataSource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafixauth.JavaFixAuth;
import model.Fruit;
import model.Fruit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.emplo;

public class MarketController implements Initializable {

    public static String connectedOffre2;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Button btSearch;

    @FXML
    private Button backll;

    private List<Fruit> fruits = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    @FXML
    void btnsearch(ActionEvent event) {

    }

    @FXML
    private TextField searchrest;

    /*
@FXML
    void searchact(KeyEvent event) {
        System.out.println(searchrest.getText().toString());
      /*   grid.getChildren().clear();
        fruits.clear();
        marketshow();
    } */
    @FXML
    void searchact2(KeyEvent event) {
        System.out.println(searchrest.getText().toString());
        grid.getChildren().clear();
        fruits.clear();
        marketshow();
    }

    private List<Fruit> getData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;

        ObservableList<emplo> booksList = FXCollections.observableArrayList();
        Connection connection = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM restaurant ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            emplo books;
            while (rs.next()) {
                fruit = new Fruit();
                //books = new emplo(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("age"),rs.getString("tel"));
                fruit.setId(rs.getString("id").toString());
                fruit.setName(rs.getString("nom").toString());
                fruit.setImgSrc("/assest/restaurant-5841.jpg");
                fruit.setColor("6A7324");
                if (searchrest.getText().isEmpty()) {
                    fruits.add(fruit);
                } else if (rs.getString("nom").toString().toUpperCase().indexOf(searchrest.getText().toUpperCase()) > -1) {
                    fruits.add(fruit);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /* fruit = new Fruit();
        fruit.setName("Xfood");
        fruit.setImgSrc("/assest/restaurant-5841.jpg");
        fruit.setColor("1b7329");
        fruits.add(fruit);*/
        return fruits;
    }

    private void setChosenFruit(Fruit fruit) {
        fruitNameLable.setText(fruit.getName());
        //fruitPriceLabel.setText(Main.CURRENCY + fruit.getPrice());
        MarketController.connectedOffre2 = fruit.getId();
        System.out.println(MarketController.connectedOffre2);
        image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n"
                + "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fruits.clear();
        marketshow();

    }
    @FXML
    private Button shob;

    @FXML
    void showrestp(ActionEvent event) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("/views/restoview.fxml"));
            shob.getScene().setRoot(parent);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void marketshow() {
        fruits.clear();
        fruits.addAll(getData());
        if (fruits.size() > 0) {
            setChosenFruit(fruits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i), myListener);

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
    private void backl(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/Gui/Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
