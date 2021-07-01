package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(value = "classpath:db/populateDB.sql", config = @SqlConfig (encoding = "UTF-8"))
public class JdbcMealRepositoryTest {
    @Autowired
    private  MealRepository repository;


    @Test
    public void save() {
        assertEquals(repository.save(NEWMEAL, USER_ID), NEWMEAL);
    }

    @Test
    public void delete() {
        assertEquals(repository.delete(MEAL10.getId(), USER_ID), true );
    }

    @Test
    public void get() {
        assertEquals(repository.get(MEAL1.getId(), ADMIN_ID), MEAL1);
    }

    @Test
    public void getNotFoundFromUserId(){
        assertEquals(repository.get(NOT_FOUND, USER_ID), null );
    }

    @Test
    public void getAll() {
        assertEquals(repository.getAll(ADMIN_ID), Arrays.asList(MEAL1,MEAL2));
        assertEquals(repository.getAll(ADMIN_ID).size(), 2);
    }

    @Test
    public void getBetweenHalfOpen() {
        assertEquals(repository.getBetweenHalfOpen(null, LocalDateTime.of(2021,6,30,10,00), USER_ID)
                , Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3));
        assertEquals(repository.getBetweenHalfOpen(null, LocalDateTime.of(2021,6,30,10,00), USER_ID).size()
                , 4);
    }
}