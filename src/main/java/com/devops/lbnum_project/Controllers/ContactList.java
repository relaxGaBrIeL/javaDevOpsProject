package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Controllers.socket.ClientController;
import com.devops.lbnum_project.Controllers.socket.SocketConnection;
import com.devops.lbnum_project.Models.DAOUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContactList extends ClientController implements Initializable {

    static DAOUser db = new DAOUser();
    public VBox userList;
    public ScrollPane sp_main;
    public VBox vbox_messages;
    public Button btn_send;
    public TextField tf_message;

    public  void show(VBox vBox) {

        List<User> users = db.getUsers();
        ListView<String> listView = new ListView<>();

        ObservableList<String> userList = FXCollections.observableArrayList();
        for (User user : users) {
            userList.add(user.getLname());
        }

        listView.setItems(userList);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(listView);

    }

    @Override
    public void conversation() {
       SocketConnection client = getSocketConnection();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       conversation();
        show(userList);
    }
}

