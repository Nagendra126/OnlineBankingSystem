public class Account {
    private int accountNo;
    private String holderName;
    private double balance;

    public Account(int accountNo, String holderName, double balance) {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNo() { return accountNo; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    public void setAccountNo(int accountNo) { this.accountNo = accountNo; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return accountNo + " | " + holderName + " | ₹" + balance;
    }
}
