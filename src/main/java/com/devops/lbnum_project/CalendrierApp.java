package com.devops.lbnum_project;

import java.time.LocalDate;
import java.time.YearMonth;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CalendrierApp extends Application {

    private YearMonth currentYearMonth;
    private BorderPane root;
    private GridPane calendarGrid;
    private Label monthYearLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        currentYearMonth = YearMonth.now();

        // Créer les contrôles
        Button previousMonthButton = new Button("<<");
        previousMonthButton.setOnAction(e -> previousMonth());

        Button nextMonthButton = new Button(">>");
        nextMonthButton.setOnAction(e -> nextMonth());
        monthYearLabel = new Label(currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear());
        monthYearLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        VBox headerBox = new VBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.getChildren().addAll(previousMonthButton, monthYearLabel,nextMonthButton);

        calendarGrid = new GridPane();
        calendarGrid.setAlignment(Pos.CENTER);
        calendarGrid.setHgap(10);
        calendarGrid.setVgap(10);

        root = new BorderPane();
        root.setTop(headerBox);
        root.setCenter(calendarGrid);

        updateCalendar();

        // Afficher la scène
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Calendrier");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        updateCalendar();
    }

    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        updateCalendar();
    }

//    private void updateCalendar() {
//        calendarGrid.getChildren().clear();
//        String[] daysOfWeek = {"Dim","Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"};
//        for (int i = 0; i < daysOfWeek.length; i++) {
//            String day = daysOfWeek[i];
//            Button dayButton = new Button(day);
//            GridPane.setColumnIndex(dayButton, i);
//            GridPane.setRowIndex(dayButton, 0);
//            calendarGrid.getChildren().add(dayButton);
//        }
//
//        // Ajouter les cellules du calendrier
//        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
//        int row = 1;
//        int col = firstDayOfMonth.getDayOfWeek().getValue() % 7;
//        for (int dayOfMonth = 1; dayOfMonth <= currentYearMonth.lengthOfMonth(); dayOfMonth++) {
//            Button button = new Button(String.valueOf(dayOfMonth));
//            button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//            button.setPrefSize(60, 60);
//            button.setOnAction(e -> System.out.println("Vous avez cliqué sur le " + button.getText()));
//            GridPane.setRowIndex(button, row);
//            GridPane.setColumnIndex(button, col);
//            calendarGrid.getChildren().add(button);
//            col++;
//            if (col == 7) {
//                col = 0;
//                row++;
//            }
//        }
//        monthYearLabel.setText(currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear());
//    }
private void updateCalendar() {
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

}
