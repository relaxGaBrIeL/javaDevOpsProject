package com.devops.lbnum_project;

import com.devops.lbnum_project.Views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class Welcome {
    public Button sign_button;
    public Button login_button;


    //Redirection vers la page d'inscription
    public void registerPage(ActionEvent event) throws IOException {
        ViewFactory.setPage("Register", event);
    }

    //Redirection vers la page de connexion
    public void loginPage(ActionEvent event) throws IOException {
        ViewFactory.setPage("Login", event);
    }
}
