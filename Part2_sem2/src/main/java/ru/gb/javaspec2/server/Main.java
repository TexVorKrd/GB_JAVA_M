package ru.gb.javaspec2.server;

import ru.gb.javaspec2.server.client.ClientGUI;
import ru.gb.javaspec2.server.server.*;

public class Main {
    public static void main(String[] args) {

        String path = "src/main/java/ru/gb/javaspec2/server/log.txt";
        RepositoryImpl repository= new RepositoryImpl(path);

        Server server = new ServerImpl(new ServerWindow(), repository);

        new ClientGUI(server);
        new ClientGUI(server);
    }
}