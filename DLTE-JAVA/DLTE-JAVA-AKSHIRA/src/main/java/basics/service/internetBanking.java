package basics.service;

import java.util.Scanner;

public class internetBanking {
    public static void main(String args[]){
      String Name,emailId="",service="";
      Long accountNumber=0L,cardNumber,mobileNumber=0L;
      Scanner scanner=new Scanner(System.in);
        System.out.println("--------------Internet Banking------------------");
        System.out.println("Enter the name of Account Holder");
        Name=scanner.nextLine();
        System.out.println("Enter the Email Id");
        emailId=scanner.nextLine();
        System.out.println("enter the service you wish to avail from Internet banking(Fund Transfer/Insurance/IMPS");
        service=scanner.nextLine();
        System.out.println("Enter the Account Number");
        accountNumber=scanner.nextLong();
        System.out.println("Enter the Mobile Number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the card Number");
        cardNumber=scanner.nextLong();
        System.out.println("Welcome you can now avail "+service+" service");


    }
}
