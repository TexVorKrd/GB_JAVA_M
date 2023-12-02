package ru.geekbrains.lesson1.hw1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>();
        Random myRND = new Random();
        for (int i = 0; i <10 ; i++) {
            myList.add(myRND.nextInt(100));
        }
        System.out.println(myList);
        double s = myList.stream()
                .filter(a->a%2==0)
                .mapToInt(a->a)
                .average()
                .orElse(0);

        System.out.printf("Среднее арефметическое четных чисел равно %.2f",s);
    }
}
