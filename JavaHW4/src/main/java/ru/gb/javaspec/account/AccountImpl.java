package ru.gb.javaspec.account;

public class AccountImpl implements Account {


    private static final String ERR_MSG_BADSTARTBALANCE = "Стартовый баланс не может быть отрицательным";
    private static final String ERR_MSG_BAD_ADD = "пополнение не может быть отрицательным";
    private static final String ERR_MSG_BAD_WITHDRAW = "Сумма снятия должна быть положительной";
    private static final String ERR_MSG_OVER_WITHDRAW = "Превышение суммы баланса";


    /**
     * Создает новый аккаунт при условии, что такого аккаунта еще нет, и начальный остаток не отрицательный.
     *
     * @param accountNumber - Номер аккаунта.
     * @param startBalance  - Стартовый баланс.
     * @return - Новый Аккаунт.
     * @throws IllegalArgumentException - Вызывает исключение если аккаунти ужу есть или стартовый баланс отрицательный.
     */
    public static AccountImpl createAccount(int accountNumber, int startBalance) throws IllegalArgumentException {

        if (startBalance < 0)
            throw new IllegalArgumentException(ERR_MSG_BADSTARTBALANCE);

        return new AccountImpl(accountNumber, startBalance);
    }

    protected int accountID;
    protected int balance = 0;
    protected int overDraft = 0;

    protected AccountImpl(int accountID, int balance) {
        this.accountID = accountID;
        this.balance = balance;
    }


    /**
     * Пополнение депозита. Сумма должна быть положительной.
     *
     * @param sum -сумма на которую пополнятся депозит.
     */
    @Override
    public void addDeposite(int sum) throws IllegalArgumentException {
        if (sum < 0)
            throw new IllegalArgumentException(ERR_MSG_BAD_ADD);
        balance += sum;
    }

    /**
     * Снятие средств с депозита.
     *
     * @param sum - снятия средств с депозита. Сумма должна быть положительной и меньше размера депозита.
     */
    @Override
    public void withdrawMoney(int sum) throws IllegalArgumentException, InsufficientFundsException {
        if (sum < 0)
            throw new IllegalArgumentException(ERR_MSG_BAD_WITHDRAW);
        if (sum > balance + overDraft)
            throw new InsufficientFundsException(
                    String.format("%s текущий баланс %d запрошено к списанию %d", ERR_MSG_OVER_WITHDRAW, balance, sum)
            );
        balance -= sum;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getBalance() {
        return balance;
    }

    /**
     * Перевод денег с текущего счета на указанный
     * @param accTo - Счет куда нужно перевести
     * @param sum - сумма перевода
     */
    public void sendMoney(AccountImpl accTo, int sum) {
        Transaction transaction = new Transaction();

        try {
            transaction.transaction(this, accTo, sum);
        } catch (RuntimeException e) {
            System.out.printf("\n====\nОшибка перевода со счета %d суммы %d\n", this.accountID, sum);
            System.out.println(e.getMessage());
            return;
        }
        System.out.printf("\n====\nПеревод со счета %d суммы %d на счет %d прошел успешно\n", this.accountID, sum, accTo.getAccountID());
    }
}
