package com.devops.lbnum_project.Views;

import com.devops.lbnum_project.Controllers.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class ViewFactory {

   public static void getPage(AnchorPane content, String page) throws IOException {
        Pane newPane = FXMLLoader.load(Objects.requireNonNull(HomeController.class.getResource("/com/devops/lbnum_project/Fxml/Pages/"+page+".fxml")));
        content.getChildren().setAll(newPane);
    }

    public static void getScene(Pane content, String page) throws IOException {
        Pane newPane = FXMLLoader.load(Objects.requireNonNull(HomeController.class.getResource("/com/devops/lbnum_project/Fxml/Pages/"+page+".fxml")));
        content.getChildren().setAll(newPane);
    }
}
