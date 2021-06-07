package ru.javawebinar.topjava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Meal implements Serializable {
    private static final long serialVersionUID = 2041275512219239990L;

    private final String uuid;
    private  LocalDateTime dateTime;

    private  String description;

    private  int calories;

    public Meal() {
        this.uuid = UUID.randomUUID().toString();
        this.description ="";
        this.calories =0;
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.uuid = UUID.randomUUID().toString();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public String getUuid() {
        return uuid;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
