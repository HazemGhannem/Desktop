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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable {

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
    private void insertButton() {

        if ((titleField.getText().isEmpty())
                || (authorField.getText().toString().isEmpty()) || (yearField.getText().isEmpty()) || (pagesField.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insertion incorrect");
            alert.setHeaderText("Remplir tous les champs pour valide l'insertion");
            alert.setContentText("faild insert!");

            alert.showAndWait();
        } else {
            int ok = 0;

            Connection connection = DataSource.getInstance().getCnx();
            String query2 = "SELECT * FROM employer ";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query2);
                emplo books;
                while (rs.next()) {
                    if (rs.getString("tel").toString().equals(pagesField.getText().toString())) {
                        ok = 1;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (ok == 0) {
                String query = "INSERT INTO `employer` (`id`, `nom`, `prenom`, `age`, `tel`, `password`, `resto_id`) VALUES (NULL, '" + titleField.getText() + "', '" + authorField.getText() + "', " + yearField.getText() + ", " + pagesField.getText() + ", '', '2')";
                executeQuery(query);
                showBooks();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insertion incorrect!!");
                alert.setHeaderText("Attention numero telephone est deja existe");
                alert.setContentText("faild insert!");

                alert.showAndWait();
            }

        }
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
                String query = "DELETE FROM employer WHERE id=" + String.valueOf(id) + "";
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
        showBooks();
    }

    public ObservableList<emplo> getBooksList() {
        ObservableList<emplo> booksList = FXCollections.observableArrayList();
        Connection connection = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM employer ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            emplo books;
            while (rs.next()) {
                books = new emplo(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("age"), rs.getString("tel"));
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
    private void bak(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/Gui/Accueil.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
