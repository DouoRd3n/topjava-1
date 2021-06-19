package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
@Component
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MealService service;


    public Meal get(Integer id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal);
    }

    public Meal update(Meal meal, int id) {
        log.info("update {}", meal);
        assureIdConsistent(meal, id);
        return service.update(meal);
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<MealTo> getAll(String startDate, String endDate, String startTime, String endTime) {
        List<MealTo> all = service.getAll();
        if (!startDate.equals("") && !endDate.equals("")) {
            return all.stream()
                    .filter(m -> DateTimeUtil.toLocalDate(startDate).getDayOfMonth() <= m.getDateTime().getDayOfMonth())
                    .filter(m -> DateTimeUtil.toLocalDate(endDate).getDayOfMonth() >= m.getDateTime().getDayOfMonth())
                    .collect(Collectors.toList());

        } else if (!startDate.equals("") && endDate.equals("")) {
            return all.stream()
                    .filter(m -> DateTimeUtil.toLocalDate(startDate).getDayOfMonth() <= m.getDateTime().getDayOfMonth())
                    .collect(Collectors.toList());

        } else if (startDate.equals("") && !endDate.equals("")) {
            return all.stream()
                    .filter(m -> DateTimeUtil.toLocalDate(endDate).getDayOfMonth() >= m.getDateTime().getDayOfMonth())
                    .collect(Collectors.toList());
        } else if (!startTime.equals("") && !endTime.equals("")) {
            return all.stream()
                    .filter(m -> DateTimeUtil.isBetweenHalfOpen(m.getDateTime().toLocalTime(), DateTimeUtil.toLocalTime(startTime), DateTimeUtil.toLocalTime(endTime)))
                    .collect(Collectors.toList());
        } else if (!startTime.equals("") && endTime.equals("")){
            return all.stream()
                    .filter(m->DateTimeUtil.toLocalTime(startTime).compareTo(m.getDateTime().toLocalTime())<0)
                    .collect(Collectors.toList());
        }else if (startTime.equals("") && !endTime.equals("")){
            return all.stream()
                    .filter(m->DateTimeUtil.toLocalTime(endTime).compareTo(m.getDateTime().toLocalTime())>0)
                    .collect(Collectors.toList());
        }
        return all;
    }

}