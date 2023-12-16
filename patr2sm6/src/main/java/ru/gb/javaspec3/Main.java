package ru.gb.javaspec3;

public class Main {
    public static void main(String[] args) {


        System.out.println(
                MontyHall.getSatistic(
                        MontyHall.tryes(100,false,true)
                )
        );

        System.out.println(
                MontyHall.getSatistic(
                        MontyHall.tryes(500,false,false)
                )
        );

        System.out.println(
                MontyHall.getSatistic(
                        MontyHall.tryes(10000,false,false)
                )
        );

        System.out.println(
                MontyHall.getSatistic(
                        MontyHall.tryes(10000,true,false)
                )
        );
    }
}