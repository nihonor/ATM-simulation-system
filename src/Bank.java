import java.sql.ResultSet;

public class Bank {
    public Dboperation dbOperation;

    public Bank(DB db) {
        this.dbOperation = new Dboperation(db);
    }

    public void addUser(User user) {
        try {
            String query = "INSERT INTO users (userId, username, password, balance) VALUES (?, ?, ?, ?)";
            dbOperation.insert(query, user.getUserId(), user.getUsername(), user.getPassword(), user.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User validateUser(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            ResultSet resultSet = dbOperation.get(query, username, password);
            if (resultSet.next()) {
                int userId = Integer.parseInt(resultSet.getString("userId"));
                double balance = resultSet.getDouble("balance");
                return new User(userId, username, password, balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBalance(int userId, double newBalance) {
        try {
            String query = "UPDATE users SET balance = ? WHERE userId = ?";
            dbOperation.put(query, newBalance, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
