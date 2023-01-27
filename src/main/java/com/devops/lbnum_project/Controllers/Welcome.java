package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Views.ViewFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Welcome implements Initializable {
    public Button sign_button;
    public Button login_button;
    public AnchorPane app_content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //autres boutons
        login_button.setOnAction(actionEvent -> {

            try {
                ViewFactory.getPage(app_content,"Login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        sign_button.setOnAction(actionEvent -> {
            try {
                ViewFactory.getPage(app_content,"Register");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
