package com.devops.lbnum_project.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class ViewFactory  {

    /**
     * Permet de changer la vue globale de l'application suite à l'action sur un bouton
     * @param page nom de la page désirée
     * @param event clic du bouton
     */
    public static void setPage(String page, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ViewFactory.class.getResource("/com/devops/lbnum_project/Fxml/Pages/"+page+".fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Permet de changer des scene ou contenu de la grid pane content
     * @param content gridPane
     * @param scene fichier Fxml
     */
    public static void setScene(GridPane content, String scene) throws IOException {
        Pane newPane = FXMLLoader.load(Objects.requireNonNull(ViewFactory.class.getResource("/com/devops/lbnum_project/Fxml/Components/"+scene+".fxml")));
        content.getChildren().setAll(newPane);
    }
}
