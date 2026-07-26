package session5;


class BankSystem {
    private int totalAmount;
    private final Object lock = new Object();

    public BankSystem(int amount) {
        totalAmount = amount;
    }

    public void deposit() {
        synchronized (lock) {
            totalAmount++;
        }
    }

    public void withdraw() {
        synchronized (lock) {
            totalAmount--;
        }
    }

    public synchronized int getTotalAmount() {
        return totalAmount;
    }
}

class AddToBankTask implements Runnable {
    private final BankSystem bank;

    public AddToBankTask(BankSystem bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
                bank.deposit();
        }
    }
}

class WithdrawFromBankTask implements Runnable {
    private final BankSystem bank;

    public WithdrawFromBankTask(BankSystem bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            bank.withdraw();
        }
    }
}

public class Sync {
    static void main() {
        BankSystem myBank = new BankSystem(10000000);
        BankSystem myBank2 = new BankSystem(100000);
        AddToBankTask bt1 = new AddToBankTask(myBank);
        WithdrawFromBankTask bt2 = new WithdrawFromBankTask(myBank);

        Thread t1 = new Thread(bt1);
        Thread t2 = new Thread(bt2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(myBank.getTotalAmount());
    }
}
