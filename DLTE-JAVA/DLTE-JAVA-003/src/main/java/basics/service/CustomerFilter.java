package basics.service;

import java.util.Date;
import java.util.Scanner;

public class CustomerFilter {
    public static void main (String args[]){
        CreditCard[] myBank={
                new CreditCard(1234567L,"Akshira",new Date(2026,03,5),543,100000,new Date(2024,01,05),new Date(2024,01,5),1234),
                new CreditCard(1234547L,"Anu",new Date(2028,03,21),593,200000,new Date(2024,03,01),new Date(2024,04,1),1294),
                new CreditCard(1233567L,"Kelly",new Date(2029,03,5),943,50000,new Date(2024,02,01),new Date(2024,03,12),1894),
                new CreditCard(1234587L,"Sam",new Date(2034,03,10),743,100000,new Date(2024,05,03),new Date(2024,07,12),4534),
                new CreditCard(1244567L,"Ram",new Date(2029,04,6),503,250000,new Date(2024,02,07),new Date(2024,03,12),8314)
        };
        CustomerFilter customerFilter=new CustomerFilter();
//        customerFilter.list(myBank);
//        customerFilter.limit(myBank);
//        customerFilter.billPayment(myBank);
//        customerFilter.UpdatePin(myBank);
          customerFilter.UpdateLimit(myBank);

    }
    public void list(CreditCard[] customers){
        System.out.println("available Customers");
        for (CreditCard each:customers){
            System.out.println(each);
        }

    }
//    Filter based on the date of bill payment
    public void limit(CreditCard[] customers){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the amount");
        double amount=scanner.nextDouble();
        for(CreditCard each:customers){
            if(each.getCreditCardLimit()<amount)
                System.out.println(each.getCreditCardHolder()+" has exceeded the limit");
        }
    }
//    Filter based on the date of bill payment
    public void billPayment(CreditCard[] customers){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the date in the format(yyyy/mm/dd)");
        String date=scanner.next();
        String splitDate[]=date.split("/");
        for(CreditCard each:customers){
           if (Integer.parseInt(splitDate[0])==(each.getDateofBillPayment().getYear())&& Integer.parseInt(splitDate[1])==(each.getDateofBillPayment().getMonth()) && Integer.parseInt(splitDate[2])==(each.getDateofBillPayment().getDate())){
               System.out.println(each.getCreditCardHolder()+"pays the bill on"+each.getDateofBillPayment().getDate());
           }

        }
    }
//    Update specific PIN of customer
    public void UpdatePin(CreditCard[] customers){
        Scanner scanner=new Scanner(System.in);
        int NewPin=0;
        System.out.println("Enter the credit card number");
        long CreditCardNumber=scanner.nextLong();
        for (CreditCard each:customers) {
            if (CreditCardNumber == each.getCreditCardNumber()) {
                System.out.println("Enter the current pin");
                int currentPin = scanner.nextInt();
                    if (currentPin == each.getCreditCardPin()) {
                        System.out.println("Enter your new Pin");
                        NewPin = scanner.nextInt();
                        System.out.println("Your new pin has been Updated");
                    }
                    else
                        System.out.println("Pin doesn't match");
                }
            else
                System.out.println("Invalid Credit Card number");
            break;
        }
    }
//    Update the limit of customers those date of bill generation is 05th
    public void UpdateLimit(CreditCard[] customers) {

        for (CreditCard each : customers) {
            if (each.getDateofBillGeneration().getDate() == 5) {
                double currentLimit = each.getCreditCardLimit();
                double newLimit = currentLimit + (currentLimit * 0.05);
                each.setCreditCardLimit((int)newLimit);
                System.out.println(each.getCreditCardHolder() + ", your limit has been updated to" + newLimit);
            }


        }
    }

}
