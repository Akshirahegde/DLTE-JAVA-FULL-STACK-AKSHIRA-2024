package exception.org.account;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditCardException {
static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
public static void main(String args[]){
    System.out.println(resourceBundle.getString("welcome.message"));
    int choice,currentPin,limit,wrongLogin=1;
    String Date="";
    Scanner scanner=new Scanner(System.in);
    CreditCard[] myBank={
            new CreditCard("Akshira",2345678,1234,80000,new Date(2024,02,01),new Date(2024,02,06),12345678L),
            new CreditCard("Raksha",234578,7896,89000,new Date(2024,01,01),new Date(2024,04,06),12305678L),
            new CreditCard("Rakshitha",2355678,5643,56000,new Date(2023,12,01),new Date(2024,03,06),12645678L),
            new CreditCard("Swasthi",2340678,9873,67000,new Date(2023,12,06),new Date(2024,05,06),12345078L)

    };
    CreditCardException creditCardException=new CreditCardException();
    System.out.println("Enter your choice\n");
    System.out.println("1.Filter based on limit amount\n 2.Filter based on date of bill payment");
    choice=scanner.nextInt();
    switch (choice){
        case 1:
            try {
                System.out.println(resourceBundle.getString("limit.input"));
                limit=scanner.nextInt() ;
                creditCardException.limitFilter(myBank,limit);
            }
            catch (Exception exception){
                System.out.println(resourceBundle.getString("limit.user"));
                logger.log(Level.INFO,exception.getMessage());
            }
            break;
        case 2:
            try {
                System.out.println(resourceBundle.getString("date.input"));
                Date=scanner.next();
                creditCardException.dateFilter(myBank,Date);
            }
            catch (Exception exception){
                System.out.println(resourceBundle.getString("limit.user"));
                logger.log(Level.INFO,exception.getMessage());
            }
            break;
    }
    scanner.close();
}
public void limitFilter(CreditCard[] customers,Integer amountLimit) throws Exception {
    int flag=0;
    for (CreditCard each:customers) {
        if(each.getCreditcardLimit()>=amountLimit){
            System.out.println("user"+each.getCreditcardLimit()+"has limit greater than"+amountLimit);

        }
        else{
            System.out.println("user"+each.getCreditcardLimit()+"has limit lesser than"+amountLimit);
            flag=1;
        }

    }
    if (flag==0){
        throw new Exception();
    }
}
public void dateFilter(CreditCard[] customers, String filterDate){
    String splitDate[]=filterDate.split("/");
    int flag=0;
    for (CreditCard each:customers){
        if(Integer.parseInt(splitDate[0])==each.getBillPayment().getYear() && Integer.parseInt(splitDate[1])==each.getBillPayment().getMonth()&& Integer.parseInt(splitDate[2])==each.getBillPayment().getDate())
        {
            System.out.println(each.getCreditcardHolder()+" "+each.getCreditcardNumber());
        }
    }


}
}
