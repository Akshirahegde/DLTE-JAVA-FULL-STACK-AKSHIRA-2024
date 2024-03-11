package second;

import java.util.Date;

public class Transaction {
    private double amountInTransaction;
    private  String transactionReciever;
    private String Remarks;

    public Transaction( double amountInTransaction, String transactionReciever, String remarks) {

        this.amountInTransaction = amountInTransaction;
        this.transactionReciever = transactionReciever;
        Remarks = remarks;
    }

    public Transaction() {
    }


    public double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getTransactionReciever() {
        return transactionReciever;
    }

    public void setTransactionReciever(String transactionReciever) {
        this.transactionReciever = transactionReciever;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +", amountInTransaction=" + amountInTransaction +
                ", transactionReciever='" + transactionReciever + '\'' +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }
}
