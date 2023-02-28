package com.devops.lbnum_project.services.client;

import javafx.application.Platform;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public abstract class ClientController {
   // SocketConnection client = SignupResponse.getClient() != null ? SignupResponse.getClient() : LoginResponse.getClient();
//    public SocketConnection getSocketConnection() {
//
//
//
//      //  return client;
//    }
   public abstract void  conversation();

    public static void addLabel(String messageFromServer, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(messageFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle(";-fx-background-color:rgb(233,233,255)"+
                ";-fx-background-radius:20px");
        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> vBox.getChildren().add(hBox));
    }

}
