package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.BooleanMealHolder;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)


        );

//        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        for (UserMealWithExcess userMealWithExcess : mealsTo) {
//            System.out.println(userMealWithExcess);
//        }

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    private static void setMealsForDAy(UserMeal um, Map<Integer, BooleanMealHolder> mealForDay, int caloriesPerDay) {
        if (mealForDay.get(getDayOfMount(um)) == null) {
            mealForDay.put(getDayOfMount(um), new BooleanMealHolder(um.getCalories(), caloriesPerDay));
        } else {
            mealForDay.get(getDayOfMount(um)).setCalories(um.getCalories());
        }

    }

    private static LocalTime getLocalTime(UserMeal userMeal) {
        return LocalTime.of(userMeal.getDateTime().getHour(), userMeal.getDateTime().getMinute());
    }

    private static Integer getDayOfMount(UserMeal userMeal) {
        return userMeal.getDateTime().getDayOfMonth();
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        Map<Integer, BooleanMealHolder> mealForDay = new HashMap<>();
        List<UserMealWithExcess> listMeals = new ArrayList<>();
        BooleanMealHolder booleanMealHolder;
        for (UserMeal um : meals) {
            setMealsForDAy(um, mealForDay, caloriesPerDay);
            booleanMealHolder = mealForDay.get(um.getDateTime().getDayOfMonth());
            if (TimeUtil.isBetweenHalfOpen(getLocalTime(um), startTime, endTime)) {
                listMeals.add(new UserMealWithExcess(um, booleanMealHolder));
            }
        }
        return listMeals;
    }


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        Map<Integer, BooleanMealHolder> mealForDay = new HashMap<>();
        return meals.stream()
                .peek(um -> setMealsForDAy(um, mealForDay, caloriesPerDay))
                .filter(um -> TimeUtil.isBetweenHalfOpen(getLocalTime(um), startTime, endTime))
                .map(um -> new UserMealWithExcess(um, mealForDay.get(um.getDateTime().getDayOfMonth())))
                .collect(Collectors.toList());


    }
}
