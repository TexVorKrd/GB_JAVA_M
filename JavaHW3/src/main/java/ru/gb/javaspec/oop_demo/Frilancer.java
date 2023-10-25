package ru.gb.javaspec.oop_demo;

public class Frilancer extends Worker {

    int salary;
    public Frilancer(int yearOfBirthday, String firstName, String innId,int salary) {
        super(yearOfBirthday, firstName, innId,salary);
    }

    public Frilancer(int yearOfBirthday, String firstName, String secondName, String innId,int salary) {
        super(yearOfBirthday, firstName, secondName, innId,salary);

    }

    @Override
    public double avrSallary() {
        return 20.8D * 8 * super.salary;
    }
}
