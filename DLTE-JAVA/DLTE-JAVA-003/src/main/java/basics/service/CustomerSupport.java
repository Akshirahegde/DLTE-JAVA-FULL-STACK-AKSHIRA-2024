package basics.service;

import java.util.Date;

public class CustomerSupport {
  public static void main (String args[]){
//     CreditCard creditCard=new CreditCard();
//     creditCard.setCreditCardNumber(23456789089L);
//     creditCard.setCreditCardExpiry(new Date(2034,12,20));
//     creditCard.setCreditCardCVV(456);
//     creditCard.setCreditCardHolder("Akshira");
//     creditCard.setCreditCardLimit(100000);
//     creditCard.setCreditCardPin(1234);
//     creditCard.setDateofBillGeneration(new Date(2024,03,11));
//     creditCard.setDateofBillPayment(new Date(2024,03,25));
//CreditCard creditCard1=new CreditCard(123456789L,"Akshira",new Date(2034,12,20),456,100000,new Date(2024,03,11),new Date(2024,03,25),1234);
//        System.out.println(creditCard1.getCreditCardHolder()+" "+creditCard1.getCreditCardPin());
//        System.out.println;


      //CreditCard[] myBank=new CreditCard[10];
      CreditCard[] myBank={new CreditCard(12345678L,"Akshira",new Date(2034,03,11),555,100000,new Date(2034,01,9),new
              Date(2024,06,11),1234) ,
              new CreditCard(123456789L,"Ash",new Date(2034,07,11),555,100000,new Date(2034,03,9),new
                      Date(2024,06,11),1254),
              new CreditCard(1234567890L,"Asmo",new Date(2034,03,11),555,100000,new Date(2034,03,9),new
                      Date(2024,06,11),1274),
              new CreditCard(1234568L,"Asra",new Date(2034,07,11),555,100000,new Date(2034,03,9),new
                      Date(2024,06,11),1244),
              new CreditCard(12345078L,"Ashira",new Date(2034,03,11),555,100000,new Date(2034,02,9),new
                      Date(2024,06,11),1734)
      };//anonymous way of object creation

CustomerSupport customerSupport=new CustomerSupport();
      customerSupport.findearlyexpire(myBank);

      customerSupport.list(myBank);
      customerSupport.sortingCustomers(myBank);
      customerSupport.list(myBank);

}
   public void findearlyexpire(CreditCard[] customers){
for(CreditCard each:customers){
    if(each.getCreditCardExpiry().before(new Date(2026,01,01))){
        System.out.println(each.getCreditCardHolder()+"your credit card"+each.getCreditCardNumber()+"needs to be Upgraded");
   // after-start before-end
    }
}
   }

   public void list(CreditCard[] customers){
       System.out.println("available Customers");
       for (CreditCard each:customers){
           System.out.println(each);
       }

   }

   public void  sortingCustomers(CreditCard[] customers){
      CreditCard backup=null;
       for(int select=0;select<customers.length;select++){
           for (int next=select+1;next<customers.length;next++){
               if(customers[select].getCreditCardHolder().compareTo(customers[next].getCreditCardHolder())>0){
                   backup=customers[select];
                   customers[select]=customers[next];
                   customers[next]=backup;

               }
           }
       }

   }

    }

