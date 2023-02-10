package com.devops.lbnum_project.Controllers.socket;

import javafx.application.Platform;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public abstract class ClientController {

    public SocketConnection getSocketConnection() {
        try{
            SocketConnection client = new SocketConnection();
            System.out.println("Connected au serveur !");
            return client;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
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
