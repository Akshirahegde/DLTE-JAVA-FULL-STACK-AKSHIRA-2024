package org.example;

public class Transaction {
    private int id;
    private double amount;
    private String date;

    public Transaction(int id, double amount, String date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount(int i) {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", Date='" + date + '\'' +
                '}';
    }
}
