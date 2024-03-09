package exception.org.account;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
public class CreditCardException {

    static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    //static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) throws IOException {
        System.out.println(resourceBundle.getString("welcome.message"));
        Scanner scanner=new Scanner(System.in);
        String filterDate="";
        int option,currPIN,wrongLogin=1;
        Integer lowerLimit,upperLimit;
        Long creditCardNumber;
        CreditCard[] myBank={
                new CreditCard("Akshira",2345678,1234,80000,new Date(2024,02,01),new Date(2024,02,06),12345678L),
                new CreditCard("Raksha",234578,7896,89000,new Date(2024,01,01),new Date(2024,04,06),12305678L),
                new CreditCard("Rakshitha",2355678,5643,56000,new Date(2023,12,01),new Date(2024,03,06),12645678L),
                new CreditCard("Swasthi",2340678,9873,67000,new Date(2023,12,06),new Date(2024,05,06),12345078L)

        };
        CreditCardException creditCardException= new CreditCardException();
        System.out.println("Enter \n1.Filter based on Limit Amount\n2.Filter based on date of bill payment\n");
        option=scanner.nextInt();
        switch(option){
            case 1:
                try {
                    System.out.println(resourceBundle.getString("limit.lowerinput"));
                    lowerLimit = scanner.nextInt();
                    System.out.println(resourceBundle.getString("limit.upperinput"));
                    upperLimit = scanner.nextInt();
                    creditCardException.filterBasedOnLimit(myBank, lowerLimit, upperLimit);
                }catch(MyBankCreditCardException exception){
                    System.out.println(resourceBundle.getString("limit.user"));
                }
                break;
            case 2:
                try {
                    System.out.println(resourceBundle.getString("date.input"));
                    filterDate = scanner.next();
                    creditCardException.filterBasedOnBillPayment(myBank, filterDate);
                }catch (MyBankCreditCardException exception){
                    System.out.println("No users");
                }
                break;
        }
        scanner.close();
    }
    public void filterBasedOnLimit(CreditCard[] customers,Integer start,Integer end){
        System.out.println("List of users having amount limit between "+start+" and "+end);
        int flag=0;
        for(CreditCard each:customers){
            if(each.getCreditcardLimit()>=start && each.getCreditcardLimit()<end){
                System.out.println("User "+each.getCreditcardHolder()+" with card number "+each.getCreditcardNumber());
                flag=1;
            }
        }
        if(flag==0) {
            throw new MyBankCreditCardException();
        }
    }

    public void filterBasedOnBillPayment(CreditCard[] customers,String date){
        String splitDate[]=date.split("-");
        int flag=0;
        for(CreditCard each:customers){
            if(Integer.parseInt(splitDate[0])==each.getBillPayment().getDate() && Integer.parseInt(splitDate[1])==each.getBillPayment().getMonth() && Integer.parseInt(splitDate[2])==each.getBillPayment().getYear()){
                System.out.println(each.getCreditcardHolder()+" "+each.getCreditcardNumber());
                flag=1;
            }
        }if(flag==0) {
            throw new MyBankCreditCardException();
        }
    }
}
