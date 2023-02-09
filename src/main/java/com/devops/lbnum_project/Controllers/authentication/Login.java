package com.devops.lbnum_project.Controllers.authentication;

import com.devops.lbnum_project.Models.Model;
import com.devops.lbnum_project.Models.Validator;
import com.devops.lbnum_project.Views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class Login {

    public Button login_btn;
    public TextField email_field;
    public TextField password_field;
    public Text msg_form;
    public Button retour_btn;

    Model db = new Model();


    /**
     * Permet de savoir si l'User existe
     */

    protected boolean handleLoginSubmit() {
        boolean connected = false;
        String mail = email_field.getText();
        String password = password_field.getText();
        if (Validator.validateEmail(mail)) {
            LoginResponse loginResponse = db.login(mail, password);

            if (loginResponse.isConnected()) {
                msg_form.setText("connected!");
                connected = true;
//            User user = loginResponse.getUser();
            } else {
                msg_form.setText("no Connected!");
            }
        } else {
            msg_form.setText("Mail incorrect !");
        }
        return connected;
    }

    //redirection vers Welcome
    public void returnWelcome(ActionEvent event) throws IOException {
        ViewFactory.setPage("Welcome", event);
    }

    //Redirection vers Home suite à la vérification de l'existence du compte
    public void connexion(ActionEvent event) throws IOException {
        if (handleLoginSubmit()) ViewFactory.setPage("Home", event);
    }
}
