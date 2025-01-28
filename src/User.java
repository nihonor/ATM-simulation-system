import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private String password;
    private double balance;

    public User(int userId, String username, String password, double balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static List<Transaction> getTransactionHistory(Dboperation dbOperation, int userId) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT type, amount, timestamp FROM transactions WHERE userId = ?";
            var resultSet = dbOperation.get(query, userId);
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                double amount = resultSet.getDouble("amount");
                String timestamp = resultSet.getString("timestamp");
                transactions.add(new Transaction(type, amount, timestamp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
