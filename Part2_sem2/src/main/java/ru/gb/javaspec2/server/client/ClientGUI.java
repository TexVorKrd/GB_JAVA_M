package ru.gb.javaspec2.server.client;

import ru.gb.javaspec2.server.server.Server;
import ru.gb.javaspec2.server.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class ClientGUI extends JFrame implements ClientView{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    /**
     * перенос
     */
    private ServerWindow server;
    private boolean connected;
    private String name;

    private JTextArea log;
    private JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    private JPasswordField password;
    private JButton btnLogin, btnSend;
    private JPanel headerPanel;

    private Client client;

    public ClientGUI(Server server) {
        client = new Client(this,server); // ???

        this.server= server.getServer();

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(this.server.getX() - 500, this.server.getY());

        createPanel();

        setVisible(true);
    }

    public void answer(String text){
        appendLog(text);
    }

    private void connectToServer() {
        if (client.connectToServer(tfLogin.getText())){
            headerPanel.setVisible(false);
        }

    }

    @Override
    public void showMessage(String text) {
        appendLog(text);
    }

    @Override
    public void disconnectFromServer() {
        headerPanel.setVisible(true);
        client.disconnect();
    }

    public void sendMessage(){
        client.sendMessage(tfMessage.getText());
        tfMessage.setText("");
    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()=='\n'){
                    sendMessage();
            }
        }
    });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectFromServer();
        }
    }
}
