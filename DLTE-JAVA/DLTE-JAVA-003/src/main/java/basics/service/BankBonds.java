package basics.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Encapsulated Class Bonds with maturity, interest rate, their tax status, bondholder, period
//        Create at least 5 funds in array
//        sort and view bonds with high rate of interest
public class BankBonds {
    private Integer maturity;
    private Double interestRate;
    private String taxstatus;
    private  Integer period;
    private String bondHolder;
    public static void main (String[] args){
        BankBonds Mortgage=new BankBonds(5,5.3,"Tax Levied",7,"Amith");
        BankBonds Muncipal=new BankBonds(6,7.3,"No Tax",6,"anu");
        BankBonds Corporate=new BankBonds(5,8.6,"No Tax",4,"ram");
        BankBonds Callable=new BankBonds(7,3.2,"No Tax",8,"Sam");
        BankBonds Perpetual=new BankBonds(10,5.2,"No Tax",7,"Avi");
        BankBonds bankBonds[]={Mortgage,Muncipal,Corporate,Callable,Perpetual};
        int position=MaximumRate(bankBonds);
    }
    public static int MaximumRate(BankBonds[] bankBonds){
        double maximumInterest=0;
        int position=0,count=0;
        for (BankBonds each:bankBonds){
            count++;
            if (each.getInterestRate()>maximumInterest){
                maximumInterest=each.getInterestRate();
                position=count;

            }
        }
        System.out.println("Maximum Interest Rate is"+maximumInterest);
        return position;
    }

}
