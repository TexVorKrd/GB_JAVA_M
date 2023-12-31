package ru.gb.javaspec.oop_demo;

public class StaffWorker extends Worker {

    int salary;

    public StaffWorker(int yearOfBirthday, String firstName, String innId, int salary) {
        super(yearOfBirthday, firstName, innId, salary);
    }

    public StaffWorker(int yearOfBirthday, String firstName, String secondName, String innId, int salary) {
        super(yearOfBirthday, firstName, secondName, innId, salary);
        this.salary = salary;
    }

    /**
     * Реализация расчета среднемесячной зарплаты для обслуживающего персонала.
     *
     * @return - Среднемесячная зарплата
     */
    @Override
    public double avrSallary() {
        return salary;
    }
}
