package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Optional;

import static org.junit.Assert.*;

public class LoanAppTest {
    private final ByteArrayOutputStream outputStreamCaptor=new ByteArrayOutputStream();
    @Before
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void addLoan() throws IOException {
        LoanApp loanApp = new LoanApp();
//        File file=new File();
//        FileInputStream fileInputStream=new FileInputStream(file);
//        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        ByteArrayInputStream in=new ByteArrayInputStream("123,6000.0,20Feb2024,open,Raksha,87765498764".getBytes());
        System.setIn(in);
        try{
            loanApp.addLoan();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //this case will pass
      assertEquals("Loan{loanNumber=123,loanAmount=6000.0,loanDate=20Feb2024,loanStatus=open,borrowerName=Raksha,borrowerContact=87765498764",outputStreamCaptor.toString().trim());
    }

    @Test
    public void availableLoans() {
        LoanApp loanApp=new LoanApp();
//        ByteArrayInputStream in=new ByteArrayInputStream("123,6000.0,20Feb2024,open,Raksha,87765498764".getBytes());
//        System.setIn(in);
        try {
            loanApp.availableLoans();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //this case should fail as there is open loans
        assertEquals("No open loans",outputStreamCaptor.toString().trim());

    }

    @Test
    public void closedLoans() {
        LoanApp loanApp=new LoanApp();
        try {
            loanApp.closedLoans();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //this case should pass as there is no closed loans
        assertEquals("No loans are closed",outputStreamCaptor.toString().trim());
    }

}