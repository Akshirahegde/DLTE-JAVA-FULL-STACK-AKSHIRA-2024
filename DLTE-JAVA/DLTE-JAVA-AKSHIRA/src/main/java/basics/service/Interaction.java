package basics.service;

import java.util.Scanner;
//Coomand line interaction: Car loan
/*
Personal details :name,aadhaar,pan,address,mobile,email
Income: salaried,self-employed:ITR
 */
public class Interaction {
    public static void main(String args[]){
        String borrowerName="",borrowerpan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="";
        Long mobileNumber=0L,aadhaar=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-------------------welcome to my Bank-----------------------");
        System.out.println("Fill your Name");
        borrowerName=scanner.nextLine();
        System.out.println("Fill your aadhaar number");
        aadhaar=scanner.nextLong();
        System.out.println("Enter the PAN");
        borrowerpan=scanner.next();
        System.out.println("Let us know the income type(Salaried/Self Employed");
        borrowerIncomeType=scanner.nextLine();
        System.out.println("Mention the Mobile Number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the email address");
        borrowerEmail=scanner.next();
        System.out.println("Dear"+borrowerName+"Thanks for showing the interest in car loan in our bank email will be sent to"+borrowerEmail+"or mobile number"+mobileNumber);

    }
}
