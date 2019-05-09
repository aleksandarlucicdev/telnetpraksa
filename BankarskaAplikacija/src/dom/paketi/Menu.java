package dom.paketi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;

    //---------------------------------------MAIN-------------------------------------------
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
        //---------------------------------------------------------------------------------------
    }

    // ------------------------------rad menija----------------------------------------------
    public void runMenu() {
        printHeader();
        while (!exit) {
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }

    //----------------------------------------------------------------------------------------
    //----------------------izbornik opcija---------------------------------------------------
    private void performAction(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Thanks for using our application.");
                System.exit(0);
                break;
            case 1:
                createAnAccount();
                break;
            case 2:
                makeADeposit();
                break;
            case 3:
                makeAWithdrawal();
                break;
            case 4:
                listBalances();
                break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }
    //---------------------------------------------------------------------------------
    // metode za izbor ----------------------------------------------------------------

/*
    private String getAccountType(){
         String accountType = "";
        boolean valid = false;
        while (!valid) {
            accountType = askQuestestion("Please enter type of your account (checking/savings)");


            if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")) {
                valid = true;

            } else {
                System.out.println("That account type is not available in our app. Please choose checking or savings");
            }

        }
        return accountType;
    }
*/

    private String askQuestestion(String question, List<String> answers){
        String response = "";
        Scanner input = new Scanner(System.in);
        boolean choices = answers == null ? false : true;
        boolean firstRun = true;
        do{
            if(!firstRun){
                System.out.print("Invalid selection, please try again!");
            }
            System.out.print(question);
            if (answers != null) {
                System.out.println("(");
                for (int i = 0 ;i< answers.size() -1;++i){
                    System.out.println(answers.get(i) +"/");


                }
                System.out.print(answers.get(answers.size() - 1));
                System.out.print(") :");
            }
            response = input.nextLine();
            firstRun = false;
                if(!choices){
                    break;
                }
        }while (!answers.contains(response));

        return response;
    }


    private double getDeposit(String accountType){
        double initialDeposit = 0;
        boolean valid = false;
        valid = false;

        while (!valid) {
            System.out.print("Please enter your initial deposit");
            try {
                initialDeposit = Double.parseDouble(keyboard.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Deposit must be number");

            }

            if (accountType.equalsIgnoreCase("checking")) {
                if (initialDeposit < 100) {
                    System.out.println("Checking account require a minimum of 100 usd");
                } else {
                    valid = true;

                }
            } else if (accountType.equalsIgnoreCase("savings")) {
                if (initialDeposit < 50) {
                    System.out.println("Savings account require a minimum of 50 usd");
                } else {
                    valid = true;


                }

            }

        }
    return  initialDeposit;
    }



    private void createAnAccount() {   // program proverava da li ce korisnik da pristupi checking ili saving nalogu, a potom ga evidentira


        List<String> accountTypes = Arrays.asList("checking", "savings");
        String accountType = askQuestestion("Please enter type of your account :", accountTypes);
        String firstName = askQuestestion("Please enter your first name", null);
        String lastName = askQuestestion("Please enter your last name", null);
        String ssn = askQuestestion("Please enter your social number", null);
        double initialDeposit = getDeposit(accountType);

        Account account;
        if (accountType.equalsIgnoreCase("checking")) {
            account = new Checking(initialDeposit);

        } else {
            account = new Savings(initialDeposit);


        }

        Customer customer = new Customer(firstName, lastName, ssn, account);

        bank.addCustomer(customer);

    }


    private void listBalances() {
        int account = selectAccount();
        if (account >= 0) {

            System.out.println(bank.getCustomer(account).getAccount());


        }
    }
    private void makeAWithdrawal() {

        int account = selectAccount();
        if (account >= 0) {
            System.out.print("How much would you like to withdraw?");
            double amount = 0;
            try {

                amount = Double.parseDouble(keyboard.nextLine());

            } catch (NumberFormatException e) {

                amount = 0;
            }
            bank.getCustomer(account).getAccount().withdraw(amount); // kupi musteriju, racun i kolicinu povucenog novca
        }




    }

    private void makeADeposit() {

        int account = selectAccount();
        if (account >= 0) {
            System.out.print("How much would you like to deposit?");
            double amount = 0;
            try {

                amount = Double.parseDouble(keyboard.nextLine());

            } catch (NumberFormatException e) {

                amount = 0;
            }
            bank.getCustomer(account).getAccount().deposit(amount); // kupi musteriju, racun i kolicinu depozita
        }

    }


    private int selectAccount() {

        ArrayList<Customer> customers = bank.getCustomers();
        if (customers.size() <= 0) {
            System.out.println("There is no customers at your bank");
            return -1;
        }
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ")" + customers.get(i).basicInfo());
        }
        int account = 0;
        System.out.print("Please enter your selection");
        try {

            account = Integer.parseInt(keyboard.nextLine()) - 1;

        } catch (NumberFormatException e) {

            account = -1;
        }
        if(account<0 || account> customers.size()){
            System.out.println("Invalid account selected");
            account = -1;
        }
        return account;


    }


    //-----------------------------------------------------------------------------------

    //------------------------------Stampa menija----------------------------------------
    private void printMenu() {
        System.out.println("Please make a selection:");
        System.out.println("1) Create a new account");
        System.out.println("2) Make a deposit");
        System.out.println("3) Make a withdrawal");
        System.out.println("4) List account money");
        System.out.println("0) Exit");
    }

    //-----------------------------------------------------------------------------------------------

    //-----------------------------------Izbor korisnika----------------------------------------------
    private int getInput() {
        int choice = -1;
        do {
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only");

                if (choice < 0 || choice > 4) {
                    System.out.println("Number that you are trying to type is not on list! Please, choose only numbers that are already given.");
                }
            }
        } while (choice < 0 || choice > 4);
        return choice;

    }
    //-------------------------------------------------------------------------------------------------


    //-----------------------------stampa hedera-------------------------------------------------------

    private void printHeader() {
        System.out.println("+--------------------------------+");
        System.out.println("+          Banking App           +");
        System.out.println("+      Feel free to use it       +");
        System.out.println("+--------------------------------+");
    }

    //-------------------------------------------------------------------------------------------------
}
