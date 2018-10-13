package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static List<MealWithExceed> mealsDAO;
    private MealDao meal = new MealDaoImpl();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static String LIST_MEAL = "/meals.jsp";
    private static String EDIT_MEAL = "/mealEdit.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String description = request.getParameter("description");
        String str = request.getParameter("datetime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(str, formatter);
        int calories = Integer.parseInt(request.getParameter("calories"));
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            meal.addMeal(new Meal(localDateTime,description,calories));
        }else {
            meal.updateMeal(new Meal(Integer.parseInt(id),localDateTime,description,calories));
        }

        mealsDAO = MealsUtil.getFilteredWithExceededByCycle(meal.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("mealList", mealsDAO);
        request.getRequestDispatcher("/meals.jsp").forward(request,response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action == null) {
            mealsDAO = MealsUtil.getFilteredWithExceededByCycle(meal.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("mealList", mealsDAO);
            forward = LIST_MEAL;
        } else if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            meal.deleteMeal(id);
            forward = LIST_MEAL;
            mealsDAO = MealsUtil.getFilteredWithExceededByCycle(meal.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("mealList", mealsDAO);
        } else if (action.equalsIgnoreCase("edit")) {
            int idMeal = Integer.parseInt(request.getParameter("id"));
            forward=EDIT_MEAL;
            Meal meal = this.meal.getMeal(idMeal);
            request.setAttribute("meal",meal);
        } else {
            forward=EDIT_MEAL;
        }
        request.setAttribute("formatter", formatter);
        request.getRequestDispatcher(forward).forward(request, response);

    }
}