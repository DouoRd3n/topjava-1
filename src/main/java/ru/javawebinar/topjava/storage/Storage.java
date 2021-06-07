package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface Storage {
    public void clear();
    public void save(Meal m);
    public void delete(String uuid);
    public void update(Meal m);
    public Meal get(String uuid);
    public List<MealTo> getAllMeals();
}
