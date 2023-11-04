package ru.gb.javaspec.account;


public class Transaction <T extends AccountImpl> {

    String ERR_TR_NULL = "Один или оба счета не существуют";
    String ERR_TR_WITHDRAW = "Ошибка списания";
    String ERR_TR_ADD = "Ошибка зачисления";

    /**
     * Перевод средств со счета accFrom на accTo
     *
     * @param accFrom - Счет с которого переводятся деньги.
     * @param accTo - Счет на который переводятся деньги.
     * @param sum - Сумма перевода
     * @throws NullPointerException - В случае если хотя бы один из счетов не существует.
     * @throws IllegalArgumentException - В случае ошибки списания или зачисления средств.
     */
    public void transaction(T accFrom, T accTo, int sum) throws NullPointerException, IllegalArgumentException {

        if (accFrom == null || accTo == null) throw new NullPointerException(ERR_TR_NULL);

        //Пытаемся списать деньги
        try {
            accFrom.withdrawMoney(sum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("%s\n%s", ERR_TR_WITHDRAW, e.getMessage()));
        }

        //Пытаемся зачислить
        try {
            accTo.addDeposite(sum);
        } catch (IllegalArgumentException e) {
            accFrom.addDeposite(sum);
            throw new IllegalArgumentException(String.format("%s\n%s", ERR_TR_ADD, e.getMessage()));
        }
    }
}