package ru.geekbrains.lesson4.task1;

import java.util.Random;

public class Program {

    private static Random random = new Random();

    /**
     Задача 1
     ========
     1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
     При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
     2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
     Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
     должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
     3. В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и
     MyArrayDataException и вывести результат расчета.
     */

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            System.out.printf("\n***** Итерация #%d *****\n", i + 1);
            processArray();
        }
    }

    public static void processArray(){

        try {
            processArrayInternal(generateArray());
        }
        catch (MyArrayDataException e){
            System.out.printf("%s по индексу [%d][%d]\n", e.getMessage(), e.getX(), e.getY());

        }
        catch (MyArraySizeException e){
            System.out.printf("%s Требовалось 4x4, получили %dx%d\n", e.getMessage(), e.getX(), e.getY());

        }
        catch (CustomArrayException e){

        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * Получить сумму элементов массива
     * @param array массив
     * @return сумма элементов массива
     * @throws NullPointerException массив должен существовать
     * @throws MyArraySizeException некорректный размер массива
     * @throws MyArrayDataException Некорректный формат данных
     */
    private static int processArrayInternal(String[][] array) throws NullPointerException, MyArrayDataException, MyArraySizeException{
        if (array == null){
            throw new NullPointerException("Массив должен существовать");
        }
        for (int i = 0; i < array.length; i++){
            if (array[i] == null)
                throw new NullPointerException("Массив должен существовать");
        }
        if (array.length != 4 && array[0].length != 4) {
            throw new MyArraySizeException("Некорректный размер массива", array.length, array[0].length);
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                sum += parseElement(array[i][j], i, j);
            }
        }
        return sum;
    }

    /**
     * Вспомогательный метод, преобразует значение элемента к числу
     * @param s значение элемента
     * @param x индекс элемента в массиве
     * @param y индекс элемента в массиве
     * @return число
     * @throws MyArrayDataException Некорректный формат данных
     */
    private static int parseElement(String s, int x, int y) throws MyArrayDataException{
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            throw new MyArrayDataException("Некорректный формат данных", x, y);
        }
    }

    public static String[][] generateArray() {
        int add = random.nextInt(2); //50%
        String[][] array = new String[4 + add][4 + add];

        for (int i = 0; i < array.length; i++){
            for (int j= 0; j < array[i].length; j++){
                array[i][j] = Integer.toString(random.nextInt(10, 100));
            }
        }

        if (random.nextInt(3) == 0){ // 33%
            array[random.nextInt(array.length)][random.nextInt(array.length)] = "abc";
        }

        for (int i = 0; i < array.length; i++){
            for (int j= 0; j < array[i].length; j++){
                System.out.printf("%s\t", array[i][j]);
            }
            System.out.println();
        }

        return array;
    }

}
