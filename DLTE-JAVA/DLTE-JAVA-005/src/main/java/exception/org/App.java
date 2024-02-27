package exception.org;

import exception.org.account.Gpay;
import exception.org.account.MyBankException;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
   static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    static final Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args){
        Gpay gpay=new Gpay(1234345,76543,"Akshira","2345");
        Scanner scanner=new Scanner(System.in);
        int attempt=0;
        while(attempt<5){
            try{
                System.out.println("Enter the BillName");
                String billName=scanner.next();
                System.out.println("Enter the BillType");
                String billType=scanner.next();
                System.out.println("Enter the BillAmount");
                Double billAmount=scanner.nextDouble();
                System.out.println("Enter the Pin number");
                String pinNumber=scanner.next();
                gpay.payBill(billName,billAmount,billType,pinNumber);
                attempt=0;
                return;
            }catch (MyBankException exception){
                logger.log(Level.WARNING,exception.toString());
                attempt++;
                if (attempt>5){
                    logger.log(Level.WARNING,"Account Blocked");
                    break;
                }
            }
        }
        scanner.close();
    }
}
