package basics.service;
import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Datavalidation {
    public static void main(String args[]){
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",aadhaar,mobileNumber;

        Scanner scanner= new Scanner(System.in);
        System.out.println("welcome to my bank");
        System.out.println("Enter your name");
        borrowerName=scanner.nextLine();
        String nameExpression="^[A-Za-z .']+$";
        Pattern patternname=Pattern.compile(nameExpression);
        Matcher matchername= patternname.matcher(borrowerName);
        if(matchername.matches()){
            System.out.println("Valid name");
        }
        else
            System.out.println("invalid name");
        System.out.println("Enter the Aadhaar number");
        aadhaar=scanner.next();
        String AadhaarExpression="[1-9]{1}[0-9]{11}";
        Pattern patternaadhaar= Pattern.compile(AadhaarExpression);
        Matcher matcheraadhaar=patternaadhaar.matcher(aadhaar);
        if(matcheraadhaar.matches()){
            System.out.println("Valid Aadhaar number");
        }
        else
            System.out.println("invalid Aadhaar");
        System.out.println("Enter your MobileNumber");
        mobileNumber=scanner.next();
        String mobileExpression="[6-9]{1}[0-9]{9}";
        Pattern patternmobile= Pattern.compile(mobileExpression);
        Matcher matchermobile=patternmobile.matcher(mobileNumber);
        if(matchermobile.matches()){
            System.out.println("valid Mobile number");
        }

        else
            System.out.println("invalid mobile number");
        System.out.println("Enter your PanNumber");
        borrowerPan=scanner.next();
        String panExpression="[A-Z]{5}[0-9]{4}[A-Z]";
        Pattern patternpan= Pattern.compile(panExpression);
        Matcher matcherpan=patternpan.matcher(borrowerPan);
        if(matchermobile.matches()){
            System.out.println("valid pan number");
        }

        else
            System.out.println("invalid mobile number");
        System.out.println("Enter your Email id");
        borrowerEmail=scanner.next();
        int _atpos=borrowerEmail.indexOf('@');
        int _dotpos=borrowerEmail.indexOf('.');
        if((_dotpos - _atpos)>=3){
            System.out.println("valid Email");
        }
        else {
            System.out.println("invalid Email");
        }

        System.out.println("Dear"+borrowerName+"Thank you for applying ");




    }
}

