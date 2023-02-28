package com.devops.lbnum_project.services.taskManager;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {
    private final SimpleStringProperty name;
    private final SimpleStringProperty description;
    private final SimpleBooleanProperty completed;

    public Task(String name, String description, boolean completed) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.completed = new SimpleBooleanProperty(completed);
    }

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public void setCompleted(boolean b) {
    }
}

