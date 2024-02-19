package basics.service;
import java.util.*;
//details required:name,phone, pan, aadhaar,address,salary type(self employed/salaried),email
public class personalLoan {
    public static void main(String[] args){
        String Name="",pan="",Address="",Email="",SalaryType="";
        Long mobileNumber=0L,aadhaar=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-------------------welcome to my Bank-----------------------");
        System.out.println("Fill your Name");
        Name=scanner.nextLine();
        System.out.println("Let us know the income type(Salaried/Self Employed)");
        SalaryType=scanner.nextLine();
        System.out.println("Fill your aadhaar number");
        aadhaar=scanner.nextLong();
        System.out.println("Enter the PAN");
        pan=scanner.next();
        System.out.println("Enter the Mobile Number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the email address");
        Email=scanner.next();
        System.out.println("Dear"+ Name +"Thanks for showing interest in taking loan from our bank.Your request has been successfully submitted.Further response will be sent to "+Email+" or "+mobileNumber);
    }
}
