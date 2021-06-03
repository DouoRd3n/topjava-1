package ru.javawebinar.topjava.model;

public class BooleanMealHolder {
    private boolean excess;
    private int calories;
    private int caloriesPerDay;

    public BooleanMealHolder(int calories, int caloriesPerDay) {
        this.excess = calories < caloriesPerDay;
        this.calories = calories;
        this.caloriesPerDay = caloriesPerDay;
    }

    public void setCalories(int calories) {
        this.calories =this.calories + calories;
        this.excess = getExsces();

    }

    private boolean getExsces() {
        return calories<=caloriesPerDay;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "" + excess;
    }
}
