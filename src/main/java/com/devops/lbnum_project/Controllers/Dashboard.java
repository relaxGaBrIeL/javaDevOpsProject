package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Controllers.services.FileExplore;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    public FileExplore fileExplore = new FileExplore();
    public ScrollPane boxFiles;
    public Text listFiles;
    public Button btnGoBack;
    public TextField searchDirectory;
    public Button btnMove;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        majList(fileExplore.getListe());
    }


    public void majList(File[] folderList){
        listFiles.setText("Liste dossier :");
        for (File file : folderList){
            if(file.isFile()){
                listFiles.setText(listFiles.getText() + "\n - (Fichier) " + file.getName());
            }
            if (file.isDirectory()){
                listFiles.setText(listFiles.getText() + "\n - (Dossier) " + file.getName());
            }
        }

//        for (File file : folderList){
//            if(file.isFile()){
//                boxFiles
//            }
//            if (file.isDirectory()){
//                listFiles.setText(listFiles.getText() + "\n - (Dossier) " + file.getName());
//            }
//        }
    }


    public void goBack(ActionEvent event) {
        fileExplore.goBackFolder();
        majList(fileExplore.getListe());
    }

    public void moveFolder(ActionEvent event) {
        if(searchDirectory.getText() != "") {   //si le champ de recherche n'est pas vide
            fileExplore.moveFolder(searchDirectory.getText());
            majList(fileExplore.getListe());
            searchDirectory.setText("");
            searchDirectory.requestFocus();
        }
    }
}
