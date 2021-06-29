import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository;
import ru.javawebinar.topjava.repository.jdbc.JdbcUserRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class MainRepo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appcTx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml");
        JdbcMealRepository bean = appcTx.getBean(JdbcMealRepository.class);
        JdbcUserRepository userRepo = appcTx.getBean(JdbcUserRepository.class);
        bean.save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510), 100001);
        bean.save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500), 100001);
        Meal meal = bean.get(100078, 100001);

        List<User> all = userRepo.getAll();
        System.out.println(meal.toString());
        System.out.println(all);
    }
}
