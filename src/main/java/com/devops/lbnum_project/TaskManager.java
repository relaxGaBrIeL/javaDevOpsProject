package com.devops.lbnum_project;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Optional;

public class TaskManager extends Application {

    private ObservableList<Task> tasks;
    private ListView<Task> taskListView;

    @Override
    public void start(Stage primaryStage) {
        // create task list
        tasks = FXCollections.observableArrayList();

        // create list view
        taskListView = new ListView<>(tasks);

        // create menu bar
        MenuBar menuBar = new MenuBar();

        // create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");

        // create menu items
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        MenuItem changeStatusMenuItem = new MenuItem("Change Status");

        // add menu items to menus
        fileMenu.getItems().add(newMenuItem);
        editMenu.getItems().addAll(deleteMenuItem, changeStatusMenuItem);

        // add menus to menu bar
        menuBar.getMenus().addAll(fileMenu, editMenu);

        // add event handlers to menu items
        newMenuItem.setOnAction(event -> showNewTaskDialog());
        deleteMenuItem.setOnAction(event -> deleteSelectedTask());
        changeStatusMenuItem.setOnAction(event -> showChangeStatusDialog());

        // create main layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(taskListView);

        // create scene
        Scene scene = new Scene(root, 400, 300);

        // set stage properties
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showNewTaskDialog() {
        // create dialog
        Dialog<Task> dialog = new Dialog<>();
        dialog.setTitle("New Task");

        // set dialog content
        Label descriptionLabel = new Label("Description:");
        TextField descriptionTextField = new TextField();
        Label dueDateLabel = new Label("Due Date:");
        DatePicker dueDatePicker = new DatePicker();
        VBox content = new VBox(descriptionLabel, descriptionTextField, dueDateLabel, dueDatePicker);
        dialog.getDialogPane().setContent(content);

        // add buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // set result converter
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                String description = descriptionTextField.getText();
                LocalDate dueDate = dueDatePicker.getValue();
                return new Task(description, dueDate, Task.Status.NOT_STARTED);
            }
            return null;
        });

        // show dialog and add new task if OK button is clicked
        Optional<Task> result = dialog.showAndWait();
        result.ifPresent(task -> {
            tasks.add(task);
        });
    }

    private void deleteSelectedTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            tasks.remove(selectedTask);
        }
    }

    private void showChangeStatusDialog() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            // create dialog
            Dialog<Task.Status> dialog = new Dialog<>();
            dialog.setTitle("Change Task Status");

            // set dialog content
            Label statusLabel = new Label("Status:");
            ComboBox<Task.Status> statusComboBox = new ComboBox<>(FXCollections.observableArrayList(Task.Status.values()));
            statusComboBox.getSelectionModel().select(selectedTask.getStatus());
            VBox content = new VBox(statusLabel, statusComboBox);
            dialog.getDialogPane().setContent(content);

            // add buttons
            ButtonType changeButton = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(changeButton, ButtonType.CANCEL);

            // set result converter
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == changeButton) {
                    return statusComboBox.getValue();
                }
                return null;
            });

            // show dialog and change task status if OK button is clicked
            Optional<Task.Status> result = dialog.showAndWait();
            result.ifPresent(status -> {
                selectedTask.setStatus(status);
            });
        }
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
            return description + " (Due: " + dueDate + ", Status: " + status + ")";
        }

        public enum Status {
            NOT_STARTED,
            IN_PROGRESS,
            COMPLETED
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
