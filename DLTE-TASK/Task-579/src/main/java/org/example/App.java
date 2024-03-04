package org.example;

import java.util.Date;

public class App
{
    public static void main(String[] args) {


    MyBankDatabase<CreditCard> creditCardMyBankDatabase=new MyBankDatabase<>() ;
    CreditCard card1=new CreditCard("45623456",1234);
    CreditCard card2=new CreditCard("45623678",6544);
    creditCardMyBankDatabase.create(card1);
    creditCardMyBankDatabase.create(card2);
        //System.out.println("Cards are"+card1+" and "+card2);
        System.out.println("Credit card data"+creditCardMyBankDatabase.read(0)+" and "+creditCardMyBankDatabase.read(1));
        card1.setBalance(5000);
        creditCardMyBankDatabase.update(0,card1);
        System.out.println("updated credit card details are "+creditCardMyBankDatabase.read(0));
        creditCardMyBankDatabase.delete(1);
        System.out.println("Credit card 1 deleted");

        MyBankDatabase<Transaction> transactionMyBankDatabase=new MyBankDatabase<>();
        Transaction transaction1=new Transaction(1,2345,"20Feb2024");
        Transaction transaction2=new Transaction(2,235,"23Feb2024");
        transactionMyBankDatabase.create(transaction1);
        transactionMyBankDatabase.create(transaction2);
        System.out.println("transactions done");
        System.out.println(" Transaction are "+transactionMyBankDatabase.read(0)+" and "+transactionMyBankDatabase.read(1));
        transaction1.setAmount(34566);
        transactionMyBankDatabase.update(0,transaction1);
        System.out.println("updated transaction is"+transactionMyBankDatabase.read(0));
        transactionMyBankDatabase.delete(1);
        System.out.println("Transaction deleted");


    }
}
