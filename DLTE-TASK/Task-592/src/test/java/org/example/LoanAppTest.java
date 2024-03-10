package org.example;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoanAppTest {
    static List<Loan> loanList=new ArrayList<>();
    @BeforeClass
    public static void addLoan(){
        loanList.add(new Loan(123,2000.0,"6/4/2024","open","Raksha",23456789L));
        loanList.add(new Loan(321,3000.0,"9/3/2023","closed","Rakshitha",34567678L));

    }

    @Test
    public void availableLoans() {
        //test case will fail
            String expectedLoanStatus="open";
            assertEquals(expectedLoanStatus,loanList.get(1).getLoanStatus());//this will pass
            assertEquals(expectedLoanStatus,loanList.get(2).getLoanStatus());//this will fail
        }

    @Test
    public void closedLoans() {
        //test case will fail
        String expectedLoanStatus = "closed";
        assertEquals(expectedLoanStatus, loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus, loanList.get(1).getLoanStatus());
    }

    @Test
    public void testSize(){
        //test case will pass
        assertFalse(loanList.isEmpty());

    }
    @Test
    public void checkNull(){
     //test case will pass
        assertNotNull(loanList);
    }
    @Test
    public void checkSame(){
        //test case will pass
        assertNotSame(loanList.get(0),loanList.get(1));
    }

}