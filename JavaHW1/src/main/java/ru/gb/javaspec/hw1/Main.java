package ru.gb.javaspec.hw1;

import ru.gb.javaspec.hw1.cesar_crypto.CesarCryptoImpl;
import ru.gb.javaspec.hw1.servises.Conroler;
import ru.gb.javaspec.hw1.servises.MenuList;

public class Main {
    public static void main(String[] args) {


        CesarCryptoImpl crypto = new CesarCryptoImpl((int)(Math.random()*10000));

        String msg="Демонстрация работы алгоритма шифрования";
        String cryptMsg=crypto.crypt(msg);
        System.out.println(msg);
        System.out.println(cryptMsg);
        System.out.println();
        System.out.println(crypto.decrypt(cryptMsg));
        System.out.println(msg.equals(crypto.decrypt(crypto.crypt(msg))));

        Conroler conroler = new Conroler(crypto);
        conroler.lanch();
    }
}