package ru.gb.javaspec.hw1.controler;

import ru.gb.javaspec.hw1.cesar_crypto.Crypto;
import ru.gb.javaspec.hw1.menu.Menu;
import ru.gb.javaspec.hw1.menu.MenuList;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Демонстрация работы шифрования дешифрования
 */
public class ConrolerDemoEmulation {

    //Кодировщик
    Crypto coder;

    //Список пунктов меню с действиями
    MenuList menuList;

    //Строка для демонстрации шифрования
    String msg = null;

    //Состояние отражающее зшифрован объект или нет
    boolean isCrypt;

    /**
     * Конструктор контролера
     * Проверяет системму шифрования на корректность, если системма шифрования некорректна
     * ограничивает пунты меню только выбором
     *
     * @param coder - принимает систему шифрования.
     */
    public ConrolerDemoEmulation(Crypto coder) {
        this.coder = coder;
        String test = "тестирование алгоритма шифрования - вврщащйшращиigghbkhb";
        if (test.equals(coder.decrypt(coder.crypt(test)))) {
            menuCreater();
        } else {
            System.out.println("Алгоритм шифрования не подтвержден");
        }

    }

    /**
     * Создает пункты меню для конкретного контролера
     */

    private void menuCreater() {
        /**
         * Обработка меню ввода сообщения
         */
        menuList = new MenuList();
        Menu inputMsg = new Menu("Ввод сообщения");
        inputMsg.setAction(() -> {
            System.out.println("Ввод сообщения для шифрования. Если сообщение уже есть оно будет стерто");
            this.msg = "new Scanner(System.in).nextLine() не работает нормально";
            isCrypt = false;
        });
        menuList.addMenu(inputMsg);


        /**
         * Обработка меню шифрования
         */
        Menu cryptMsg = new Menu("шифрование");
        cryptMsg.setAction(() -> {
            System.out.println("Выполнение шифрования");

            if (msg == null) {
                System.out.println("Сначала введите сообщение. Шифрование невозможно");
            } else if (isCrypt) {
                System.out.println("Сообщение уже зашифровано");
            } else {
                this.msg = coder.crypt(this.msg);
                System.out.println("Cообщение зашифровано");
                isCrypt = true;
            }
        });
        menuList.addMenu(cryptMsg);

        /**
         * Обработка меню расшифровки
         */
        Menu encryptMsg = new Menu("расшифровать");
        encryptMsg.setAction(() -> {
            System.out.println("Выполнение расшифровки");
            if (msg == null) {
                System.out.println("Сначала введите сообщение. Шифрование невозможно");
            } else if (!isCrypt) {
                System.out.println("Сообщение не зашифровано");
            } else {
                this.msg = coder.decrypt(this.msg);
                System.out.println("Cообщение расшифровано");
                isCrypt = false;
            }
        });
        menuList.addMenu(encryptMsg);
    }

    /**
     * Запуск демонстрации работы системы шмфрования
     */
    public void lanch() throws InterruptedException {
        System.out.println("Демо шифрования/дешифрования сообщения");
        Queue<String> emulate = new ArrayDeque<>();

        emulate.add("1");
        emulate.add("2");
        emulate.add("2");
        emulate.add("3");
        emulate.add("3");
        emulate.add("0");


        boolean isExit = false;
        StringBuilder view;
        String selectMenu;
        selectMenu = emulate.poll();
        while (!isExit) {

            view = new StringBuilder();
            view.append("----------------\n")
                    .append("MSG=");

            if (msg != null) {
                view.append(msg);
            } else {
                view.append("{Сообщение пока не задано}");
            }

            System.out.println(view.toString());
            menuList.menuPrint();


            try {
                int sMenu = Integer.parseInt(selectMenu);
                if (sMenu == 0) isExit = true;
                if (menuList.getMenuList().containsKey(sMenu)) {
                    menuList.getMenuList().get(sMenu).getAction().run();
                } else {
                    System.out.println("Выберите доступный пункт меню");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Выберите корректно пункт меню");
            }
            selectMenu=emulate.poll();
        }
    }
}
