package com.devops.lbnum_project.services.calendar;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;

public class Cal implements Initializable {

    public Label monthYearLabel;
    public GridPane calendarGrid;
    public YearMonth currentYearMonth;
    public ToolBar tolbar;
    public BorderPane calendar;

    public void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        updateCalendar();
    }

    public void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        updateCalendar();
    }

    public void createCalendar() {
        currentYearMonth = YearMonth.now();
        // Créer les contrôles
        Button previousMonthButton = new Button("<<");
        previousMonthButton.setOnAction(e -> previousMonth());

        Button nextMonthButton = new Button(">>");
        nextMonthButton.setOnAction(e -> nextMonth());
        // monthYearLabel = new Label(currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear());

        monthYearLabel.setText(currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear());
        monthYearLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        //tolbar.getItems().addAll(previousMonthButton, nextMonthButton);

//        calendarGrid = new GridPane();
//        calendarGrid.setAlignment(Pos.CENTER);
//        calendarGrid.setHgap(10);
//        calendarGrid.setVgap(10);
//

       //updateCalendar();
    }


    public void updateCalendar() {
        calendarGrid.getChildren().clear();

        // Ajouter les en-têtes de colonne
        String[] daysOfWeek = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            String day = daysOfWeek[i];
            Button dayButton = new Button(day);
            dayButton.setStyle("-fx-background-color: #b5ab72;");
            dayButton.setPrefWidth(60.0);
            GridPane.setColumnIndex(dayButton, i);
            GridPane.setRowIndex(dayButton, 0);
            calendarGrid.getChildren().add(dayButton);
        }

        // Ajouter les jours du mois
        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        int daysInMonth = currentYearMonth.lengthOfMonth();
        int row = 1;
        int col = dayOfWeek - 1;
        for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
            Button dayButton = new Button(String.valueOf(dayOfMonth));
            dayButton.setPrefWidth(60.0);
            GridPane.setColumnIndex(dayButton, col);
            GridPane.setRowIndex(dayButton, row);
            calendarGrid.getChildren().add(dayButton);

            // Mettre en bleu le bouton correspondant au jour en cours
            if (currentYearMonth.atDay(dayOfMonth).equals(LocalDate.now())) {

                dayButton.setStyle("-fx-background-color: blue;");
            }

            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
        calendar.setCenter(calendarGrid);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // createCalendar();
    }
}