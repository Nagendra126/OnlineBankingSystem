import java.sql.*;
import java.util.*;

public class BankOperations {
    public void createAccount(Account acc) throws SQLException {
        String sql = "INSERT INTO accounts (account_no, holder_name, balance) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, acc.getAccountNo());
            ps.setString(2, acc.getHolderName());
            ps.setDouble(3, acc.getBalance());
            ps.executeUpdate();
        }
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Account a = new Account(
                    rs.getInt("account_no"),
                    rs.getString("holder_name"),
                    rs.getDouble("balance")
                );
                list.add(a);
            }
        }
        return list;
    }

    public void deposit(int accountNo, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, accountNo);
            ps.executeUpdate();
        }
    }

    public void withdraw(int accountNo, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE account_no = ? AND balance >= ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, accountNo);
            ps.setDouble(3, amount);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Insufficient balance or invalid account.");
            }
        }
    }

    public void deleteAccount(int accountNo) throws SQLException {
        String sql = "DELETE FROM accounts WHERE account_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountNo);
            ps.executeUpdate();
        }
    }
}
