import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        double amount, convertedAmount;

        while (true) {
            System.out.println("\n=== Currency Converter ===");
            System.out.println("1. INR to USD");
            System.out.println("2. USD to INR");
            System.out.println("3. INR to EUR");
            System.out.println("4. EUR to INR");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Exiting... Thank you!");
                break;
            }

            System.out.print("Enter amount: ");
            amount = sc.nextDouble();

            switch (choice) {
                case 1:
                    convertedAmount = amount * 0.012;
                    System.out.println("Converted Amount: $" + convertedAmount);
                    break;

                case 2:
                    convertedAmount = amount * 83;
                    System.out.println("Converted Amount: ₹" + convertedAmount);
                    break;

                case 3:
                    convertedAmount = amount * 0.011;
                    System.out.println("Converted Amount: €" + convertedAmount);
                    break;

                case 4:
                    convertedAmount = amount * 90;
                    System.out.println("Converted Amount: ₹" + convertedAmount);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
