package ru.gb.javaspec.hw1.menu;

/**
 * Элемент Меню для контролера
 */
public class Menu {
    private String name;
    private Runnable action;

    public Menu(String name) {
        this.name = name;
        action=null;
    }

    /**
     * Описание действия в пункте меню
     * @param action - Runable функциональный интерфейс
     */
    public void setAction(Runnable action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public Runnable getAction() {
        return action;
    }
}
