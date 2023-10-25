package ru.gb.javaspec.oop_demo;

import java.util.Comparator;


/**
 * Абстрактный класс сотрудника
 */
public abstract class Worker implements Comparable<Worker>, Comparator<Worker> {

    //Год рождения
    protected int yearOfBirthday;

    //Имя
    protected String firstName;

    //Фамилия
    protected String secondName;

    // ИНН
    protected String innId;

    //Ставка (зарплата)
    protected int salary;

    public Worker(int yearOfBirthday, String firstName, String secondName, String innId, int salary) {
        this.yearOfBirthday = yearOfBirthday;
        this.firstName = firstName;
        this.secondName = secondName;
        this.innId = innId;
        this.salary = salary;
    }

    public Worker(int yearOfBirthday, String firstName, String innId, int salary) {
        this.yearOfBirthday = yearOfBirthday;
        this.firstName = firstName;
        this.innId = innId;
        this.salary = salary;
    }

    /**
     * Компаратор по году рождения
     *
     * @param worker the object to be compared.
     * @return - стандартный вывод ля компаратора
     */
    @Override
    public int compareTo(Worker worker) {
        if (worker == null) return 1;
        if (worker == this || this.yearOfBirthday == worker.yearOfBirthday) return 0;
        return this.yearOfBirthday - worker.yearOfBirthday;
    }

    /**
     * Реализация сравнения двух объектов на основе реализации CompateTo
     *
     * @param worker1 the first object to be compared.
     * @param worker2 the second object to be compared.
     * @return - стандартный вывод компаратора.
     */
    @Override
    public int compare(Worker worker1, Worker worker2) {
        return worker1.compareTo(worker2);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "yearOfBirthday=" + yearOfBirthday +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", innId=" + innId +
                ", salary=" + salary +
                '}';
    }

    /**
     * Возвращает размер среднемесячной зарплаты.
     *
     * @return - Среднемесячная зарплата
     */
    abstract public double avrSallary();

    /**
     * Возвращает имя сотрудника.
     *
     * @return - Имя
     */
    public String getFirstName() {
        return firstName;
    }
}
