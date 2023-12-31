package ru.gb.javaspec.oop_demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Реализация итерируемого списка сотрудников, наследников Класса Worker.
 */
public class WorkerList implements Iterable<Worker> {
    List<Worker> workerList = new ArrayList<>();


    /**
     * Добавление сотрудника в список.
     *
     * @param worker - Сотрудник. Объект Класса Worker или наследники
     * @return - true если добавление прошло успешно.
     */
    public boolean add(Worker worker) {
        if (worker == null) return false;
        workerList.add(worker);
        return true;
    }

    /**
     * Самописный итератор
     *
     * @return - Iterator
     */

    int position;
    @Override
    public Iterator<Worker> iterator() {

        /*
        Проще конечно было бы сделать так
        return workerList.iterator();
         */

        position=0;
        return new Iterator<Worker>() {
            @Override
            public boolean hasNext() {
                if (workerList == null || position >= workerList.size()) return false;
                return true;
            }

            @Override
            public Worker next() {
                position++;
                return workerList.get(position - 1);
            }
        };
    }
}
