package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        MyBank myBank=new LoanApp();
        while(true){
            Scanner scanner=new Scanner(System.in);
            int choice;
            System.out.println("1.Add loans\n2.Check available loans\n3.Check closed Loans\n4.Display loans");
            System.out.println("enter your choice");
            choice=scanner.nextInt();
            switch (choice){
                case 1:
                    myBank.addLoan();
                    break;
                case 2:
                    myBank.availableLoans();
                    break;
                case 3:
                    myBank.closedLoans();
                    break;


            }
        }

    }
}
