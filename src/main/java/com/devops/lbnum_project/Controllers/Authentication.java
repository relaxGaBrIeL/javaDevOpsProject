package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Models.Model;
import com.devops.lbnum_project.Models.Validator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Window;
import java.net.URL;
import java.util.ResourceBundle;


public class Authentication implements Initializable {

    // public VBox register_form;
    public Button register_btn;
    public Label error_form;
    public VBox register_form;
    public TextField fName_field;
    public TextField lastName_field;
    public TextField email_field;
    public PasswordField password_field;
    public PasswordField cPassword_field;
    public Button login_btn;
    public Text form_title;
    public VBox login_form;
    Model db = new Model();


    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    public void handleLoginSubmit() {
        String mail = email_field.getText();
        String password = password_field.getText();
        if (Validator.validateEmail(mail)) {
            LoginResponse loginResponse = db.login(mail, password);

            if (loginResponse.isConnected()) {
                error_form.setText("connected!");
//            User user = loginResponse.getUser();
            } else {
                error_form.setText("no Connected!");
//                password_field.setText("");
//                email_field.setText("");
            }
        } else {
            error_form.setText("Mail incorrect !");
        }
       // db.closeConnection();
    }


    @FXML
    public void handleSignSubmit() {
        String mail = email_field.getText();
        String password = password_field.getText();
        String passwordConf = cPassword_field.getText();
        String fName = fName_field.getText();
        String lName = lastName_field.getText();

        if (Validator.validateEmail(mail)) {

            if (mail.isEmpty() || passwordConf.isEmpty() || password.isEmpty() || fName.isEmpty() || lName.isEmpty()) {
                error_form.setText("Tous les champs doivent être renseigner !");

            } else if (!password.equals(passwordConf)) {
                error_form.setText("Vos mots de passe doit être identique!");

            } else {
                SignupResponse signupResponse = db.signup(mail, password, fName, lName);
//                db.closeConnection();
                if (signupResponse.isSuccess()) {
                    error_form.setText(signupResponse.getMessage());
                    email_field.setText("");
                    password_field.setText("");
                    cPassword_field.setText("");
                    fName_field.setText("");
                    lastName_field.setText("");

                } else {
                    error_form.setText(signupResponse.getMessage());
                }
            }
        } else {
            error_form.setText("Email non valide");
        }
        db.closeConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        register_btn.setOnAction(event -> {
            handleSignSubmit();
        });
        login_btn.setOnAction(actionEvent -> {
            handleLoginSubmit();
        });
    }
}
