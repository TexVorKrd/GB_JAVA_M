package ru.gb.javaspec3;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class MontyHall {

    /**
     * Проверка гипотезы Монти Холла.
     *
     * @param numberOfAttempts - Число попыток. Должно быть больше 0.
     * @param isChange         - Менять дверь после открытия двери ведущим.
     * @param isPrintLog       - печатать или нет лог.
     * @return - Результат эксперемента.
     */
    public static HashMap<Integer, Boolean> tryes(int numberOfAttempts, boolean isChange, boolean isPrintLog) {
        if (numberOfAttempts < 1) return null;
        Random random = new Random();
        HashMap<Integer, Boolean> allAttempts = new HashMap<>();

        for (int i = 0; i < numberOfAttempts; i++) {
            boolean[] currentTry = newTry();
            StringBuilder stepInfo = new StringBuilder();

            //Выбор игрока
            int playerChoice = random.nextInt(0, 3);

            //Выбор двери ведущим
            Integer[] keyArray = Stream.of(0, 1, 2)
                    .filter(a -> a != playerChoice)
                    .filter(a -> !currentTry[a])
                    .toArray(Integer[]::new);

            int masterOpen = keyArray[random.nextInt(0, keyArray.length)];

            stepInfo
                    .append(i + 1)
                    .append(". Игрок выбрал дверь ")
                    .append(playerChoice)
                    .append(", ведущий открыл ")
                    .append(masterOpen)
                    .append(", осталась дверь ")
                    .append(3 - playerChoice - masterOpen);

            //Окончательный выбор бвери играком.
            int playerNewChoice;
            if (isChange) {
                playerNewChoice = 3 - playerChoice - masterOpen;
                stepInfo
                        .append(". Игрок сменил выбор на ")
                        .append(playerNewChoice);
            } else {
                playerNewChoice = playerChoice;
                stepInfo
                        .append(". Игрок не стал менять дверь");
            }

            //Проверка результата.
            if (currentTry[playerNewChoice]) {
                stepInfo
                        .append(". Игрок Выиграл");
            } else {
                stepInfo
                        .append(". Игрок Проиграл");
            }

            allAttempts.put(i, currentTry[playerNewChoice]);

            if (isPrintLog) System.out.println(stepInfo);
        }

        //Добавляем информацию о том была ли смена двери или нет.
        allAttempts.put(-1, isChange);

        return allAttempts;
    }

    /**
     * Создание новой попытки.
     *
     * @return - массив из трех элементов один из которых истина.
     */
    private static boolean[] newTry() {
        boolean[] newTry = new boolean[3];
        newTry[new Random().nextInt(0, 2)] = true;
        return newTry;
    }

    /**
     * Вывод статистики из данных по испытаниям
     *
     * @param test - HashMap с номером испытания и результатом
     * @return - Строка с информацией
     */
    public static String getSatistic(HashMap<Integer, Boolean> test) {
        if (!test.containsKey(-1)) return ("Неверные данные");
        boolean isChange = test.get(-1);
        test.remove(-1);
        int totalTries = test.size();
        long winTries = test.values()
                .stream()
                .filter(a -> a)
                .count();

        StringBuilder info = new StringBuilder("\n****************************************************\nБыло проведено ");
        info
                .append(totalTries)
                .append(" испытаний. Выиграно ")
                .append(winTries);
        if (isChange) {
            info.append(". Игрок всегда менял дверь");
        } else {
            info.append(". Игрок всегда оставля выбор");
        }
        info
                .append(". Процент выигышей ")
                .append(String.format("%.2f \n****************************************************", (double) winTries / totalTries * 100));
        return info.toString();
    }

}
