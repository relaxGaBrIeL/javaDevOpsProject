import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class testFolder extends Application {
    private File currentDirectory;
    private ListView<String> listView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Directories Manager App");

        // UI elements
        Label directoryLabel = new Label("Current Directory:");
        TextField directoryField = new TextField();
        Button browseButton = new Button("Browse");
        Button createButton = new Button("Create Directory");
        Button deleteButton = new Button("Delete Directory");
        listView = new ListView<String>();

        // set event handlers
        browseButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                currentDirectory = selectedDirectory;
                directoryField.setText(currentDirectory.getAbsolutePath());
                showDirectoryContents();
            }
        });

        createButton.setOnAction(e -> {
            if (currentDirectory != null) {
                String newDirectoryName = "New Directory";
                File newDirectory = new File(currentDirectory, newDirectoryName);
                if (newDirectory.mkdir()) {
                    showDirectoryContents();
                }
            }
        });

        deleteButton.setOnAction(e -> {
            if (currentDirectory != null) {
                String selected = listView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    File toDelete = new File(currentDirectory, selected);
                    if (toDelete.delete()) {
                        showDirectoryContents();
                    }
                }
            }
        });

        // layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.add(directoryLabel, 0, 0);
        layout.add(directoryField, 1, 0);
        layout.add(browseButton, 2, 0);
        layout.add(createButton, 0, 1);
        layout.add(deleteButton, 1, 1);
        layout.add(listView, 0, 2, 3, 1);

        // scene
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDirectoryContents() {
        listView.getItems().clear();
        if (currentDirectory != null) {
            List<String> items = new ArrayList<>();
            for (File file : currentDirectory.listFiles()) {
                items.add(file.getName());
            }
            listView.getItems().addAll(items);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
