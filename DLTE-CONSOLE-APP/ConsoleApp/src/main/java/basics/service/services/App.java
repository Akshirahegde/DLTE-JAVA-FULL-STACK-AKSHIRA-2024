package basics.service.services;

import java.util.ResourceBundle;
import java.util.Scanner;

public class App
{
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    public static void main( String[] args )
    {
        System.out.println(ResourceBundle.getBundle("application").getString("welcome.message"));
        Scanner scanner=new Scanner(System.in);
        String userName;
        String password;
        System.out.println("Enter your UserName");
        userName=scanner.next();
        System.out.println("Enter your password");
        password=scanner.next();
        int choice;

        if(services.callverifyPassword(userName,password))//----------------------
            while(true){
                System.out.println(ResourceBundle.getBundle("application").getString("app.menu"));
                choice=scanner.nextInt();
                switch (choice){
                    case 1:
                        double amount;
                        System.out.println("Enter the amount to be depositted");
                        amount=scanner.nextDouble();
                        if(amount>)
                        System.out.println("Enter the Password");
                        password=scanner.next();
                        if(services.callverifyPassword(userName,password)){
                            services.callDeposit(userName,password,amount);
                        }
                    case 2:
                        System.out.println("Fund Transfer");
                    case 3:
                        System.out.println("Withdrawl");
                    case 4:
                        System.out.println("You have signed out successfully");
                    default:
                        System.out.println("Wrong choice");
                        System.exit(0);

                }
            }


    }
}
