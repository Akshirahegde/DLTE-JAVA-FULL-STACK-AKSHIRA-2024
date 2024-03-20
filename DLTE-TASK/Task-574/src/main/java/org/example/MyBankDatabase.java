package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class MyBankDatabase<T> implements Activity<T> {
    ArrayList<T> creditCard = new ArrayList<>();

    public void create(T object) throws IOException {
        creditCard.add(object);
        writeIntoFile();
    }

    private void writeIntoFile() throws IOException {
        File file = new File("Records.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(creditCard);
        objectOutputStream.close();
        fileOutputStream.close();

    }

    private void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Records.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        creditCard = (ArrayList<T>) objectInputStream.readObject();
        System.out.println(creditCard);
        objectInputStream.close();
        fileInputStream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase<CreditCard> myBankDatabase = new MyBankDatabase<>();
        CreditCard creditCard1 = new CreditCard(1234523L, 45678.0, "Raksha", new Date(2 / 1 / 2023), 123);
        CreditCard creditCard2 = new CreditCard(1456523L, 98678.0, "Rakshitha", new Date(1 / 7 / 2024), 183);
        CreditCard creditCard3 = new CreditCard(8734523L, 85678.0, "Rashmitha", new Date(9 / 9 / 2013), 763);
        myBankDatabase.create(creditCard1);
        myBankDatabase.create(creditCard2);
        myBankDatabase.create(creditCard3);
//writing into the file as soon as object is inserted
        myBankDatabase.writeIntoFile();
        myBankDatabase.readFromFile();
    }
}
