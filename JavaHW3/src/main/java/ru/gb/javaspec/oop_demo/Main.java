package ru.gb.javaspec.oop_demo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Worker> workerList = new ArrayList<>();
        workerList.add(new Freelancer(1998, "Василий", "Пупкин", "1223", 15));
        workerList.add(new Freelancer(1996, "Сергей", "Петров", "1223", 16));
        workerList.add(new Freelancer(1978, "Виталий", "Иванов", "1223", 20));
        workerList.add(new StaffWorker(1980, "Максим", "1223", 2000));
        workerList.add(new StaffWorker(1991, "Петр", "1223", 2100));
        workerList.add(new StaffWorker(1975, "Марина", "Степанова", "1223", 1950));
        workerList.add(new StaffWorker(1983, "Светлана", "Голубева", "1223", 2500));

        for (Worker worker : workerList) {
            System.out.println(worker.toString());
        }

        workerList.sort(Worker::compareTo);

        System.out.println("---");

        for (Worker worker : workerList) {
            System.out.println(worker.toString());
        }

        System.out.println("-++++++++-");
        WorkerList workerList1 = new WorkerList();
        for (Worker worker : workerList) {
            workerList1.add(worker);
        }

        for (Worker worker : workerList1) {
            System.out.println(worker.toString());
        }
    }
}