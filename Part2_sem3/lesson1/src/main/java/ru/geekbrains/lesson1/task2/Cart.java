package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private  ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing() {
        if (foodstuffs.stream().filter(Objects::nonNull).noneMatch(Food::getProteins)) {
            foodstuffs.add(
                    (T) market.getThings(Food.class).stream()
                            .filter(Objects::nonNull)
                            .filter(Food::getProteins)
                            .findFirst()
                            .orElse(null));
        }

        if (foodstuffs.stream().filter(Objects::nonNull).noneMatch(Food::getFats)) {
            foodstuffs.add(
                    (T) market.getThings(Food.class).stream()
                            .filter(Objects::nonNull)
                            .filter(Food::getFats)
                            .findFirst()
                            .orElse(null));
        }
        foodstuffs.add(null);

        if (foodstuffs.stream().filter(Objects::nonNull).noneMatch(Food::getCarbohydrates)) {
            foodstuffs.add(
                    (T) market.getThings(Food.class).stream()
                            .filter(Objects::nonNull)
                            .filter(Food::getCarbohydrates)
                            .findFirst()
                            .orElse(null));
        }

        if (foodstuffs.stream().noneMatch(Objects::isNull))
            System.out.println("Корзина сбалансирована по БЖУ.");
        else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

            foodstuffs = new ArrayList<T>(foodstuffs.stream().filter(Objects::nonNull).toList());
        }

    }


    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs()
    {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }

}
