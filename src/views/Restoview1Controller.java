/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Controller.MarketController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.emplo;
import Models.MyListener;
import Utils.DataSource;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Restoview1Controller implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    String idrsto;
    int aff = 0;
    @FXML
    private Button addcomnt;

    @FXML
    private WebView mapview;

    @FXML
    private ImageView imgqrcode;
    @FXML
    private WebView infopage;

    @FXML
    private Label testlaba;
    @FXML
    private Label testlaba1;
    @FXML
    private Label local;
    @FXML
    private Label tel;
    String lat, lon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("heyyyyyy -- " + MarketController.connectedOffre2);

        /*  try{
      testlaba.setText("hey");
   }catch(Exception e)
   {
       System.out.println(e);
   }
         */
        ////////////INFO
        infopage.setVisible(false);

        ObservableList<emplo> booksList = FXCollections.observableArrayList();
        Connection connection = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM restaurant where id=" + MarketController.connectedOffre2;
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            emplo books;
            while (rs.next()) {

                //books = new emplo(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("age"),rs.getString("tel"));
                idrsto = (rs.getString("id").toString());
                testlaba.setText(rs.getString("specialitee").toString());
                testlaba1.setText(rs.getString("descripction").toString());
                local.setText(rs.getString("local").toString());
                tel.setText(rs.getString("tel").toString());
                lat = rs.getString("latitude").toString();
                lon = rs.getString("longitude").toString();

                System.out.println(rs.getString("specialitee").toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ///end info

        WebEngine webEngine = mapview.getEngine();

        /* Charge la carte HTML avec Leafletjs */
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
                + "                center: new google.maps.LatLng(" + lat + "," + lon + "),\n"
                + "                mapTypeId: google.maps.MapTypeId.ROADMAP\n"
                + "            });\n"
                + "            // creates a draggable marker to the given coords\n"
                + "            var vMarker = new google.maps.Marker({\n"
                + "                position: new google.maps.LatLng(" + lat + "," + lon + "),\n"
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
                + "    <div id=\"map_canvas\" style=\"width: auto; height: 345px;\">\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>");

        //////////qrcode
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "http://flyfood/resto/" + idrsto.toString();
        int width = 300;
        int height = 300;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            Logger.getLogger(Restoview1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageView qrView = new ImageView();
        imgqrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

    }

    @FXML
    void infobtn(ActionEvent event) {

        WebEngine webEngine = infopage.getEngine();

        /* Charge la carte HTML avec Leafletjs */
        webEngine.load("https://achref12.000webhostapp.com/chat.php");
        if (aff == 0) {
            infopage.setVisible(true);
            aff = 1;
        } else {
            aff = 0;
            infopage.setVisible(false);
        }
        System.out.println("hh)");
    }

    @FXML
    void evcommnt(ActionEvent event) {
        System.out.println("hey");
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("reating.fxml"));
            addcomnt.getScene().setRoot(parent);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void backtoo(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/Gui/Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
