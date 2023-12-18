package ru.geekbrains.lesson4.task2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Program {

    private static Random random = new Random();

    /**
     *  Задача 2
     *  ========
     *  Запишите в файл следующие строки:
     *
     *  Анна=4
     *  Елена=5
     *  Марина=6
     * @param args
     */
    public static void main(String[] args) {
        prepareFile();
    }

    static void prepareFile(){
        try (FileWriter fileWriter = new FileWriter("name.txt")){
            fileWriter.write("Анна=4\n");
            fileWriter.write("Елена=5\n");

            if (random.nextInt(2) == 0){ //50%
                throw new Exception("Error!");
            }

            fileWriter.write("Марина=6\n");

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
