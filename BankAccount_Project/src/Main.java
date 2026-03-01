import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("==== Create Your Bank Account ====");
        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.print("Enter account number: ");
        String accNumber = input.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance = input.nextDouble();

        BankAccount account = new BankAccount(name, accNumber, initialBalance);

        int choice;

        do {
            System.out.println("\n==== Mini Bank Menu ====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = input.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = input.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;

                case 4:
                    account.displayInfo();
                    break;

                case 5:
                    System.out.println("Thank you for using Mini Bank System!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        input.close();
    }
}