package basics.service;
import java.util.*;
public class DebitTransaction {
    public static void main(String args[]){
        int debitCount=0,NumOfTransaction=1;
        double currentBalance,newBalance;
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the Current Balance");
        currentBalance=scanner.nextDouble();
        for(;NumOfTransaction<=10;NumOfTransaction++){
            System.out.println("enter the newBalance after"+NumOfTransaction+" transactions");
            newBalance=scanner.nextDouble();
            if(newBalance<currentBalance){
                debitCount++;
            }
            currentBalance=newBalance;
        }
        System.out.println("Total no of debits in 10 transactions is"+debitCount);

    }
}
