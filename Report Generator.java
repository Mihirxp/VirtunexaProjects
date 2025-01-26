import java.sql.*;

public class ReportGenerator {

    private DatabaseHelper dbHelper;

    public ReportGenerator(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void generateExpenseReport(Account account) {
        try {
            ResultSet rs = dbHelper.getExpenses(account.getAccountId());
            double total = 0.0;
            while (rs.next()) {
                total += rs.getDouble("amount");
            }
            System.out.println("Total Expenses: " + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
