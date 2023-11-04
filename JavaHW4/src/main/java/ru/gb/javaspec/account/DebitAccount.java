package ru.gb.javaspec.account;

public class DebitAccount extends AccountImpl{
    public static DebitAccount create(int id, int sum) {

        // Для генерации исключений в родительском классе
        AccountImpl.createAccount(id,sum);
        return new DebitAccount(id,sum);
    }

    private DebitAccount(int accountID, int balance) {
        super(accountID, balance);
    }
}
