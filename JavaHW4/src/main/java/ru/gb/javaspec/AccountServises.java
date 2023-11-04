package ru.gb.javaspec;

import ru.gb.javaspec.account.AccountImpl;
import ru.gb.javaspec.account.CreditAccount;
import ru.gb.javaspec.account.DebitAccount;

/**
 * Обработчик ошибок
 * Вынесен из мейна
 */
public class AccountServises {

    public static CreditAccount createCreditAcc(int accNumber, int deposit){
        CreditAccount acc;
        try {
            acc =CreditAccount.create(accNumber,deposit);
        }catch (IllegalArgumentException e){
            System.out.print("Создание аккаунта невозможно  ");
            System.out.printf("ERR MSG - \"%s\"\n", e.getMessage());
            return null;
        }
        System.out.printf("Аккаунт {%d} успешно создан, начальный баланс равен = %d \n",accNumber,deposit);
        return acc;
    }
    public static DebitAccount createDebitAcc(int accNumber, int deposit){
        DebitAccount acc;
        try {
            acc = DebitAccount.create(accNumber,deposit);
        }catch (IllegalArgumentException e){
            System.out.print("Создание аккаунта невозможно  ");
            System.out.printf("ERR MSG - \"%s\"\n", e.getMessage());
            return null;
        }
        System.out.printf("Аккаунт {%d} успешно создан, начальный баланс равен = %d \n",accNumber,deposit);
        return acc;
    }

    /**
     * Пополнение счета с обработкой ошибок.
     * @param acc - счет.
     * @param sum - сумма пополнения.
     */
    public static void addMoney(AccountImpl acc, int sum){

        if (acc==null){
            System.out.println("---\nСчет не существует\n---");
            return;
        }
        StringBuilder msg= new StringBuilder();
        msg
                .append("---\n")
                .append("Операция пополнения счета [ ")
                .append(acc.getAccountID())
                .append(" ] ");

        try{
            acc.addDeposite(sum);
        }catch (IllegalArgumentException e){

            msg
                    .append("\nОперация прервана\n").append("ERR MSG - \"")
                    .append(e.getMessage())
                    .append("\n---");
            System.out.println(msg);
            return;
        }
        msg
                .append("на сумму [ ").append(sum)
                .append(" ] прошла успешно\n")
                .append("Баланс = ")
                .append(acc.getBalance())
                .append("\n---");
        System.out.println(msg);
    }

    public static void withdrawMoney(AccountImpl acc, int sum){

        if (acc==null){
            System.out.println("---\nСчет не существует\n---");
            return;
        }
        StringBuilder msg= new StringBuilder();
        msg
                .append("---\n")
                .append("Операция списания средств со счета [ ")
                .append(acc.getAccountID())
                .append(" ] ");

        try{
            acc.withdrawMoney(sum);
        }catch (IllegalArgumentException e){

            msg
                    .append("\nОперация прервана\n").append("ERR MSG - \"")
                    .append(e.getMessage())
                    .append("\n---");
            System.out.println(msg);
            return;
        }
        msg
                .append("сумму [ ").append(sum)
                .append(" ] прошла успешно\n")
                .append("Баланс = ")
                .append(acc.getBalance())
                .append("\n---");
        System.out.println(msg);
    }
}
