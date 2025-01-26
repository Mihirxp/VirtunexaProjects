public class ExpenseManager {

    private DatabaseHelper dbHelper;

    public ExpenseManager(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void addExpense(Account account, double amount, String category, String description) {
        try {
            dbHelper.createExpense(account.getAccountId(), amount, category, description);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewExpenses(Account account) {
        try {
            ResultSet rs = dbHelper.getExpenses(account.getAccountId());
            while (rs.next()) {
                System.out.println("Amount: " + rs.getDouble("amount") + ", Category: " + rs.getString("category") + ", Description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
