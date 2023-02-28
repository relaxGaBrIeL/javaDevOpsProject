package com.devops.lbnum_project;

import com.devops.lbnum_project.services.fileExplore.FileExploreHome;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    public FileExploreHome fileExplore = new FileExploreHome();
    public ScrollPane boxFiles;
    public Text listFiles;
    public Button btnGoBack;
    public TextField searchDirectory;
    public Button btnMove;
    public Button gl_calendar;
    public Button gl_drive;
    public Button m_files;
    public Button m_folders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gl_calendar.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/gl_calendar.png")),75.0,75.0,true,true)));
        gl_drive.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/gl_drive.png")),75.0,75.0,true,true)));
        m_files.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/doc.png")),60.0,60.0,true,true)));
        m_folders.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/folder.png")),55.0,55.0,true,true)));

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
    }
    public void goBack(ActionEvent event) {
        fileExplore.goBackFolder();
        majList(fileExplore.getListe());
    }

    public void moveFolder(ActionEvent event) {
        if(!Objects.equals(searchDirectory.getText(), "")) {   //si le champ de recherche n'est pas vide
            fileExplore.moveFolder(searchDirectory.getText());
            majList(fileExplore.getListe());
            searchDirectory.setText("");
            searchDirectory.requestFocus();
        }
    }
}
