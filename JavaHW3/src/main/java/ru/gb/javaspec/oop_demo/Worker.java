package ru.gb.javaspec.oop_demo;

import java.util.Comparator;


public abstract class Worker implements Comparable<Worker>, Comparator<Worker> {



    protected int yearOfBirthday;
    protected String firstName;
    protected String secondName;
    protected String innId;
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


    @Override
    public int compareTo(Worker worker) {
        if (worker==null) return 1;
        if (worker==this||this.yearOfBirthday==worker.yearOfBirthday) return 0;
        return this.yearOfBirthday-worker.yearOfBirthday;
    }

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

    abstract public double avrSallary();

    public String getFirstName() {
        return firstName;
    }
}
