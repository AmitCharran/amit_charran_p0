package com.revature.model;
import com.revature.tools.ArrayList;

/**
 * This class is for my banking application. This is how I keep the clients information.
 * @Author Amit Charran
 * @version 1.0
 * @ Since 2021-08-1
 */
public class Client {
    String name;
    String password;
    String email;
    int clientID;
    ArrayList<Account> accounts;

    public Client(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
        // create clientID
        accounts = new ArrayList<>();
    }

    public void viewAccount(){
        for(int i = 0; i < accounts.size(); i++){
            System.out.println(accounts.get(i)); // need to format output
        }
    }

    public boolean isPasswordSecure(String password){
        // returns false if password is not secure

        return true;
    }

    public void transferMoneyToOtherAccounts(Account a1, Account a2, double m){

    }


}
