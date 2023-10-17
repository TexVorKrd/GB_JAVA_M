package ru.gb.javaspec.hw1.cesar_crypto;

/**
 * Базоый функционал шифрования
 * Должно выполять условие msg==decrypt(crypt(msg))
 */
public interface Crypto {

    /**
     * Шифрованме входящего сообщения.
     * @param msg - шифруемое сообщение
     * @return - зашифрованное сообщение
     */
    public String crypt(String msg);


    /**
     * Расшифровка входящего сообщение
     * @param msg - зашифроанное соощение
     * @return расшифрованное сообщение
     */
    public String decrypt(String msg);
}
