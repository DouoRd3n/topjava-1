package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.ListStorage;
import ru.javawebinar.topjava.storage.Storage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

public class mealServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.storage = new ListStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> meals = storage.getAllMeals();
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Meal meal;
        if (action == null) {
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "remove":
                storage.delete(uuid);
                response.sendRedirect("meals");
                return;
            case "edit":
            case "update":
                meal = storage.get(uuid);
                request.setAttribute("meal", meal);
                break;
        }
        request.getRequestDispatcher(action.equals("edit") ? "create.jsp" : "meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uuid = request.getParameter("uuid");
        Meal meal;
        String date = request.getParameter("date");
        if (uuid != null && uuid.trim().length() != 0) {
            meal = storage.get(uuid);
            storage.update(getMeal(date, meal, request));
            response.sendRedirect("meals");
            return;
        } else {
            storage.save(getMeal(date, new Meal(), request));
        }
        response.sendRedirect("meals");
    }

    private Meal getMeal(String date, Meal meal, HttpServletRequest request) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        meal.setDateTime(localDateTime);
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        return meal;
    }
}
