package com.devops.lbnum_project.Controllers.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TaskController {
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> nameColumn;
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    @FXML
    private TableColumn<Task, Boolean> completedColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;

    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));

        taskTable.setItems(taskList);
    }

    @FXML
    private void addTask() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        Task task = new Task(name, description, false);
        taskList.add(task);
        nameField.clear();
        descriptionField.clear();
    }

    @FXML
    private void deleteTask() {
        Task task = taskTable.getSelectionModel().getSelectedItem();
        taskList.remove(task);
    }

    @FXML
    private void markCompleted() {
        Task task = taskTable.getSelectionModel().getSelectedItem();
        task.setCompleted(true);
    }
}
