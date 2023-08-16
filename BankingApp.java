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
        
        String[] customerNames = new String[0];
        String[] account_id = new String[0];
        double[] Balance = new double[0];

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
                    String id;
                    String name;
                    boolean valid;
                    double intialDeposit;

                    // ID Validation
                    do {
                        valid = true;
                        System.out.print("\tEnter New Bank Account : "); // C-ac
                        id = SCANNER.nextLine().toUpperCase().strip();
                        if (id.isBlank()) {
                            System.out.printf(ERROR_MSG, "Account number can't be empty");
                            valid = false;
                        } else if (!id.startsWith("SDB-") || id.length() < 9) {
                            System.out.printf(ERROR_MSG, "Invalid account number format");
                            valid = false;
                        } else {
                            String number = id.substring(4);
                            for (int i = 0; i < number.length(); i++) {
                                if (!Character.isDigit(number.charAt(i))) {
                                    System.out.printf(ERROR_MSG, "Invalid account number format");
                                    valid = false;
                                    break;
                                }
                            }
                            for (int i = 0; i < account_id.length; i++) {
                                if (account_id[i].equals(id)) {
                                    System.out.printf(ERROR_MSG, "Account Number already exists");
                                    valid = false;
                                    break;
                                }
                            }
                        }
                    } while (!valid);
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

                    String[] newAccountIds = new String[account_id.length + 1];
                    String[] newCustomerNames = new String[customerNames.length + 1];
                    double[] newBalance=new double[account_id.length+1];
                    for (int i = 0; i < account_id.length; i++) {
                        newAccountIds[i] = account_id[i];
                        newCustomerNames[i] = customerNames[i];
                        newBalance[i]=Balance[i];
                    }
                    newAccountIds[newAccountIds.length - 1] = id;
                    newCustomerNames[newAccountIds.length - 1] = name;
                    newBalance[newBalance.length-1]=intialDeposit;
                    account_id = newAccountIds;
                    customerNames = newCustomerNames;
                    Balance=newBalance;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG,
                            String.format("Bank Account ID %s:%s has created successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                        continue;
                    screen = DASHBOARD;
                    break;

            }

        } while (true);
    }
}