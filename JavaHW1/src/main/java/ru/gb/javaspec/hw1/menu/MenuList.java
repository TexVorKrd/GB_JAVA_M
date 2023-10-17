package ru.gb.javaspec.hw1.menu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Список Меню
 */
public class MenuList {
    private Map<Integer, Menu> menuList;

    /**
     * В конструкторе сразу определяется пункт меню
     * для выхода с ключем 0
     */
    public MenuList() {
        this.menuList = new HashMap<>();
        Menu exitSelect = new Menu("EXIT");
        exitSelect.setAction(() -> System.out.println("Выполнение программы завершно"));
        menuList.put(0, exitSelect);
    }

    /**
     * Добавляет пункт меню если такого ключа еще нет и если определена реакция пункта меню
     *
     * @param key  - ключ
     * @param menu - пункт меню
     * @return - true если пункт добавлен к списку меню
     */
    public boolean addMenu(Integer key, Menu menu) {
        if (menu == null || menu.getAction() == null) return false;
        if (this.menuList.containsKey(key)) return false;
        menuList.put(key, menu);
        return true;
    }

    /**
     * Возвращает списки меню
     * @return - список меню как HashMap
     */
    public Map<Integer, Menu> getMenuList() {
        return menuList;
    }

    /**
     * Добавление пункта меню. Перегруженый метод, сам определяет порядковый номер пункта меню
     * как следующий после максимального
     *
     * @param menu - пункт меню
     * @return true если элемент меню добавлен
     */
    public boolean addMenu(Menu menu) {
        if (menu == null || menu.getAction() == null) return false;
        Integer key = Collections.max(menuList.keySet()) + 1;
        return addMenu(key, menu);
    }

    /**
     * Вывод списка меню
     */
    public void menuPrint() {
        System.out.println("----------------");
        menuList.keySet()
                .stream()
                .sorted()
                .forEach((a) -> System.out.println(a + " - " + menuList.get(a).getName().toUpperCase()));
        System.out.println("----------------");

    }
}
