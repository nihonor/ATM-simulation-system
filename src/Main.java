public class Main {
    public static void main(String[] args) {

        DB db = new DB();


        Bank bank = new Bank(db);


        bank.addUser(new User(20, "honore", "password123", 1000.00));
        bank.addUser(new User(21, "niyitanga", "securepass", 1500.00));

        ATM atm = new ATM(bank);
        atm.displayMenu();


        db.closeConnection();
    }
}
