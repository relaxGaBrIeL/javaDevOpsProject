package com.devops.lbnum_project.Controllers.authentication;

import com.devops.lbnum_project.Models.DAOUser;
import com.devops.lbnum_project.Models.Model;
import com.devops.lbnum_project.Models.Validator;
import com.devops.lbnum_project.Views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class Register {


    public Button register_btn;
    public TextField cPassword_field;
    public TextField password_field;
    public TextField email_field;
    public TextField lastName_field;
    public TextField fName_field;
    public Text msg_form;

   DAOUser db = new DAOUser();

    /**
     * Fonction qui permet de créer un nouvel utilisateur après avoir vérifié que le formulaire soit conforme
     * @return true si l'utilisateur a été créé
     */
    public boolean handleSignSubmit() {
        String mail = email_field.getText();
        String password = password_field.getText();
        String passwordConf = cPassword_field.getText();
        String fName = fName_field.getText();
        String lName = lastName_field.getText();
        boolean connected = false;

        if (Validator.validateEmail(mail)) {

            if (mail.isEmpty() || passwordConf.isEmpty() || password.isEmpty() || fName.isEmpty() || lName.isEmpty()) {
                msg_form.setText("Tous les champs doivent être renseigner !");

            } else if (!password.equals(passwordConf)) {
                msg_form.setText("Vos mots de passe doit être identique!");

            } else {
                SignupResponse signupResponse = db.signup(mail, password, fName, lName);
//                db.closeConnection();
                if (signupResponse.isSuccess()) {
                    msg_form.setText(signupResponse.getMessage());
                    email_field.setText("");
                    password_field.setText("");
                    cPassword_field.setText("");
                    fName_field.setText("");
                    lastName_field.setText("");
                    connected=true;

                } else {
                    msg_form.setText(signupResponse.getMessage());
                }
            }
        } else {
            msg_form.setText("Email non valide");
        }
        return connected;
    }

    //Redirige vers Home après l'inscription
    public void register(ActionEvent event) throws IOException {
        if (handleSignSubmit()) ViewFactory.setPage("Home", event);
    }

    //Redirection vers Welcome
    public void returnWelcome(ActionEvent event) throws IOException {
        ViewFactory.setPage("Welcome", event);
    }
}
