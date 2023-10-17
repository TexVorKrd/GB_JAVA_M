package ru.gb.javaspec.hw1.cesar_crypto;

/**
 * Реализация алгоритма шифрования цезаря черз смещение посимвольно
 */
public class CesarCryptoImpl implements Crypto {


    //Смещение по алгоритму Цезаря
    int key;

    /**
     * Конструктор для создания шифратора/дешифратора
     *
     * @param key - ключ шифрования
     */
    public CesarCryptoImpl(int key) {
        this.key = key;
    }

    /**
     * Шифорование сообщения.
     * Работает на люпом смещении в размере параметка int
     *
     * @param msg - шифруемое сообщение
     * @return - зашифрованное сообщение
     */
    @Override
    public String crypt(String msg) {

        StringBuilder cryptMsg = new StringBuilder();
        long q;

        for (int i = 0; i < msg.length(); i++) {
            q = ((int) msg.charAt(i) + this.key);
            cryptMsg.append((char) q);
        }
        return cryptMsg.toString();
    }

    /**
     * Расшифровыает сообщение
     *
     * @param msg - зашифроанное соощение
     * @return - расшифрованное сообщение
     */
    @Override
    public String decrypt(String msg) {

        long q = 0;
        StringBuilder cryptMsg = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            q = (int) msg.charAt(i) - this.key;
            cryptMsg.append((char) q);
        }
        return cryptMsg.toString();
    }
}
