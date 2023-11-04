package ru.gb.javaspec.account;

public class CreditAccount extends AccountImpl{

    public static CreditAccount create(int id, int sum) {

        // Для генерации исключений в родительском классе
        AccountImpl.createAccount(id,sum);

        return new CreditAccount(id,sum);
    }

    private CreditAccount(int id, int sum) {
        super(id,sum);
        overDraft =100_000; //Кредитный лимит
    }

}