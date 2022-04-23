/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.User;
import Services.Codes;
import Services.ServiceUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.ResourceBundle;
import static javafixauth.JavaFixAuth.RestpasswordID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author Hazem
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tftelephone;
    @FXML
    private TextField tfpasswordvisible;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnadd;
    @FXML
    private CheckBox check_af;
    @FXML
    private Label lab_email;
    @FXML
    private Label lab_cin;
    @FXML
    private PasswordField tfcpassword;
    private ServiceUser userService;
    @FXML
    private Button Pic;
    File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new ServiceUser();

    }

    @FXML
    private void btnadd(ActionEvent event) throws IOException {
        // hash password
        String password= DigestUtils.md5Hex(tfpassword.getText());
        
        FileInputStream fl = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            String image = fileName;
            fl.read(data);
            fl.close();
        User user = new User(tfusername.getText(), tftelephone.getText(), tfemail.getText(), password,image,false);
       // user.setRoles("[\"ROLE_ADMIN\"]");
       
        if (tfusername.getText().length() != 0 || tfemail.getText().length() != 0) {

            userService.ajouter(user);
            System.out.println(user);
            sendEmail();
            AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setWidth(600);
            stage.setHeight(400);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

        } else {
            System.out.println("wrong");
        }
    }

    @FXML
    private File Pic(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\Users\\Hazem\\Documents\\NetBeansProjects\\JavaFixAuth\\src\\Image";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();
               System.out.println(fileName);
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
    public  void sendEmail() {
        String to =tfemail.getText() ;
        String from = "hamatalbi9921@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "hamatalbi9921@gmail.com";
        final String password = "123456789hama";
        
        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(username, password);
            }
        });

        try{
            ServiceUser serviceuser = new ServiceUser();
            Codes code= new Codes();
            code.getUserBy(to);
            
           String verification=code.envoyerCode(RestpasswordID);
           code.codemail(verification, to);
          // code.update(verification, RestpasswordID);
            System.out.println(RestpasswordID);
            System.out.println(verification);
            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("Verifier your account");
            m.setText("To verifier youre account  entre this code:  "+verification);

           

            Transport.send(m);
            
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
//        } catch (SQLException ex) {
//            Logger.getLogger(RestPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
