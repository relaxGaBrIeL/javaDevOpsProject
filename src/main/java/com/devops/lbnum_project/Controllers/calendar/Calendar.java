package com.devops.lbnum_project.Controllers.calendar;

import java.time.LocalDate;
import java.time.YearMonth;

public class Calendar {

    private YearMonth currentYearMonth;

    public Calendar() {
        currentYearMonth = YearMonth.now();
    }

    public void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
    }

    public void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
    }

    public String getMonthYearLabel() {
        return currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear();
    }

    public LocalDate getFirstDayOfMonth() {
        return currentYearMonth.atDay(1);
    }

    public int getDaysInMonth() {
        return currentYearMonth.lengthOfMonth();
    }

    public boolean isToday(LocalDate date) {
        return date.equals(LocalDate.now());
    }
}