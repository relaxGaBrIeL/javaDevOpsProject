package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Models.DAOUser;
import com.devops.lbnum_project.Views.ViewFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    public Button accueil_btn;
    public Button travail_btn;
    public Button groupe_btn;
    public Button calendrier_btn;
    public Button dossier_btn;
    public Button notifs_btn;
    public Button logout_btn;
    public GridPane app_content;

    public FontAwesomeIconView profil;
    

    public void logout(ActionEvent event) throws IOException {

        //TODO : mettre à jour le statut de l'utilisateur
        ViewFactory.setPage("Login", event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        accueil_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "dashboard");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        travail_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "task");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        groupe_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "group");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        calendrier_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "calendar");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        dossier_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "directory");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
