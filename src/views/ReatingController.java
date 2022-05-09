/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Controller.MarketController;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseDragEvent;
import Utils.DataSource;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReatingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private RadioButton gr1;

    @FXML
    private RadioButton gr2;

    @FXML
    private RadioButton gr3;

    @FXML
    private RadioButton gr4;

    @FXML
    private RadioButton gr5;

    @FXML
    private ToggleGroup grating;

    @FXML
    private TextArea idc;
    @FXML
    private ListView<String> listcom;

    String notte;

    @FXML
    private Label notreat;

    @FXML
    void clickbtn(ActionEvent event) {

        calcul();
        if (gr1.isSelected()) {
            notte = "1";
        } else if (gr2.isSelected()) {
            notte = "2";
        } else if (gr3.isSelected()) {
            notte = "3";
        } else if (gr4.isSelected()) {
            notte = "4";
        } else if (gr5.isSelected()) {
            notte = "5";
        }

        System.out.println("hey");

        Connection connection = DataSource.getInstance().getCnx();
        String query = "INSERT INTO `commentaire` (`id`, `utilisateur_id`, `restaurant_id`, `contenu`, `date`, `note`) VALUES (NULL, 1,'" + MarketController.connectedOffre2 + "' , '" + idc.getText().toString() + "', NULL, '" + notte.toString() + "')";
        executeQuery(query);

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

    int note = 0;
    int cmpt = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        calcul();

    }

    public void calcul() {
        listcom.getItems().clear();
        Connection connection = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM commentaire where restaurant_id=" + MarketController.connectedOffre2;
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {

                listcom.getItems().add("Comnt: " + rs.getString("contenu") + " -  Note:" + rs.getString("note"));
                cmpt++;
                if (rs.getString("note").toString().equals("1")) {
                    note = note + 1;
                }
                if (rs.getString("note").toString().equals("2")) {
                    note = note + 2;
                }
                if (rs.getString("note").toString().equals("3")) {
                    note = note + 3;
                }
                if (rs.getString("note").toString().equals("4")) {
                    note = note + 4;
                }
                if (rs.getString("note").toString().equals("5")) {
                    note = note + 5;
                }

            }

            if (cmpt != 0) {
                System.out.println(note / cmpt);
                notreat.setText(String.valueOf(note / cmpt));
            } else {
                notreat.setText("0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void bck(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/Gui/Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
