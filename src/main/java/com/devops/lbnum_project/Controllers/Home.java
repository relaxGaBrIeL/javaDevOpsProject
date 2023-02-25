package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Controllers.services.FileExplore;
import com.devops.lbnum_project.Models.DAOUser;
import com.devops.lbnum_project.Views.ViewFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
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
    public Text header_name;


    public void logout(ActionEvent event) throws IOException {

        //TODO : mettre à jour le statut de l'utilisateur
        ViewFactory.setPage("Login", event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            ViewFactory.setScene(app_content, "dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        accueil_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "dashboard");
                header_name.setText("Accueil");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //Not Implemented
//        travail_btn.setOnAction(event -> {
//            try {
//                ViewFactory.setScene(app_content, "task");
//                header_name.setText("Tasks");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

        groupe_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "group");
                header_name.setText("Message");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //Not Implemented
//        calendrier_btn.setOnAction(event -> {
//            try {
//                ViewFactory.setScene(app_content, "calendar");
//                header_name.setText("Calendar");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        dossier_btn.setOnAction(event -> {
//            try {
//                ViewFactory.setScene(app_content, "directory");
//                header_name.setText("Directory");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

    }
}
