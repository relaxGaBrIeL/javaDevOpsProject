package com.devops.lbnum_project.services.fileExplore;

import com.devops.lbnum_project.services.client.User;
import com.devops.lbnum_project.Models.DAODirectories;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class FileExplorer implements Initializable {



    public BorderPane contentPane;
    private DAODirectories DB = new DAODirectories();
    public final ObjectProperty<TreeItem<File>> root = new SimpleObjectProperty<>();
    public void setupUI() throws SQLException {
        // Create root node for the tree view
        root.set(new TreeItem<>(null));
        root.get().setExpanded(true);

        // Create tree view
        TreeView<File> treeView = new TreeView<>();
        treeView.setPrefWidth(200.0);
        treeView.setRoot(root.get());
        treeView.setShowRoot(false);
        treeView.setCellFactory(tv -> new FileExplorerController());
        treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    TreeItem<File> selectedItem = treeView.getSelectionModel().getSelectedItem();
                    if (selectedItem != null && selectedItem.getValue().isDirectory()) {
                        exploreDirectory(selectedItem.getValue(), selectedItem);
                    }
                }
            }
        });

        // Create layout
        //contentPane.setCenter(treeView);
        FlowPane flowPane = new FlowPane();
        //contentPane.getChildren().add(treeView);
        flowPane.setPrefWrapLength(contentPane.getWidth() - 500);
        flowPane.getChildren().add(treeView);
        contentPane.setCenter(treeView);
       // System.out.println("Là");
        //System.out.println(User.getUserId());
        //System.out.println(User.getFname());
        //int UserId = User.getUserId();
        List<String> directories = this.DB.getDirectories(User.getUserId());
        File selectedDirectory ;
        if (!directories.isEmpty()) {
             selectedDirectory = new File(directories.get(0));
        } else {
            Stage primaryStage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                this.DB.insertDirectory(User.getUserId(), selectedDirectory.getAbsolutePath());
                selectedDirectory = new File(selectedDirectory.getAbsolutePath());
            }

        }
        assert selectedDirectory != null;
        exploreDirectory(selectedDirectory, root.get());
    }

    public void exploreDirectory(File directory, TreeItem<File> parentItem) {
        parentItem.getChildren().clear();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                TreeItem<File> item = new TreeItem<>(file);
                parentItem.getChildren().add(item);
                if (file.isDirectory()) {
                    item.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/folder.png")))));
                    item.setExpanded(false);
                    item.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getClickCount() == 2) {
                                exploreDirectory(item.getValue(), item);
                            }
                        }
                    });
                } else {
                    item.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/file.png")))));
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setupUI();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
