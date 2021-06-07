package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class ListStorage implements Storage {
    private  List<Meal> meals ;

    public ListStorage() {
     meals   = Arrays.asList(
             new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
             new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
             new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
             new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
             new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
             new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
             new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
     );

    }


    @Override
    public void clear() {
        meals.clear();
    }

    @Override
    public void update(Meal m) {

    }

    @Override
    public void save(Meal m) {
        meals.add(m);
    }

    @Override
    public Meal get(String uuid) {
        for (Meal m: meals) {
            if (m.getUuid().equals(uuid)){
                return m;
            }
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        for (int i = 0; i <meals.size() ; i++) {
            if (meals.get(i).getUuid().equals(uuid)){
                meals.remove(i);
            }
        }
    }

    @Override
    public List<Meal> getAllSorted() {
        return meals;
    }

    @Override
    public int size() {
        return meals.size();
    }
}
