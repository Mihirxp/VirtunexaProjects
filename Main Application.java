import java.util.Scanner;

public class MainApp {

    private static Scanner scanner = new Scanner(System.in);
    private static DatabaseHelper dbHelper = new DatabaseHelper();
    private static AccountManager accountManager = new AccountManager(dbHelper);
    private static ExpenseManager expenseManager = new ExpenseManager(dbHelper);
    private static ReportGenerator reportGenerator = new ReportGenerator(dbHelper);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Create Account\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter initial balance: ");
                double balance = scanner.nextDouble();
                scanner.nextLine();

                accountManager.createAccount(username, password, balance);
                System.out.println("Account created successfully!");
            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (accountManager.login(username, password)) {
                    Account account = accountManager.getAccount(username);
                    System.out.println("Login successful! Welcome " + account.getUsername());
                    
                    while (true) {
                        System.out.println("\n1. View Balance\n2. Deposit\n3. Withdraw\n4. View Expenses\n5. Generate Report\n6. Logout");
                        int action = scanner.nextInt();
                        scanner.nextLine(); 

                        if (action == 1) {
                            System.out.println("Balance: " + account.getBalance());
                        } else if (action == 2) {
                            System.out.print("Enter deposit amount: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine(); 
                            accountManager.deposit(account, amount);
                            System.out.println("Deposit successful!");
                        } else if (action == 3) {
                            System.out.print("Enter withdrawal amount: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine(); 
                            accountManager.withdraw(account, amount);
                            System.out.println("Withdrawal successful!");
                        } else