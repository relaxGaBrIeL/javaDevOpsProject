package com.devops.lbnum_project.Controllers.socket;

import javafx.application.Platform;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    public Button btn_send;
    @FXML
    public TextField tf_message;

    @FXML
    public VBox vbox_messages;

    @FXML
    public ScrollPane sp_main;

    private SocketConnection client;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            client = new SocketConnection();
            System.out.println("Connected au serveur !");

        }catch (IOException e){
            e.printStackTrace();
        }

        vbox_messages.heightProperty().addListener((observableValue, number, newValue) -> sp_main.setVvalue((Double)newValue));
        client.receiveMessageFromServer(vbox_messages);

        btn_send.setOnAction(actionEvent -> {
            String messageTosend = tf_message.getText();
            if (!messageTosend.isEmpty()){
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5,5,5,10));

                Text text = new Text(messageTosend);
                TextFlow textFlow = new TextFlow(text);

                textFlow.setStyle("-fx-color: rgb(239,242,255)" +
                        ";-fx-background-color:rgb(15,125,242)"+
                        ";-fx-background-radius:20px");

                textFlow.setPadding(new Insets(5,10,5,10));
                text.setFill(Color.color(0.934,0.945,0.996));

                hBox.getChildren().add(textFlow);
                vbox_messages.getChildren().add(hBox);

                client.sendMessageToServer(messageTosend);
                tf_message.clear();
            }
        });
    }
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
