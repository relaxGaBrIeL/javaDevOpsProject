package com.devops.lbnum_project.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ViewFactory {

    /**
     * Permet de changer la vue globale de l'application suite à l'action sur un bouton
     */
    public static void setPage(String page, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ViewFactory.class.getResource("/com/devops/lbnum_project/Fxml/Pages/"+page+".fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
