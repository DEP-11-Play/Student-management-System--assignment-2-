
import java.util.Scanner;

public class BankingApp {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "💰 Welcome to Smart Banking App ";
        final String OPEN_ACCOUNT = "Open New Account";
        // final String REMOVE_CUSTOMER = "Remove Customer";
        // final String PRINT_DETAILS = "Print Customer Details";
        System.out.println(DASHBOARD);

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        String[][] customers = new String[0][];
        // String[] customerNames = new String[0];
        // int[] account_id = new int[0];
        // double[] Balance = new double[0];

        do {
            final String APP_TITLE = String.format("%s%s%s",
                    COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("-".repeat(50));
            System.out.println(" ".repeat((50 - APP_TITLE.length() + 7) /
                    2).concat(APP_TITLE));
            System.out.println("-".repeat(50));

            switch (screen) {
                case DASHBOARD:
                    System.out.println("\n[1]. Open New Account");
                    System.out.println("[2]. Deposit Money");
                    System.out.println("[3]. Withdraw Money");
                    System.out.println("[4]. Transfer Money");
                    System.out.println("[5]. Check Account Balance");
                    System.out.println("[6]. Drop Existing Account");
                    System.out.println("[7]. Exit\n");
                    System.out.print("Enter an option to continue : ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option) {
                        case 1:
                            screen = OPEN_ACCOUNT;
                            break;
                        case 2:

                            break;
                        case 3:
                            // screen = PRINT_DETAILS;
                            break;
                        case 7:
                            System.exit(0);
                            break;
                        default:
                            continue;
                    }
                    break;

                case OPEN_ACCOUNT:
                    int id;
                    String name;
                    boolean valid;
                    double intialDeposit;

                    String[][] newCustomers = new String[customers.length + 1][3];

                    id = newCustomers.length;
                    System.out.printf("\t%s : SDB-%05ds \n", "Account Number", id);

                    // Name Validation
                    do {
                        valid = true;
                        System.out.print("\tEnter Customer Name: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()) {
                            System.out.printf(ERROR_MSG, "Customer name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) ||
                                    Character.isSpaceChar(name.charAt(i)))) {
                                System.out.printf(ERROR_MSG, "Invalid name");
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);

                    do {
                        valid = true;
                        System.out.print("\tIntial Deposit : ");
                        intialDeposit = SCANNER.nextDouble();
                        SCANNER.nextLine();
                        if (intialDeposit < 5000) {
                            System.out.printf(ERROR_MSG, "Insufficient Amount");
                            valid = false;
                            continue;
                        }
                    } while (!valid);

                    // String[] newCustomerNames = new String[customerNames.length + 1];

                    for (int i = 0; i < customers.length; i++) {
                        newCustomers[i][0] = customers[i][0];
                        newCustomers[i][1] = customers[i][1];
                        newCustomers[i][2] = customers[i][2];
                    }

                    newCustomers[newCustomers.length - 1][0] = Integer.toString(id);
                    newCustomers[newCustomers.length - 1][1] = name;
                    newCustomers[newCustomers.length - 1][2] = Double.toString(intialDeposit);

                    customers = newCustomers;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG,
                            String.format("Bank Account ID SDB-%05d:%s has created successfully\n", (id), name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                        continue;
                    screen = DASHBOARD;
                    break;

            }

        } while (true);
    }
}