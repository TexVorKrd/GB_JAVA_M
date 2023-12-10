package ru.geekbrains.lesson2.hw2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        List<Animal> animalsList = List.of(
                new Dog("Тузик", "Дворняга"),
                new Cat("Шустрик", "Рыжий"),
                new Dog("Бобик", "Овчарка"),
                new Cat("Барсик", "Белый"));
        Field[] fields;
        Method[] methods;

        for (Animal animal : animalsList) {

            Class<?> objClass = animal.getClass();
            System.out.println(objClass.getName());
            System.out.println("\nПоля.");
            boolean isStopClass = false;
            Class<?> stopClass = Animal.class;

            //Получаем поля из текущего класса и классов родителей пока не дойдем до указанного класса
            while (objClass != null && !isStopClass) {
                fields = objClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    System.out.printf("%s: %s\n", field.getName(), field.get(animal));
                }
                if (objClass.equals(stopClass)) {
                    isStopClass = true;
                } else {
                    objClass = objClass.getSuperclass();
                }
            }
            objClass = animal.getClass();

            System.out.println("\nМетоды.");
            methods = objClass.getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                String methodName = method.getName();
                System.out.printf("%s:", methodName);
                if (methodName.equals("makeSound")) {
                    method.invoke(animal);
                }
                System.out.println();
            }
            System.out.println("-------------------");
        }
    }
}