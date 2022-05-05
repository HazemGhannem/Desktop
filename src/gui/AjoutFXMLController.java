/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Plat;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.CategorieService;
import services.EnvoyerMail;
import services.PlatService;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AjoutFXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private Button non;
    @FXML
    private ImageView img;
    @FXML
    private Button back;
    @FXML
    private ComboBox<String> combCat;
    CategorieService servCat;
    File selectedImage;
    @FXML
    private TextField immg;
    File file;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servCat = new CategorieService();
        this.img = new ImageView("file:/assets/image/non.png");
        ObservableList<String> catNames = FXCollections
                .observableArrayList(
                        servCat.recuperer().stream().map(c -> c.getNom()).collect(Collectors.toList())
                );
        
        System.out.println(catNames);

        combCat.setItems(catNames);
    }

   
    @FXML
    private void Ajouter(ActionEvent event) throws Exception {
        final int idcategorie = servCat.getIdByCategoryName(combCat.getValue());

        Plat p = new Plat();
        if (((nom.getText().length() != 0) && (isAlpha(nom.getText()))) && ((description.getText().length() != 0))
                && (prix.getText().length() != 0 && (isNumeric(prix.getText())))){
        FileInputStream fl = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            String path = fileName;
            fl.read(data);
            fl.close();
        p.setNom(nom.getText());
        p.setImg(path);
        p.setDesc(description.getText());
        p.setCategorie_id(idcategorie);
        
        p.setPrix(Double.parseDouble(prix.getText()));
            System.out.println(p);
        
        PlatService ps = new PlatService();
        ps.ajouter(p);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Plat ajoutée");
        a.show();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            non.getScene().setRoot(root);
            SystemTray tray = SystemTray.getSystemTray();
             //If the icon is a file
        java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
       
        trayIcon.setImageAutoSize(true);
       
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Nouveau Plat 🍝 🍜",""+ nom.getText() + "  débarque ? 🥘 sur FlyFood", TrayIcon.MessageType.INFO);
            EnvoyerMail.sendMail("slaheddine.damergi@esprit.tn", "  "+ nom.getText() + "  débarque 🥗 🥘 sur FlyFood"+ "\n composé de " + description.getText());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Invalid fields!");
            alert.show();
        }
    }

    @FXML
    private void Afficher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherFXML.fxml"));
            non.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void accueil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @FXML
    private File chooseImage(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\Users\\pc\\Desktop\\3A47\\src\\image";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
         System.out.println(file.getPath());
        return file;

    }

}

