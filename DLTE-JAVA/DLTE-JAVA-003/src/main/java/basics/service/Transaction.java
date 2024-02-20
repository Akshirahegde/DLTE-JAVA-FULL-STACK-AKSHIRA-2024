package basics.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {

//    Transaction: dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)
//    Array of Objects
//    Analysis:
//    Filter based on given ranges of date
//    least amount transferred
//    maximum amount transferred
//    number of transaction made to particular beneficiary
//    filter based on particular remarks
//    Sort:
//    based on Beneficiary in descending
//    based on amount in ascending
    private Date dateOfTransaction;
    private double amountInTransaction;
    private  String transactionReciever;
    private String Remarks;

    public Transaction() {
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public Double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Double amountInTransaction) {
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
}
