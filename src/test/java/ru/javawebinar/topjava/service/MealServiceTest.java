package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    @Autowired
    private MealService service;

    static {

        SLF4JBridgeHandler.install();
    }

    @Test
    public void get() {
        assertEquals(service.get(99999, ADMIN_ID), MEAL1);
    }

    @Test
    public void getForNotUserId() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL10.getId(), ADMIN_ID));
    }

    @Test
    public void deleteForNotUserId() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL10.getId(), ADMIN_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL10.getId(), USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL10.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> betweenInclusive = service.getBetweenInclusive(LocalDate.of(2015, 6, 01), LocalDate.of(2015, 6, 01), ADMIN_ID);
        assertEquals(betweenInclusive, Arrays.asList(MEAL1, MEAL2));
        assertEquals(betweenInclusive.size(), 2);
    }

    @Test
    public void getAll() {
        assertEquals(service.getAll(ADMIN_ID), Arrays.asList(MEAL1, MEAL2));
        assertEquals(service.getAll(ADMIN_ID).size(), 2);
    }

    @Test
    public void update() {
        service.update(UPDATEMEAL, USER_ID);
        assertEquals(service.get(UPDATEMEAL.getId(), USER_ID), UPDATEMEAL);
    }

    @Test
    public void updateForNotUserId() {
        assertThrows(NotFoundException.class, () -> service.update(UPDATEMEAL, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(NEWMEAL, ADMIN_ID);
        Integer id = created.getId();
        Meal newMeal = NEWMEAL;
        assertEquals(created, newMeal);
        assertEquals(service.get(id, ADMIN_ID), newMeal);


    }
}