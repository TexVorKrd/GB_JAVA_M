package ru.gb.javaspec.oop_demo;

public class Freelancer extends Worker {

    public Freelancer(int yearOfBirthday, String firstName, String innId, int salary) {
        super(yearOfBirthday, firstName, innId, salary);
    }

    public Freelancer(int yearOfBirthday, String firstName, String secondName, String innId, int salary) {
        super(yearOfBirthday, firstName, secondName, innId, salary);
    }

    /**
     * Реализация расчета среднемесячной зарплаты для обслуживающего персонала.
     *
     * @return - Среднемесячная зарплата
     */
    @Override
    public double avrSallary() {
        return 20.8D * 8 * super.salary;
    }
}
