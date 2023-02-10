package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Models.DAOUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContactList implements Initializable {

    static DAOUser db = new DAOUser();
    public VBox userList;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show(userList);
    }
}

