package com.devops.lbnum_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskApplication extends Application {

    private List<Task> tasks = new ArrayList<>();
    ListView<Task> taskListView;
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 400);

        // create menu bar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newTaskMenuItem = new MenuItem("New Task");
        newTaskMenuItem.setOnAction(e -> showNewTaskDialog());
        MenuItem quitMenuItem = new MenuItem("Quit");
        quitMenuItem.setOnAction(e -> primaryStage.close());
        fileMenu.getItems().addAll(newTaskMenuItem, quitMenuItem);
        menuBar.getMenus().add(fileMenu);
        root.setTop(menuBar);

        // create list view
        taskListView = new ListView<>();
        root.setCenter(taskListView);

        // create form for adding tasks
        TextField taskTextField = new TextField();
        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            addTask(taskTextField.getText());
            taskTextField.clear();
        });
        root.setBottom(new BorderPane(taskTextField, null, addButton, null, new Label("Task:")));

        // add some sample tasks
        tasks.add(new Task("Task 1", LocalDate.now(), Task.Status.NOT_STARTED));
        tasks.add(new Task("Task 2", LocalDate.now().plusDays(1), Task.Status.IN_PROGRESS));
        tasks.add(new Task("Task 3", LocalDate.now().plusDays(2), Task.Status.COMPLETE));
        // update list view
        taskListView.getItems().addAll(tasks);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Task Application");
        primaryStage.show();
    }

    private void showNewTaskDialog() {
        // create dialog
        Dialog<Task> dialog = new Dialog<>();
        dialog.setTitle("Add New Task");

        // set dialog content
        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();
        Label dueDateLabel = new Label("Due Date:");
        DatePicker dueDatePicker = new DatePicker();
        VBox content = new VBox(descriptionLabel, descriptionField, dueDateLabel, dueDatePicker);
        dialog.getDialogPane().setContent(content);

        // add buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // set result converter
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                String description = descriptionField.getText();
                LocalDate dueDate = dueDatePicker.getValue();
                if (description.isEmpty() || dueDate == null) {
                    // show error message if required fields are not filled in
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please fill in all required fields");
                    alert.showAndWait();
                    return null;
                }
                return new Task(description, dueDate, Task.Status.NOT_STARTED);
            }
            return null;
        });

        // show dialog and add new task to list if OK button is clicked
        Optional<Task> result = dialog.showAndWait();
        result.ifPresent(task -> addTask(task.getDescription()));
    }

    private void addTask(String description) {
        Task task = new Task(description, LocalDate.now(), Task.Status.NOT_STARTED);
        taskListView.getItems().add(task);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Task {

        private String description;
        private LocalDate dueDate;
        private Status status;

        public Task(String description, LocalDate dueDate, Status status) {
            this.description = description;
            this.dueDate = dueDate;
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return String.format("%s (%s) %s", description, dueDate.toString(),getStatus());
        }

        public enum Status {
            NOT_STARTED,
            IN_PROGRESS,
            COMPLETE
        }
    }
}