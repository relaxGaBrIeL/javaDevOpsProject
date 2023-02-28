package com.devops.lbnum_project.services.calendar;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CalendarController implements Initializable {

    @FXML
    public Label monthYearLabel;

    @FXML
    public GridPane calendarGrid;

    public YearMonth currentYearMonth;

    @FXML
    public void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        updateCalendar();
    }

    @FXML
    public void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        updateCalendar();
    }

    public void createCalendar() {
        // Obtenir l'année et le mois actuels
        currentYearMonth = YearMonth.now();

        // Créer les contrôles
        Button previousMonthButton = new Button("<<");
        previousMonthButton.setOnAction(e -> previousMonth());

        Button nextMonthButton = new Button(">>");
        nextMonthButton.setOnAction(e -> nextMonth());

        monthYearLabel.setText(currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear());
        monthYearLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        VBox headerBox = new VBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.getChildren().addAll(previousMonthButton, monthYearLabel, nextMonthButton);

        calendarGrid.setAlignment(Pos.CENTER);

        updateCalendar();
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

        // Mettre à jour le label du mois et de l'année
        monthYearLabel.setText(currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createCalendar();
    }

}
