package ru.gb.javaspec;


import ru.gb.javaspec.account.AccountImpl;
import ru.gb.javaspec.account.CreditAccount;
import ru.gb.javaspec.account.DebitAccount;

//Обработчик исключений для класса Акаунт.
import static ru.gb.javaspec.AccountServises.*;


/**
 * Программа должна позволять пользователю вводить начальный баланс счета, сумму депозита и сумму снятия средств.
 * При этом она должна обрабатывать следующие исключительные ситуации:
 * Попытка создать счет с отрицательным начальным балансом должна вызывать исключение IllegalArgumentException с соответствующим сообщением.
 * Попытка внести депозит с отрицательной суммой должна вызывать исключение IllegalArgumentException с соответствующим сообщением.
 * Попытка снять средства, сумма которых превышает текущий баланс, должна вызывать исключение InsufficientFundsException
 * с сообщением о недостаточных средствах и текущим балансом.
 *
 * Продемонстрируйте работу вашего приложения:
 * Программа должна обрабатывать все исключения с помощью конструкции try-catch, выводя соответствующие сообщения об ошибках.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Тестирование корректности создания счетов");
        DebitAccount account1 = createDebitAcc(1,1000);//Нормальный аккаунт
        DebitAccount account2 = createDebitAcc(1,100); //Нормальный аккаунт
        CreditAccount account3 = createCreditAcc(2,-100);//Неверный начальный остаток.
        CreditAccount account4 = createCreditAcc(2,0);//Нормальный аккаунт.

        System.out.println("\nТестирование поплнения счета");
        addMoney(account1,-100);//Отрицательное пополнения
        addMoney(null,100); //Не существует счета
        addMoney(account4,1000);//Нормальное пополнение

        System.out.println("\nТестирование списания счета");
        withdrawMoney(account1,-100);//Отрицательная сумма
        withdrawMoney(null,100);//нет существует счета
        withdrawMoney(account1,10000);//Списание сверх лимита с дебетового счета
        withdrawMoney(account4,125);//Нормальное списание
        withdrawMoney(account4,10000);// Списание с кредитного счета в пределах овердрафта
        withdrawMoney(account4,10000);// Списание с кредитного счета в пределах сверх овердрафта

        System.out.println("\nТестирование переводов");
        account4.sendMoney(account1,10000000); // Перевод сверх лимита
        account4.sendMoney(account2,1000); //Нормальный перевод
        account4.sendMoney(null,1000);//Перевод на несуществующий счет

    }
}