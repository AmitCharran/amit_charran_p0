
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.model.Transaction;
import com.revature.repo.AccountDAO;
import com.revature.service.*;
import com.revature.util.AccountType;
import com.revature.util.MyArrayList;
import com.revature.util.TransactionType;
import com.sun.deploy.util.StringUtils;
import java.sql.Date;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private static final Scanner sc = new Scanner(System.in);
    private static final AccountService accountService = new AccountServiceImpl();
    private static final ClientService clientService = new ClientServiceImpl();
    private static final TransactionService transactionService = new TransactionServiceImpl();
    private static final HolderService holderService = new HolderServiceImpl();
    public static void main(String[] args) {

        // need a postgres sql driver
        // google MVN repositories

        initial_menu();

        sc.close();

    }

    public static void initial_menu(){
        System.out.println("Hello! Welcome to our bank!");
        System.out.println("1) Sign-up");
        System.out.println("2) Log-in");
        System.out.println("3) Leave the bank");
        System.out.println("Enter choice 1, 2, or 3");

        String input = sc.nextLine();
        String choice = String.valueOf(input.charAt(0));

        boolean choicesInCorrect = true;
        switch (choice){
            case "1":
                signUpMenu();
                choicesInCorrect = false;
                break;
            case "2":
                logInMenu();
                choicesInCorrect = false;
                break;
            case "0":
                System.exit(0);
            default:
                if(choicesInCorrect) {
                    System.out.println("Select one of the choices: 1, 2, or 3");
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                }
        }
        initial_menu();
    }
    public static void signUpMenu(){
        Client client = new Client();
        boolean usernameGood = true;


        do{
            if(usernameGood){
                System.out.println("Enter your username");
            }else {
                System.out.println("Username taken -- Enter a new username");
            }
            String username = sc.nextLine();

            client.setUsername(username);
            usernameGood = false;
        }while(clientService.checkClientUsername(client.getUsername()));


        System.out.println("Enter your first name");
        String firstName = sc.nextLine();
        client.setFirstName(firstName);

        System.out.println("Enter your last name");
        String lastName = sc.nextLine();
        client.setLastName(lastName);

        System.out.println("Enter password");
        String password = sc.nextLine();
        client.setPassword(password);

        createAccountMenu(client);

    }

    public static void createAccountMenu(Client c){
        System.out.println("What type of account would you like?");
        System.out.println("1: Checkings");
        System.out.println("2: Savings");
        System.out.println("0: Exit");

        Account a = new Account();

        boolean accountTypeSet = true;

        do {
            if(!accountTypeSet) {
                System.out.println("Please enter one of the three choices");
                System.out.println("Press Enter to continue");
                sc.nextLine();
            }

            String input = sc.nextLine();
            String choice = String.valueOf(input.charAt(0));
            // probably need to fix the logic here
            switch (choice) {
                case "1":
                    a.setType(AccountType.CHECKINGS);
                    break;
                case "2":
                    a.setType(AccountType.SAVINGS);
                    break;
                case "0":
                    System.exit(0);
                default:

            }
            accountTypeSet = false;
        }while(a.getType() == null);

        if(!clientService.checkClientUsername(c.getUsername())) {
            clientService.insertNewClient(c);
        }
        accountService.createAccount(a);
        holderService.insertNewClient(a,c);

        // got to after log-in menu


    }

    public static void logInMenu(){
        String username;
        Client c = null;

        boolean userNameDoesNotExist = true;
        boolean moreThanOnce = false;

        do{
            String input = "";
            if(moreThanOnce){
                System.out.println("Username does not exist");
                System.out.println("Try Enter Username again or enter \'0\' to exit");
                input = sc.nextLine();
            }else {
                System.out.println("Enter Username");
                input = sc.nextLine();
            }
            c = clientService.retrieveClient(input);

            if(c != null){
                userNameDoesNotExist = false;
            }

            if(input.equals("0")){
                System.exit(0);
            }
            moreThanOnce = true;
        }while(userNameDoesNotExist);


        boolean passwordIncorrect = true;
        moreThanOnce = false;
        do{
            String passwordInput = "";
            if(moreThanOnce){
                System.out.println("Password incorrect");
                System.out.println("Try Again or 0 to go back to initial menu");
                passwordInput = sc.nextLine();

                if(passwordInput.equals("0")){
                    initial_menu();
                }

            }else {
                System.out.println("Enter Password for " + c.getUsername());
                passwordInput = sc.nextLine();
            }
            if(passwordInput.equals(c.getPassword())){
                passwordIncorrect = false;
            }

            moreThanOnce = true;
        }while (passwordIncorrect);

        loggedInMenu(c);
    }

    public static void loggedInMenu(Client c){
        System.out.println("Hello " + c.getUsername() + "!");
        System.out.println("What would you like to do today?");
        System.out.println("1) View accounts");
        System.out.println("2) Manage accounts - Deposit/Withdraw/Transfer/Add");
        System.out.println("3) Add another account");
        System.out.println("4) Log out");
        System.out.println("0) Exit");

        String input = sc.nextLine();
        String choice = String.valueOf(input.charAt(0));

        switch(choice){
            case "1":
                MyArrayList<Account> allAccounts=  accountService.getAllAccounts(c);

                if(allAccounts != null) {
                    for (int i = 0; i < allAccounts.size(); i++) {
                        System.out.println("Account # " + (i + 1));
                        System.out.println(allAccounts.get(i));
                    }
                }else {
                    System.out.println("You do not have any accounts");
                }

                System.out.println("Press Enter to continue");
                sc.nextLine();
                loggedInMenu(c);
                break;
            case "2":
                Account a = selectAccountToManage(c);
                if(a == null){
                    System.out.println("You do not have any accounts");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                }else{
                    manageAccountMenu(c, a);
                }
                break;
            case "3":
                createAccountMenu(c);
                loggedInMenu(c);
                break;
            case "4":
                initial_menu();
                break;
            case "0":
                System.exit(0);
            default:
                loggedInMenu(c);

        }

    }

    public static void manageAccountMenu(Client c, Account a){
        System.out.println("Current Balance: $" + a.getBalance());
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Transfer Money to Another Account");
        System.out.println("4) Add another user to this Account ");
        System.out.println("5) View all users for account");
        System.out.println("6) Switch Account");
        System.out.println("7) View Transactions");
        System.out.println("8) Log out");
        System.out.println("0) Exit");


        String input = sc.nextLine();
        String choice = String.valueOf(input.charAt(0));
        double val;
        switch (choice){
            case "1":
                val = depositWithdrawTransfer("deposit");
                if(a.validForDeposit(val)){
                    accountService.deposit(a, val);
                    transactionService.createTransaction(
                            new Transaction(
                                    new Date(System.currentTimeMillis()),
                                    val,
                                    TransactionType.DEPOSIT,
                                    a.getAccount_id(),
                                    a.getAccount_id()
                            )
                    );

                }else{
                    System.out.println("Amount not valid for deposit, check balance");
                }


                break;
            case "2":
                val = depositWithdrawTransfer("withdraw");
                if(a.validForWithdraw(val)){
                    accountService.withdraw(a, val);
                    transactionService.createTransaction(
                            new Transaction(
                                    new Date(System.currentTimeMillis()),
                                    val,
                                    TransactionType.WITHDRAW,
                                    a.getAccount_id(),
                                    a.getAccount_id()
                            ));
                }else{
                    System.out.println("Amount not valid for withdraw, check balance");
                }
                break;
            case "3":
                Account a2 = selectAccountToManage(c);
                System.out.println("Second account balance = $" + a2.getBalance());
                System.out.println("Press enter to continue");
                sc.nextLine();
                val = depositWithdrawTransfer("transfer");

                if(a.validForWithdraw(val) && a2.validForDeposit(val)){
                    accountService.withdraw(a, val);
                    accountService.deposit(a2, val);

                    transactionService.createTransaction(
                            new Transaction(
                                    new Date(System.currentTimeMillis()),
                                    val,
                                    TransactionType.WITHDRAW,
                                    a.getAccount_id(),
                                    a2.getAccount_id()
                            ));


                }else{
                    System.out.println("Check accounts, cannot transfer money");
                }
                break;
            case "4":
                System.out.println("Enter username you want to add to this account");
                String username = sc.nextLine();

                Client clientToInsert = clientService.retrieveClient(username);
                if(clientToInsert == null){
                    System.out.println(username + " does not exist");
                }else{
                    int numberAccounts = holderService.getAllClients(a).size();
                    if(numberAccounts>= 5){
                        System.out.println("Cannot add more users to account, at max capacity (5)");
                    }else{
                        holderService.insertNewClient(a, clientToInsert);
                    }
                }
                break;
            case "5":
                MyArrayList<Client> allClientsInAccount = holderService.getAllClients(a);
                if(allClientsInAccount.isEmpty()){
                    System.out.println("There are no users associated with this account");
                    System.out.println("press enter to continue");
                    sc.nextLine();
                    break;
                }
                System.out.println("List of all the clients");
                for(int i =0; i < allClientsInAccount.size(); i++){
                    System.out.print(allClientsInAccount.get(i) + " ");
                }

                break;
            case "6":
                Account toSwitch = selectAccountToManage(c);
                manageAccountMenu(c, toSwitch);
                break;
            case "7":
                MyArrayList<Transaction> allTs = transactionService.retrieveAllTransactionByAccount(a);
                for(int i =0;i < allTs.size(); i++){
                    System.out.println(allTs.get(i));
                }
                break;
            case "8":
//                holderService.deleteRow(a);
//                accountService.deleteAccount(a);
                initial_menu();
                break;
            case "0":
                System.exit(0);
            default:

        }
        manageAccountMenu(c,a);




    }

    public static double depositWithdrawTransfer(String t){
        boolean oneTimeThrough = false;
        String input = "";

        do{
            if(oneTimeThrough){
                System.out.println("Please enter a number");
                input = sc.nextLine();
            }else {
                System.out.println("Enter the amount you want to " + t);
                input = sc.nextLine();
            }

            oneTimeThrough = true;
        }while(!isNumber(input));

        return Double.parseDouble(input);
    }

    public static Account selectAccountToManage(Client c){
        MyArrayList<Account> allAccounts = accountService.getAllAccounts(c);
        if(allAccounts.size() == 0){
            return null;
        }
        if(allAccounts.size() == 1){
            return allAccounts.get(0);
        }

        System.out.println("Select account");
        for(int i = 0; i < allAccounts.size(); i++){
            System.out.println("Account # " + (i + 1));
            System.out.println(allAccounts.get(i));
        }

        String input = "";
        while(!isNumber(input)) {
            System.out.println("Enter the number of the account below");
            input = sc.nextLine();

            if(isNumber(input)){
                int val = Integer.parseInt(input);
                if(val > allAccounts.size() || val < 0){
                    System.out.println("Number is not valid");
                    input = "";
                }

            }
        }

        int val = Integer.parseInt(input);
        return allAccounts.get((val - 1));

    }

    public static boolean isNumber(String string){
        double doubleValue;

        if(string == null || string.equals("")){
            //System.out.println("Not a number");
            return false;
        }

        try{
            doubleValue = Double.parseDouble(string);
            return true;
        }catch (NumberFormatException e){
            //System.out.println("Not a number");
        }
        return false;

    }
}
