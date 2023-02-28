package com.devops.lbnum_project;

import com.devops.lbnum_project.services.authentication.LoginController;
import com.devops.lbnum_project.Views.ViewFactory;
import com.devops.lbnum_project.services.client.User;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ServicesController implements Initializable {
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
    public Button btn_profil;
    public Label username;


    public void logout(ActionEvent event) throws IOException {

        //TODO : mettre à jour le statut de l'utilisateur
        ViewFactory.setPage("Welcome", event);
        LoginController.getClient().disconnectedServer();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ViewFactory.setScene(app_content, "dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        username.setText(User.getFname());

        btn_profil.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/profil.png")),75.0,75.0,true,true)));

        accueil_btn.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/home.png")),75.0,75.0,true,true)));

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

        groupe_btn.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/message.png")),75.0,75.0,true,true)));

        groupe_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "group");
                header_name.setText("Message");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //Not Implemented
        calendrier_btn.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/calendar.png")),75.0,75.0,true,true)));
        calendrier_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "calendar");
                header_name.setText("task");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        dossier_btn.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/work.png")),75.0,75.0,true,true)));
        dossier_btn.setOnAction(event -> {
            try {
                ViewFactory.setScene(app_content, "directory");
                header_name.setText("Directory");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
