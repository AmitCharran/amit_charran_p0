
import com.revature.model.Client;
import com.revature.repo.AccountDAO;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceImpl;

import java.util.Scanner;

public class Driver {
    private static final Scanner sc = new Scanner(System.in);
    private static AccountService account = new AccountServiceImpl();
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

        switch (choice){
            case "1":
                signUpMenu();
                break;
            case "2":
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Select one of the choices: 1, 2, or 3");
                System.out.println("Press Enter to continue...");
                sc.nextLine();
        }
        initial_menu();
    }
    public static void signUpMenu(){
        Client client = new Client();


        while(true) {
            System.out.println("Enter your username");
            String username = sc.nextLine();
            // check if username exists <-- if exists breaks
            client.setUsername(username);
            break;

        }

        System.out.println("Enter your first name");
        String firstName = sc.nextLine();
        client.setFirstName(firstName);

        System.out.println("Enter your last name");
        String lastName = sc.nextLine();
        client.setLastName(lastName);

        while(true) {
            System.out.println("Enter email address");
            String email = sc.nextLine();
            //validate email

            break;
        }

        while(true){
            System.out.println("Enter password");
            String password = sc.nextLine();
            // verify password
            client.setPassword(password);
            break;
        }

        // add client to database
    }
}
