/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.Codes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.File;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class FXMLController implements Initializable {

    @FXML
    private Button a;
    @FXML
    private ImageView img;
    Random rn = new Random();
    int randomNumber =rn.nextInt(3)+1 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image im = new Image(this.getClass().getResourceAsStream("/captcha/"+""+randomNumber+".png"));
        img.setFitHeight(150);
        img.setFitWidth(150);
        img.setImage(im);
        
        //System.out.println(randomNumber);
    }

    @FXML
    private void a(ActionEvent event) {
        String s = "/captcha/"+""+randomNumber+".png";
        //System.out.println(randomNumber);
        Tesseract tesseract = new Tesseract();
        try {
           String path= "C:\\Users\\Hazem\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\tesseract";
            tesseract.setDatapath(path);

            // the path of your tess data folder
            // inside the extracted file
            String text = tesseract.doOCR(new File("C:\\Users\\Hazem\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\"+s));

            // path of your image file
            //System.out.print(text);
            for (int i = 0; i<4; i++) {
                System.out.println(text.charAt(i));
                
            }
            System.out.println(text.length());
            String d =text.replaceAll("\\s+","");
            System.out.println(d.length());
            //System.out.println("aze");
        } catch (TesseractException e) {
            System.out.println(e.getMessage());
        }
    }

}
