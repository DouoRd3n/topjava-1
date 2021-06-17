package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public class MealRestController {

    private MealService service;

    public Meal get(Integer id){
      return service.get(id);
    }
    public void delete(int id){
        service.delete(id);
    }
    public Meal create(Meal meal){
        checkNew(meal);
        return service.create(meal);
    }
    public Meal update(Meal meal, int id){
        assureIdConsistent(meal, id);
        return service.update(meal);
    }
    public List<MealTo> getAll(){
        return service.getAll();
    }

}