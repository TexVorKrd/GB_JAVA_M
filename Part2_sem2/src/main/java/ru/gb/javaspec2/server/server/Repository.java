package ru.gb.javaspec2.server.server;

public interface Repository {

    /**
     * Запись лога
     *
     * @param text - лог
     */
    void saveInLog(String text);

    /**
     * Чтение логоа
     *
     * @return - лог
     */
    String readLog();

}
