package ru.gb.javaspec2.server;

import ru.gb.javaspec2.server.client.ClientGUI;
import ru.gb.javaspec2.server.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}