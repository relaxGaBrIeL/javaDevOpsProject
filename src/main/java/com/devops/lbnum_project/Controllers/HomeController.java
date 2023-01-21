package com.devops.lbnum_project.Controllers;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Handler;

public class HomeController implements Initializable {


    public Text memoire_form;

    public Pane content_pane;
    public Button accueil_btn;
    public Button travail_btn;
    public Button groupe_btn;
    public Button calendrier_btn;
    public Button dossier_btn;


    public void displayPage(String buttonName) throws IOException {
        //en fonction du nom du bouton sur lequel on a cliqué, la fonction va affiché la page correspondante

        //Pane newPane = FXMLLoader.load(Objects.requireNonNull(HomeController.class.getResource("/Fxml/Pages/" + buttonName + ".fxml")));
        Pane newPane = FXMLLoader.load(Objects.requireNonNull(HomeController.class.getResource("/Fxml/Pages/Login.fxml")));
        content_pane.getChildren().setAll(newPane);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accueil_btn.setOnAction(event -> {
            try {
                displayPage("accueil");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //autres boutons
    }
}
