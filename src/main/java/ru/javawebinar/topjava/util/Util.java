package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public class Util {
    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(Meal m, LocalDateTime start, LocalDateTime end) {
        return (start==null || m.getDateTime().compareTo(start)>=0) && ( end == null || m.getDateTime().compareTo(end)<0);
    }
}