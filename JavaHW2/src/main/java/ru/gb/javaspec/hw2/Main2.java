package ru.gb.javaspec.hw2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main2 {

    private static final char DOT_HUMAN = 'X'; // Фишка игрока - человека
    private static final char DOT_AI = '0'; // Фишка игрока - компьютер
    private static final char DOT_EMPTY = '*'; // Признак пустого поля
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field; // Двумерный массив хранит состояние игрового поля
    private static int fieldSizeX; // Размерность игрового поля
    private static int fieldSizeY; // Размерность игрового поля
    private static int winCount; // Кол-во фишек для победы
    private static final int MAX_ROWS = 9; //Максимальное количество строк
    private static final int MAX_COLS = 9; //Максимальное количество столбцов

    private static final int MIN_ROWS = 3; //Минимальное количество строк
    private static final int MIN_COLS = 3; //Минимальное количество столбцов
    private static final double RATIO_XY = 2; //Максимальное соотношение сторон

    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {

                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Победил компьютер!"))
                    break;

                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили!"))
                    break;

            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация начального состояния игры
     */
    private static void initialize() {
        System.out.println("Введите размер поля для игры через пробел ");

        do {
            System.out.printf("Количество строк в интервале от %d до %d\n", MIN_ROWS, MAX_ROWS);
            System.out.printf("Количество столбцов в интервале от %d до %d\n", MIN_COLS, MAX_COLS);
            System.out.printf("Соотношение сторон не должно превышать  %.2f\n", RATIO_XY);
            fieldSizeY = scanner.nextInt();
            fieldSizeX = scanner.nextInt();
        }
        while (fieldSizeY < MIN_ROWS || fieldSizeY > MAX_ROWS
                || fieldSizeX < MIN_COLS || fieldSizeX > MAX_ROWS
                || (double) fieldSizeX / (double) fieldSizeY > RATIO_XY
                || (double) fieldSizeY / (double) fieldSizeX > RATIO_XY);


        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            Arrays.fill(field[y], DOT_EMPTY);
        }

        int maxDotForWin = Math.min(fieldSizeY, fieldSizeX);

        do {
            System.out.printf("Введите число фишек для победы от %d до %d ", 3, maxDotForWin);
            winCount = scanner.nextInt();
        }
        while (!(winCount >= 3 && winCount <= maxDotForWin));

    }

    /**
     * Отрисовать текущее состояние игрового поля
     */
    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Обработка хода игрока (человека)
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.printf("Укажите координаты хода X (от 1 до %d) и Y (от 1 до %d)\nчерез пробел: ", fieldSizeX, fieldSizeY);
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[y][x] = DOT_HUMAN;
    }

    /**
     * Обработка хода компьютера рандом
     */
    static void aiTurnRandom() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[y][x] = DOT_AI;
    }

    /**
     * Слабенький AI
     */
    static void aiTurn() {

        int tokensForWin = winCount;

        // Ищем оптимальный ход от наибольшего количества фишек для выигрыша
        // до минимального количества строк минус 1
        while (tokensForWin >= MIN_COLS - 1) {

            //Ищем победный ход
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if (isCellEmpty(x, y)) {
                        field[y][x] = DOT_AI;
                        if (checkWin(DOT_AI, tokensForWin)) {
                            System.out.println("Нападение");
                            return;
                        } else {
                            field[y][x] = DOT_EMPTY;
                        }
                    }
                }
            }

            //Защита от проигрыша
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if (isCellEmpty(x, y)) {
                        field[y][x] = DOT_HUMAN;
                        if (checkWin(DOT_HUMAN, tokensForWin)) {
                            field[y][x] = DOT_AI;
                            System.out.println("Защита");
                            return;
                        } else {
                            field[y][x] = DOT_EMPTY;
                        }
                    }
                }
            }
            tokensForWin--;
        }
        aiTurnRandom();
    }


    /**
     * Проверка, ячейка является пустой (DOT_EMPTY)
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка состояния игры
     *
     * @param dot    фишка игрока
     * @param winStr победный слоган
     * @return признак продолжения игры (true - завершение игры)
     */
    static boolean gameCheck(char dot, String winStr) {
        if (checkWin(dot, winCount)) {
            System.out.println(winStr);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false; // Продолжим игру
    }

    /**
     * Проверка корректности ввода
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Проверка победы
     *
     * @param c фишка игрока (X или 0)
     * @return
     */
    static boolean checkWin(char c, int tokenCountForWin) {
        int tokensForWin = tokenCountForWin;

        for (int y = 0; y < fieldSizeY; y++) {

            for (int x = 0; x < fieldSizeX; x++) {

                /*
                 * Проверяем вправо. Начинаем проверку только если:
                 * - текущая фишка соответствует переданной;
                 * - справа достаточно фишек;
                 * - слева фишка отличная от переданной.
                 */
                if ((field[y][x] == c) && (fieldSizeX - x >= tokensForWin)) {

                    int count = 0;
                    for (int i = 1; i < tokensForWin; i++) {
                        if (field[y][x + i] == c) count++;
                    }
                    if (count == tokensForWin - 1) return true;
                }

                /*
                  Проверяем вниз. Начинаем проверку только если:
                  - текущая фишка соответствует переданной;
                  - снизу достаточно фишек;
                  - сверху фишка отличная от переданной.
                 */
                if ((field[y][x] == c) && (fieldSizeY - y >= tokensForWin)) {

                    int count = 0;
                    for (int i = 1; i < tokensForWin; i++) {
                        if (field[y + i][x] == c) count++;
                    }
                    if (count == tokensForWin - 1) return true;
                }

                /*
                 * Проверяем вправо-вниз. Начинаем проверку только если:
                 * - текущая фишка соответствует переданной;
                 * - снизу достаточно фишек;
                 * - сверху фишка отличная от переданной.
                 */
                if ((field[y][x] == c) && (fieldSizeY - y >= tokensForWin) && (fieldSizeX - x >= tokensForWin)) {

                    int count = 0;
                    for (int i = 1; i < tokensForWin; i++) {
                        if (field[y + i][x + i] == c) count++;
                    }
                    if (count == tokensForWin - 1) return true;
                }

                /*
                 * Проверяем влево-вниз. Начинаем проверку только если:
                 * - текущая фишка соответствует переданной;
                 * - снизу достаточно фишек;
                 * - сверху фишка отличная от переданной.
                 */
                if ((field[y][x] == c) && (fieldSizeY - y >= tokensForWin) && (x + 1 - tokensForWin >= 0)) {

                    int count = 0;
                    for (int i = 1; i < tokensForWin; i++) {
                        if (field[y + i][x - i] == c) count++;
                    }
                    if (count == tokensForWin - 1) return true;
                }

            }
        }
        return false;
    }

    static boolean check1(int x, int y, char dot, int win) {
        return true;
    }

    /**
     * Проверка на ничью
     *
     * @return
     */
    static boolean checkDraw() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellEmpty(i, j)) return false;
            }
        }
        return true;
    }

}
