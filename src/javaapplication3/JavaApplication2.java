///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package javaapplication3;
//
//import controllers.EventUpdateController;
//import javafx.application.Application;
//import static javafx.application.Application.launch;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import entities.Event;
//import services.EventService;
//
///**
// *
// * @author user01
// */
//public class JavaApplication2 extends Application {
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        EventService es = new EventService();
//        Event e = es.recuperer(13);
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/EventUpdate.fxml"));
//        Parent root = fxmlLoader.load();
//        EventUpdateController eventUpdateContoller = (EventUpdateController) fxmlLoader.getController();
//        eventUpdateContoller.loadData(e);
//        Scene scene = new Scene(root);
//        stage.setTitle("Events List");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
