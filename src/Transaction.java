public class Transaction {
    private String type; // "Deposit" or "Withdrawal"
    private double amount;
    private String timestamp;

    public Transaction(String type, double amount, String timestamp) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return type + ": " + amount + " on " + timestamp;
    }

    public static void recordTransaction(Dboperation dbOperation, int userId, String type, double amount) {
        try {
            String query = "INSERT INTO transactions (userId, type, amount) VALUES (?, ?, ?)";
            dbOperation.insert(query, userId, type, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
