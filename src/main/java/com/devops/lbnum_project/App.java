package com.devops.lbnum_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/devops/lbnum_project/Fxml/Pages/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,700);
        stage.setTitle("Le bureau Numérique");
        stage.setScene(scene);
        stage.setMinHeight(500);        //  Val min et max des dimensions de la fenêtre
        stage.setMinWidth(650);
        stage.show();
    }



}
