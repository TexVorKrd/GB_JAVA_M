package ru.gb.javaspec.hw1;

import ru.gb.javaspec.hw1.cesar_crypto.CesarCryptoImpl;
import ru.gb.javaspec.hw1.controler.ConrolerDemo;

public class Main {
    public static void main(String[] args) {

        CesarCryptoImpl crypto = new CesarCryptoImpl((int)(Math.random()*1234));
        ConrolerDemo conroler = new ConrolerDemo(crypto);
        conroler.lanch();

    }
}