package ru.gb.javaspec2.server.server;

import ru.gb.javaspec2.server.client.Client;

import java.util.LinkedList;
import java.util.List;

public class ServerImpl implements Server {

    ServerWindow serverWindow;

    List<Client> clientList;
    private boolean work;

    RepositoryImpl repository;

    public ServerImpl(ServerWindow serverWindow, RepositoryImpl repository) {
        clientList = new LinkedList<>();
        this.serverWindow = serverWindow;
        this.repository = repository;
        this.serverWindow.activateServerGUI();
        this.serverWindow.setServer(this);
        this.start();

    }

    @Override
    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clientList.add(client);
        return true;
    }

    @Override
    public void disconnectUser(Client client) {
        clientList.remove(client);
        if (client != null) {
            client.disconnect();
        }
    }

    @Override
    public void sendMessage(String text) {

        if (!work) {
            return;
        }

        this.serverWindow.appendLog(text);
        answerAll(text);
        repository.saveInLog(text);
    }

    private void answerAll(String text) {
        for (Client client : clientList) {
            client.getClientView().showMessage(text);
        }
    }

    @Override
    public ServerWindow getServer() {
        return serverWindow;
    }

    @Override
    public String getHistory() {
        return repository.readLog();
    }

    @Override
    public void stop() {
        this.serverWindow.stop(work);

        if (work) {
            while (!clientList.isEmpty()) {
                disconnectUser(clientList.get(clientList.size() - 1));
            }
            clientList.clear();
        }
        this.work = false;
    }

    @Override
    public void start() {
        this.serverWindow.start(work);
        this.work = true;
    }

}
