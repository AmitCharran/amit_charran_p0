package com.revature.model;
import com.revature.util.ArrayList;

/**
 * This class is for my banking application. This is how I keep the clients information.
 * @Author Amit Charran
 * @version 1.0
 * @ Since 2021-08-1
 */
public class Client implements ClientInterface{
    String firstName;
    String lastName;
    String username;
    String password;
    int clientID;
    ArrayList<Account> accounts;

    public Client(){
        // generate client ID
        accounts = new ArrayList<>();
    }

    public Client(String firstName, String lastName, String username, String password, int clientID, ArrayList<Account> accounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.clientID = clientID;
        this.accounts = accounts;
    }

    public void viewAccount(){
        for(int i = 0; i < accounts.size(); i++){
            System.out.println(accounts.get(i)); // need to format output
        }
    }

    private boolean isPasswordSecure(String password){
        // returns false if password is not secure

        return true;
    }

    public void transferMoneyToOtherAccounts(Account a1, Account a2, double m){

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public String toString(){
        return this.username;
    }
}
