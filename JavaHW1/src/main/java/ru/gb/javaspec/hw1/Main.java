package ru.gb.javaspec.hw1;

import ru.gb.javaspec.hw1.cesar_crypto.CesarCryptoImpl;
import ru.gb.javaspec.hw1.controler.ConrolerDemo;
import ru.gb.javaspec.hw1.controler.ConrolerDemoEmulation;

public class Main {
    public static void main(String[] args) {

        CesarCryptoImpl crypto = new CesarCryptoImpl((int)(Math.random()*1234));
        ConrolerDemoEmulation conroler = new ConrolerDemoEmulation(crypto);
        try {
            conroler.lanch();
        }
        catch (InterruptedException e){

        }


    }
}