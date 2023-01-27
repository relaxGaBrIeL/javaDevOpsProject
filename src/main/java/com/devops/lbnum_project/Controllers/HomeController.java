package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Views.ViewFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    public Text memoire_form;

    public Pane content_pane;
    public Button accueil_btn;
    public Button travail_btn;
    public Button groupe_btn;
    public Button calendrier_btn;
    public Button dossier_btn;
    public AnchorPane app_content;
    public Button login_button;
    public Button sign_button;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //Les scene
        accueil_btn.setOnAction(actionEvent -> {
            try {
                ViewFactory.getScene(content_pane,"Login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }
}
