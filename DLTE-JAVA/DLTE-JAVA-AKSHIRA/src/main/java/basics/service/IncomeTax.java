package basics.service;
import java.util.*;
public class IncomeTax{
public static void main(String[] args){
    double income,taxAmount,taxpercent=0;
    String taxType="";
    Scanner scanner= new Scanner(System.in);
    System.out.println("Enter the income");
    income=scanner.nextInt();
    System.out.println("enter the type of incometax");
    taxType=scanner.next();
    switch(taxType){
        case"OldRegime": case"oldregime": case"OLDREGIME":
            if(income>=0 && income<=250000){
                System.out.println("no tax levied");
            }
            else if(income>250000 && income<=500000){
                taxpercent=0.05;
            }
            else if(income>500000 && income<=750000){
                taxpercent=0.2;
            }
            else if(income>750000 && income<=1000000){
                taxpercent=0.2;
            }
            else if(income>1000000 && income<=1250000) {
                taxpercent = 0.3;

            }
            else if(income>1250000&& income<=1500000){
                taxpercent=0.3;
            }
            else{
                taxpercent=0.3;
            }
            break;

        case"NewRegime": case"newRegime": case"NEWREGIME":
            if(income>=0 && income<=250000){
                System.out.println("no tax levied");
            }
            else if(income>250000 && income<=500000){
                taxpercent=0.05;
            }
            else if(income>500000 && income<=750000){
                taxpercent=0.1;
            }
            else if(income>750000 && income<=1000000){
                taxpercent=0.15;
            }
            else if(income>1000000 && income<=1250000){
                taxpercent=0.20;
            }
            else if(income>1250000&& income<=1500000){
                taxpercent=0.25;
            }
            else{
                taxpercent=0.3;
            }
    }
    taxAmount=(income/100)*taxpercent;
    System.out.println("taxamount payable is"+taxAmount);


}
}
