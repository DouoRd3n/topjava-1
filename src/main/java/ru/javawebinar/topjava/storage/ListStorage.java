package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListStorage implements Storage {
    List<Meal> meals;

    public ListStorage() {
        this.meals = Arrays.asList(
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
    public void save(Meal m) {
        meals.add(m);
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
    public void update( Meal m) {
        for (Meal meal: meals) {
            if (meal.getUuid(). equals(m.getUuid())){
                meal.setDateTime(m.getDateTime());
                meal.setCalories(m.getCalories());
                meal.setDescription(m.getDescription());
            }
        }
    }

    @Override
    public Meal get(String uuid) {
        for (Meal meal: meals) {
            if (meal.getUuid().equals(uuid)){
                return meal;
            }
        }
        return null;
    }

    @Override
    public List<MealTo> getAllMeals() {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
        .collect(
                Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
        );
        List<MealTo> collect = meals.stream()
                .map(meal -> MealsUtil.createTo(meal, caloriesSumByDate.get(meal.getDate()) > 2000))
                .collect(Collectors.toList());
        return collect;

    }
}
