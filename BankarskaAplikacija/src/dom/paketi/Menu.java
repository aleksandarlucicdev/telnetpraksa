package dom.paketi;

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
    public void runMenu(){
        printHeader();
        while (!exit){
            printMenu();
            int choice = getInput();
             performAction(choice);
        }
    }
    //----------------------------------------------------------------------------------------
    //----------------------izbornik opcija---------------------------------------------------
    private void performAction(int choice) {
        switch (choice){
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
    private void listBalances() {
    }

    private void makeAWithdrawal() {
    }

    private void makeADeposit() {
    }

    private void createAnAccount() {   // program proverava da li ce korisnik da pristupi checking ili saving nalogu, a potom ga evidentira
        String firstName, lastName, ssn, accountType ="";
        double initialDeposit =0;
        boolean valid = false;
        while(!valid) {
            System.out.print("Please enter type of your account (checking/savings)");
            accountType = keyboard.nextLine();

            if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")) {
                valid = true;

            } else {
                System.out.println("That account type is not available in our app. Please choose checking or savings");
            }

        }
        System.out.print("Please enter your first name");
        firstName = keyboard.nextLine();
        System.out.print("Please enter your last name");
        lastName = keyboard.nextLine();
        System.out.print("Please enter your social number");
        ssn = keyboard.nextLine();

        valid = false;
        while (!false){
            System.out.print("Please enter your initial deposit");
            try{
                initialDeposit = Double.parseDouble(keyboard.nextLine());

            }
            catch (NumberFormatException e){
                System.out.println("Deposit must be number");

            }

            if(accountType.equalsIgnoreCase("checking")){
                if(initialDeposit < 100){
                    System.out.println("Checking account require a minimum of 100 usd");
                }else{
                    valid = true;

                }
            } else if(accountType.equalsIgnoreCase("savings")){
                if(initialDeposit < 50){
                    System.out.println("Savings account require a minimum of 50 usd");
                }else{
                    valid = true;

                }
            }
        }

        //Moze sada da se pravi nalog

        Account account;
        if(accountType.equalsIgnoreCase("checking")){
            account = new Checking(initialDeposit);

        }else{
            account = new Savings(initialDeposit);


        }



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
        }while(choice < 0 || choice > 4);
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
