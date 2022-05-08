package gui;

import entities.Pack;
import test.MyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PackrestoController implements Initializable{

    @FXML
    private Label TFType;

    @FXML
    private Label TFDescription;

    @FXML
    private Label TFPrix;
   

    @FXML
    private Button BTNAcheter;
    private MyListener myListener;
    private List<Pack> listpack = new ArrayList<>();
     private Pack pack;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }

    public void setData(Pack pack, MyListener myListener) {
         
        this.myListener = myListener;
        TFType.setText(pack.getType());
        TFPrix.setText(""+pack.getPrix());
        TFDescription.setText(pack.getDescription());
        this.pack = pack;
    }
     @FXML
    private void details(MouseEvent event) {
        myListener.onClickListener(pack);
    }
   

}
