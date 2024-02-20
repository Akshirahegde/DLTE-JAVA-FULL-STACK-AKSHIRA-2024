package basics.service;
import java.util.*;
public class SIPCalculator {
    public static void main(String args[]){
        Scanner scanner= new Scanner(System.in);
        double principalAmount,monthlyInvestment,annualReturn,estimateReturn=0,total=0;
        int tenure;
        System.out.println("Enter the Monthly Investment");
        monthlyInvestment=scanner.nextDouble();
        System.out.println("Enter the expected annual return" );
        annualReturn=scanner.nextDouble();
        double annualreturn=annualReturn/100;
        System.out.println("enter the tenure in years");
        tenure=scanner.nextInt();
        //calculating the principal amount after the tenure
        principalAmount=monthlyInvestment*12*tenure;
        double monthlyRate=annualReturn/12/100;
        double numMonths=12*tenure;
        total=monthlyInvestment*((Math.pow((1+monthlyRate),(numMonths))-1)*(1+monthlyRate))/monthlyRate;
        double totalmoneyInvested=numMonths*monthlyInvestment;
        estimateReturn=total-totalmoneyInvested;
        System.out.println("Principal amount"+principalAmount);
        System.out.println("Estimate Return"+estimateReturn);
        System.out.println("Total"+total);


    }

}
