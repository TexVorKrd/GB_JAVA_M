package ru.gb.javaspec.hw1.servises;

public class Menu {
    private String name;
    private Runnable action;

    public Menu(String name) {
        this.name = name;
        action=null;
    }

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
