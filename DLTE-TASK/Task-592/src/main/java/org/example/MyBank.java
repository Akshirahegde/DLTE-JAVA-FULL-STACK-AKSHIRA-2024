package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MyBank {
    ArrayList<Loan>loans=new ArrayList<>();
void addLoan() throws IOException, ClassNotFoundException;
void availableLoans() throws IOException, ClassNotFoundException;
void closedLoans() throws IOException, ClassNotFoundException;


}
