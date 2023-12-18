package ru.geekbrains.lesson4.task3;

public abstract class Animal {

    String name;

    public Animal(String name) {
        this.name = name;
    }

    abstract void swim(int distance) throws AnimalSwimException;

    abstract void run(int distance) throws AnimalRunException;

}
