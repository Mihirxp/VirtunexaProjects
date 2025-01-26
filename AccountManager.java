public class AccountManager {

    private DatabaseHelper dbHelper;

    public AccountManager(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public boolean login(String username, String password) {
        try {
            Account account = dbHelper.getAccountByUsername(username);
            return account != null && account.getPassword().equals(password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createAccount(String username, String password, double balance) {
        try {
            Account account = new Account(username, password, balance);
            dbHelper.createAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccount(String username) {
        try {
            return dbHelper.getAccountByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deposit(Account account, double amount) {
        try {
            double newBalance = account.getBalance() + amount;
            dbHelper.updateBalance(account.getAccountId(), newBalance);
            dbHelper.createTransaction(account.getAccountId(), "deposit", amount);
            account.setBalance(newBalance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(Account account, double amount) {
        try {
            if (account.getBalance() >= amount) {
                double newBalance = account.getBalance() - amount;
                dbHelper.updateBalance(account.getAccountId(), newBalance);
                dbHelper.createTransaction(account.getAccountId(), "withdraw", amount);
                account.setBalance(newBalance);
            } else {
                System.out.println("Insufficient balance!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
