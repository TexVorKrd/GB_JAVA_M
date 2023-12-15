package ru.geekbrains.lesson3.hw;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Василий Иванов", 25, 4.7d);
        String path = "serTest.bin";

        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(student);
            System.out.println("Объект Student сериализован.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(student.toString());

        Student student1;
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            student = (Student) objectInputStream.readObject();
            System.out.println("Объект UserData десериализован.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(student.toString());
    }
}