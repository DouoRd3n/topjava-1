package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Component

public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.meals) {
            save(meal, 1);
        }
    }

    @Override
    public Meal save(Meal meal, Integer userID) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userID);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, Integer userId) {
        log.info("delete {}", id);
        if (repository.get(id).getUserId() == userId) {
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, Integer usedId) {
        log.info("get {}", id);
        Meal meal = repository.get(id);
        if (meal.getUserId() == usedId) {
            return meal;
        }
        return null;

    }

    @Override
    public List<Meal> getAll(Integer userId) {
        log.info("getAll");
        return repository.values()
                .stream()
                .filter(m -> m.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
    }
}

