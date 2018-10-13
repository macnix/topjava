package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDaoImpl implements MealDao {
    private static AtomicInteger atomicInt = new AtomicInteger(0);
    private static ConcurrentMap<Integer,Meal> mapMeal = new ConcurrentHashMap<>();
    static {
        mapMeal.put(atomicInt.incrementAndGet(),new Meal(atomicInt.get(),LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mapMeal.put(atomicInt.incrementAndGet(),new Meal(atomicInt.get(),LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mapMeal.put(atomicInt.incrementAndGet(),new Meal(atomicInt.get(),LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mapMeal.put(atomicInt.incrementAndGet(),new Meal(atomicInt.get(),LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mapMeal.put(atomicInt.incrementAndGet(),new Meal(atomicInt.get(),LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mapMeal.put(atomicInt.incrementAndGet(),new Meal(atomicInt.get(),LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));

    }

    @Override
    public void addMeal(Meal meal) {
        int i = atomicInt.incrementAndGet();
        meal.setId(i);
        mapMeal.put(atomicInt.incrementAndGet(), meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        mapMeal.put(meal.getId(),meal);

    }

    @Override
    public void deleteMeal(int mealId) {
        mapMeal.remove(mealId);

    }

    @Override
    public Meal getMeal(int id) {
        return mapMeal.get(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(mapMeal.values());
    }
}