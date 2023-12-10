package ru.geekbrains.lesson2.hw2;

public class Dog extends Animal {

    private String breed;

    public void catchCat(){

    }
    public void makeSound(){
        System.out.println("Gav!");
    }
    public Dog(String name,String breed) {
        super.name=name;
        this.breed=breed;
    }
}
