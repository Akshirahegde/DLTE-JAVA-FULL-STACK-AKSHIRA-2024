package basics.service;

import java.util.Scanner;
//required input:principalAmount,RoI,yearlyInterest
//output :maturityAmount
public class FDCalculator {
    public static void main(String args[]){
        double principalAmount,RoI,maturityAmount=0,yearlyInterest;
        int tenure;
        Scanner scanner =new Scanner(System.in);
        System.out.println("--------------FD Calculator----------------------");
        System.out.println("Enter the amount Deposited");
        principalAmount = scanner.nextDouble();
        System.out.println("Enter the Rate of Interest");
        RoI=scanner.nextDouble();
        yearlyInterest=RoI/100;
        System.out.println("Enter the tenure for FD");
        tenure=scanner.nextInt();
        maturityAmount=principalAmount*Math.pow((1+yearlyInterest),tenure);
        System.out.println("Your Matured FD Amount is "+maturityAmount);

    }
}
