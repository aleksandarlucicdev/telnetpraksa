package dom.paketi;

public class Account {
    private double balance = 0;
    private double interest = 0.02;
    private int accountNumber;
    private static int numberOfAccounts = 1000000;

    Account(){
        accountNumber = numberOfAccounts++; // svaki put kada se kreira novi nalog, povecava se cifra numberOfAccounts

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterest() {
        return interest * 100;
    } // da bi se dobio procenat

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void withdraw (double amount){
        if(amount + 5 > balance){
            System.out.println("You dont have enough money on your account");
            return;
        }
        balance -= amount + 5;
        checkInterest(0);
        System.out.println("You have withdrawn" + amount + "dollars and added fee of 5 dollars");
        System.out.println("You now have a balance of:" + balance);
    }

    public void deposit (double amount){
        if (amount <=0){
            System.out.println("You cant deposit negative money");
            return;

        }

        checkInterest(amount);
        amount = amount + amount * interest;
        balance += amount;

        System.out.println("You have deposited" + amount + "dollars with an interest rate of " + (interest*100) + "%"); // da bi bio procenat, mora se pomnoziti sa 100
        System.out.println("You now have a balance of:" + balance);

    }

    public void checkInterest(double amount){
        if(balance + amount>10000){
            interest = 0.05;
        }else{
            interest = 0.02;
        }
    }
}
