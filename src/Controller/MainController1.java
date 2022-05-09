package Controller;

import Utils.DataSource;
import java.io.IOException;
import model.emplo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainController1 implements Initializable {

    int id;
    ResultSet rs2;
    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField pagesField;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<emplo> TableView;

    @FXML
    private TableColumn<emplo, Integer> idColumn;

    @FXML
    private TableColumn<emplo, String> titleColumn;

    @FXML
    private TableColumn<emplo, String> authorColumn;

    @FXML
    private TableColumn<emplo, String> yearColumn;

    @FXML
    private TableColumn<emplo, Integer> pagesColumn;
    @FXML
    private TextArea descrr;
    @FXML
    private TextField nomrr;
    @FXML
    private TextArea sprr;

    @FXML
    private TextField telrr;

    @FXML
    private WebView infopage;

    @FXML
    private TextField lat;

    @FXML
    private TextField lon;

    String nlat = "2";
    String nlon = "2";

    @FXML
    private Pane addview;

    @FXML
    private void insertButton() {
        addview.setVisible(true);

    }

    @FXML
    private void insertButton2() {

        int ok = 0;

        Connection connection = DataSource.getInstance().getCnx();
        String query2 = "SELECT * FROM employer ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query2);
            emplo books;

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ok == 0) {

            String query = "INSERT INTO `restaurant` (`id`, `ville_id`, `proprietaire_id`, `nom`, `specialitee`, `note`, `verif`, `latitude`, `longitude`, `tel`, `descripction`, `local`) VALUES (NULL, '1', '1', '" + nomrr.getText() + "',  '" + sprr.getText() + "', '0', '0', '1', '1', '" + telrr.getText() + "', '" + descrr.getText() + "', 'mateur')";

            /*	String query = "INSERT INTO `employer` (`id`, `nom`, `prenom`, `age`, `tel`, `password`, `resto_id`) VALUES "
                + "(NULL, '"+titleField.getText()+"', '"+authorField.getText()+"', "+yearField.getText()+", "+pagesField.getText()+", '', '2')";*/
            executeQuery(query);
            showBooks();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insertion incorrect!!");
            alert.setHeaderText("Attention numero telephone est deja existe");
            alert.setContentText("faild insert!");

            alert.showAndWait();
        }

        addview.setVisible(false);
    }

    @FXML
    private void updateButton() {
        String query = "UPDATE employer SET nom='" + titleField.getText() + "',prenom='" + authorField.getText() + "',age=" + yearField.getText() + ",tel=" + pagesField.getText() + " WHERE id=" + String.valueOf(id) + "";
        executeQuery(query);
        showBooks();
    }

    @FXML
    private void deleteButton() {
        if (idField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("suppression incorrect!!");
            alert.setHeaderText("selection employer");
            alert.setContentText("faild suppresion!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete File");
            alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
            alert.setContentText("C:/MyFile.txt");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                String query = "DELETE FROM restaurant WHERE id=" + String.valueOf(id) + "";
                executeQuery(query);
                showBooks();
            }

        }

    }

    public void executeQuery(String query) {
        Connection conn = DataSource.getInstance().getCnx();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addview.setVisible(false);
        map();
        showBooks();
    }

    @FXML
    void closev(ActionEvent event) {
        addview.setVisible(false);
    }

    public ObservableList<emplo> getBooksList() {
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
                books = new emplo(rs.getInt("id"), rs.getString("nom"), rs.getString("specialitee"), rs.getString("descripction"), rs.getString("tel"));
                booksList.add(books);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return booksList;
    }
    int myIndex;

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showBooks() {
        ObservableList<emplo> list = getBooksList();
        idField.setEditable(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<emplo, Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<emplo, String>("nom"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<emplo, String>("prenom"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<emplo, String>("age"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<emplo, Integer>("tel"));

        TableView.setItems(list);

        TableView.setRowFactory(tv -> {
            TableRow<emplo> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = TableView.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(TableView.getItems().get(myIndex).getId()));
                    idField.setText(String.valueOf(id));
                    titleField.setText(TableView.getItems().get(myIndex).getNom());
                    authorField.setText(TableView.getItems().get(myIndex).getPrenom());
                    yearField.setText(TableView.getItems().get(myIndex).getAge());
                    pagesField.setText(TableView.getItems().get(myIndex).getTel());

                }
            });
            return myRow;
        });

    }

    @FXML
    void findl(KeyEvent event) {

        nlat = lat.getText().toString();
        nlon = lon.getText().toString();
        map();

    }

    @FXML
    void findlo(KeyEvent event) {
        nlat = lat.getText().toString();
        nlon = lon.getText().toString();
        map();
    }

    public void map() {
        WebEngine webEngine = infopage.getEngine();

        webEngine.loadContent("<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <title>Google Maps Drag Marker Get Coordinates</title>\n"
                + "    <script src=\"https://code.jquery.com/jquery-3.5.1.min.js\" type=\"text/javascript\"></script>\n"
                + "    <script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBvHg2E3bOHns4yCQJ4ogiFR9wllEg4Z0E\"></script>\n"
                + "    <script type=\"text/javascript\">\n"
                + "        function initialize() {\n"
                + "            // Creating map object\n"
                + "            var map = new google.maps.Map(document.getElementById('map_canvas'), {\n"
                + "                zoom: 12,\n"
                + "                center: new google.maps.LatLng(" + nlat + "," + nlon + "),\n"
                + "                mapTypeId: google.maps.MapTypeId.ROADMAP\n"
                + "            });\n"
                + "            // creates a draggable marker to the given coords\n"
                + "            var vMarker = new google.maps.Marker({\n"
                + "                position: new google.maps.LatLng(" + nlat + "," + nlon + "),\n"
                + "                draggable: true\n"
                + "            });\n"
                + "            // adds a listener to the marker\n"
                + "            // gets the coords when drag event ends\n"
                + "            // then updates the input with the new coords\n"
                + "           \n"
                + "            // centers the map on markers coords\n"
                + "            map.setCenter(vMarker.position);\n"
                + "            // adds the marker on the map\n"
                + "            vMarker.setMap(map);\n"
                + "        }\n"
                + "        \n"
                + "    </script>\n"
                + "</head>\n"
                + "<body onload=\"initialize();\">\n"
                + "   \n"
                + "    <div id=\"map_canvas\" style=\"width: auto; height:250px;\">\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>");

    }

    @FXML
    private void bak(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/Gui/Accueil.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
