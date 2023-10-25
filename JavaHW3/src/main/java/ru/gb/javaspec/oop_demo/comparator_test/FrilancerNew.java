package ru.gb.javaspec.oop_demo.comparator_test;

import ru.gb.javaspec.oop_demo.Freelancer;
import ru.gb.javaspec.oop_demo.Worker;

public class FrilancerNew extends Freelancer {
    public FrilancerNew(int yearOfBirthday, String firstName, String innId, int salary) {
        super(yearOfBirthday, firstName, innId, salary);
    }

    public FrilancerNew(int yearOfBirthday, String firstName, String secondName, String innId, int salary) {
        super(yearOfBirthday, firstName, secondName, innId, salary);
    }

    @Override
    public int compareTo(Worker worker) {
        return this.firstName.compareTo(worker.getFirstName());
    }

    @Override
    public int compare(Worker worker1, Worker worker2) {
        return worker1.compareTo(worker2);
    }
}
