import java.sql.*;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:account_expenses.db";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void createAccount(Account account) throws SQLException {
        String sql = "INSERT INTO Account(username, password, balance) VALUES(?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword());
            pstmt.setDouble(3, account.getBalance());
            pstmt.executeUpdate();
        }
    }

    public Account getAccountByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Account WHERE username = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getString("username"), rs.getString("password"), rs.getDouble("balance"));
                account.setAccountId(rs.getInt("account_id"));
                return account;
            }
        }
        return null;
    }

    public void updateBalance(int accountId, double newBalance) throws SQLException {
        String sql = "UPDATE Account SET balance = ? WHERE account_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountId);
            pstmt.executeUpdate();
        }
    }

    public void createTransaction(int accountId, String type, double amount) throws SQLException {
        String sql = "INSERT INTO Transaction(account_id, type, amount, date) VALUES(?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setString(2, type);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, java.time.LocalDate.now().toString());
            pstmt.executeUpdate();
        }
    }

    public void createExpense(int accountId, double amount, String category, String description) throws SQLException {
        String sql = "INSERT INTO Expense(account_id, amount, category, description, date) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, category);
            pstmt.setString(4, description);
            pstmt.setString(5, java.time.LocalDate.now().toString());
            pstmt.executeUpdate();
        }
    }

    public ResultSet getTransactionHistory(int accountId) throws SQLException {
        String sql = "SELECT * FROM Transaction WHERE account_id = ? ORDER BY date DESC";
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, accountId);
        return pstmt.executeQuery();
    }

    public ResultSet getExpenses(int accountId) throws SQLException {
        String sql = "SELECT * FROM Expense WHERE account_id = ? ORDER BY date DESC";
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, accountId);
        return pstmt.executeQuery();
    }
}
