package ru.gb.javaspec2.server.server;

import ru.gb.javaspec2.server.client.Client;

public interface Server {

    /**
     * Подключение полльзователя к серверу
     *
     * @param client - Пользователь
     * @return - в случае успешного подключения true
     */
    boolean connectUser(Client client);

    /**
     * Отключение полльзователя от Сервера
     *
     * @param client - Пользователь
     * @return - в случае успешного отключения true
     */
    void disconnectUser(Client client);

    /**
     * Отправка сообщения
     *
     * @param text - Текст сообщения
     */
    void sendMessage(String text);

    /**
     * Кривая реализация из задания. Возврат GUI Сервера
     *
     * @return - GIU Сервера
     */
    ServerWindow getServer();

    /**
     * Лог истории сообщения
     *
     * @return - Лог
     */
    String getHistory();

    /*
    Остановка сервера
     */
    void stop();

    /*
     * Запуск Сервера
     */
    void start();

}
