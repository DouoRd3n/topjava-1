package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.ListStorage;
import ru.javawebinar.topjava.storage.Storage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
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
//        String forward="/meals.jsp";
//        String action = request.getParameter("action");
//
//        if (action.equalsIgnoreCase("delete")){
//            String uuid= request.getParameter("uuid");
//            storage.delete(uuid);
//            forward = "/meals.jsp";
//            request.setAttribute("meals", storage.getAllMeals());
//        } else if (action.equalsIgnoreCase("edit")){
//
//        } else if (action.equalsIgnoreCase("meals")){
//            forward = "/meals";
//            request.setAttribute("meals", storage.getAllMeals());
//
//        }
//
//        RequestDispatcher view = request.getRequestDispatcher(forward);
//        view.forward(request, response);

//        request.setAttribute("meals", meals);
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal();
        String date = request.getParameter("date");
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
    }
}
