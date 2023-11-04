package ru.gb.javaspec.account;

public class InsufficientFundsException extends IllegalArgumentException{

    public InsufficientFundsException(String message) {
        super(message);
    }
}
