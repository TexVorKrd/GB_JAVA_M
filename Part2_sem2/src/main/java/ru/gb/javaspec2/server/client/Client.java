package ru.gb.javaspec2.server.client;

import ru.gb.javaspec2.server.server.Server;
import ru.gb.javaspec2.server.server.ServerImpl;

public class Client {
    private String name;

    public ClientView getClientView() {
        return clientView;
    }

    private ClientView clientView;
    private Server serverImpl;
    private boolean connected;


    public Client(ClientView clientView, Server serverImpl) {
        this.clientView = clientView;
        this.serverImpl = serverImpl;
    }

    public boolean connectToServer(String name){
        this.name = name;
        if (serverImpl.connectUser(this)){
            printText("Вы успешно подключились!\n");
            connected = true;
            String log = serverImpl.getHistory();
            if (log != null){
                printText(log);
            }
            return true;
        } else {
            printText("Подключение не удалось");
            return false;
        }
    }
    public void disconnect(){
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            serverImpl.disconnectUser(this);
            printText("Вы были отключены от сервера!");
        }
    }

    //мы посылаем
    public void sendMessage(String message){
        if (connected) {
            if (!message.isEmpty()) {
                serverImpl.sendMessage(name + ": " + message);
            }
        } else {
            printText("Нет подключения к серверу");
        }
    }
    //нам посылают
    public void serverAnswer(String answer){
        printText(answer);
    }



    public String getName() {
        return name;
    }

    private void printText(String text){
        clientView.showMessage(text);
    }
}
