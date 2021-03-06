package com.revature.model;
import com.revature.repo.ClientDAO;
import com.revature.repo.ClientDAOimpl;
import com.revature.util.MyArrayList;

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
    MyArrayList<Account> accounts;

    public Client(){
        // generate client ID
        accounts = new MyArrayList<>();
    }

    public Client(String firstName, String lastName, String username, String password, int clientID, MyArrayList<Account> accounts) {
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
        if(clientID == 0){
            ClientDAO cDAO = new ClientDAOimpl();
            Client c = cDAO.retrieveClient(this.username);
            if(c == null){
                return -1;
            }
            clientID = c.clientID;
        }
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public MyArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(MyArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public String toString(){
        return this.username;
    }
}
