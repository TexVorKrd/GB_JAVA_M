package ru.geekbrains.lesson4.task4;

import java.util.ArrayList;
import java.util.Iterator;

public class MyCollection implements Iterable<Employee>{

    private ArrayList<Employee> employees = new ArrayList<>();
    private int position;

    @Override
    public Iterator<Employee> iterator() {
        return new MyCollectionIterator();
    }

    class MyCollectionIterator implements Iterator<Employee>{

        @Override
        public boolean hasNext() {
            position++;
            return false;
        }

        @Override
        public Employee next() {
            return null;
        }

    }


}
