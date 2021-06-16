package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id, SecurityUtil.authUserId()), id);
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id, SecurityUtil.authUserId()), id);
    }

    public Meal create(Meal meal) {
        return repository.save(meal, SecurityUtil.authUserId());
    }

    public List<MealTo> getAll() {
        List<Meal> all = repository.getAll(SecurityUtil.authUserId());
        Map<LocalDate, Integer> caloriesSumByDay = all.stream().collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return all.stream()
                .map(m -> new MealTo(m, caloriesSumByDay.get(m.getDate()) > SecurityUtil.authUserCaloriesPerDay()))
                .collect(Collectors.toList());


    }
}