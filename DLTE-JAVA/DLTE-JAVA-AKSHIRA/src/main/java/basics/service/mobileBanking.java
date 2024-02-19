package basics.service;
import java.util.*;
public class mobileBanking {
    public static void main (String args[]){
        String Name="",Address="";
        Long AccNum=0L,mobileNumber=0L,cardNumber=0L;
        Scanner scanner= new Scanner(System.in);
        System.out.println("------Mobile Banking-----");
        System.out.println("Enter the name of Account Holder");
        Name=scanner.nextLine();
        System.out.println("Enter the Address of Account Holder");
        Address=scanner.nextLine();
        System.out.println("Enter the Account Number");
        AccNum=scanner.nextLong();
        System.out.println("Enter the Mobile Number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the card Number");
        cardNumber=scanner.nextLong();
        System.out.println("Welcome to The Mobile Banking.You can now avail the internet banking services ");



    }
}
