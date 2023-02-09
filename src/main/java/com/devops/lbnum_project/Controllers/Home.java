package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class Home {
    public Button accueil_btn;
    public Button travail_btn;
    public Button groupe_btn;
    public Button calendrier_btn;
    public Button dossier_btn;
    public Button notifs_btn;
    public Button logout_btn;


    public void logout(ActionEvent event) throws IOException {

        //TODO : mettre à jour le statut de l'utilisateur
        ViewFactory.setPage("Login", event);
    }
}
