package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

public class MealTestData {
    public static final Meal MEAL1 = new Meal(99999, LocalDateTime.of(2015, Month.JULY, 01, 21, 00, 00), "админ ужын", 1500 );
    public static final Meal MEAL2 = new Meal(99998, LocalDateTime.of(2015, Month.JULY, 01, 12, 00, 00), "админ обед", 530 );
    public static final Meal MEAL3 = new Meal(99997, LocalDateTime.of(2021, Month.JULY, 29, 9, 10, 00), "юзер сніданок", 1000 );
    public static final Meal MEAL4 = new Meal(99996, LocalDateTime.of(2021, Month.JULY, 29, 14, 00, 00), "обед юзер", 500 );
    public static final Meal MEAL5 = new Meal(99995, LocalDateTime.of(2021, Month.JULY, 29, 18, 00, 00), "ужын юзер", 500 );
    public static final Meal MEAL6 = new Meal(99994, LocalDateTime.of(2021, Month.JULY, 30, 9, 00, 00), "юзер сніданок", 1100 );
    public static final Meal MEAL7 = new Meal(99993, LocalDateTime.of(2021, Month.JULY, 30, 14, 00, 00), "юзер обед", 500 );

    public static final Meal MEAL10 = new Meal(99992, LocalDateTime.of(2021, Month.JULY, 30, 20, 25, 00), "юзер обед", 500 );
    public static final Meal UPDATEMEAL = new Meal(99992, LocalDateTime.of(2021, Month.JULY, 30, 20, 25, 00), "обед", 100 );
    public static final Meal NEWMEAL = new Meal( LocalDateTime.of(2021, Month.JULY, 28, 20, 25, 00), "полдник", 1000 );

}
