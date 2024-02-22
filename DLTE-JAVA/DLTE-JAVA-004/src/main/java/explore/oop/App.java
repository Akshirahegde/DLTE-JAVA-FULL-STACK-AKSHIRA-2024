package explore.oop;


public class App
{
    public static void main( String[] args )
    {
        GPay gPay=new GPay(12345,70987,"Akshira",3456789,"7912");
        double withdrawAmount=4678.0;
        System.out.println("Amount entered to withdraw"+withdrawAmount);
        if (gPay.approveWithdraw(withdrawAmount)){
            System.out.println("Withdraw Approved,your balance is"+(gPay.getAccountBalance()-withdrawAmount));
        }
        else{
            System.out.println("Insufficient balance!!");
        }
        String billerName="Emergency Service";
        double billedAmount=700;
        String billerType="Emergency";
        String UpiPin="7912";
        gPay.payBill(billerName,billedAmount,billerType,UpiPin);
    }
}
