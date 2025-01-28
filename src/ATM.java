import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ATM {
    private Bank bank;
    private User currentUser;
    private Scanner scanner = new Scanner(System.in);
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        if (authenticateUser()) {
            boolean quit = false;
            while (!quit) {
                System.out.println("\n1. View Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. View Transaction History");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        displayBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        viewTransactionHistory();
                        break;
                    case 5:
                        quit = true;
                        System.out.println("Thank you for using the ATM!");
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    private boolean authenticateUser() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        currentUser = bank.validateUser(username, password);
        return currentUser != null;
    }

    private void displayBalance() {
        System.out.println("Current balance: " + currencyFormat.format(currentUser.getBalance()));
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            bank.updateBalance(currentUser.getUserId(), currentUser.getBalance());
            Transaction.recordTransaction(bank.dbOperation, currentUser.getUserId(), "Deposit", amount);
            System.out.println("Deposit successful! New balance: " + currencyFormat.format(currentUser.getBalance()));
        } else {
            System.out.println("Amount must be greater than zero.");
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= currentUser.getBalance()) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            bank.updateBalance(currentUser.getUserId(), currentUser.getBalance());
            Transaction.recordTransaction(bank.dbOperation, currentUser.getUserId(), "Withdrawal", amount);
            System.out.println("Withdrawal successful! New balance: " + currencyFormat.format(currentUser.getBalance()));
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : User.getTransactionHistory(bank.dbOperation, currentUser.getUserId())) {
            System.out.println(transaction.getDetails());
        }
    }
}
