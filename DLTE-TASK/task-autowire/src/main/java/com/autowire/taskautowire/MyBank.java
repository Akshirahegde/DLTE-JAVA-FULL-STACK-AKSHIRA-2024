package com.autowire.taskautowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyBank {
    @Autowired
//    @Qualifier("home")
    @Qualifier("personalLoan")
    private LoanImplementation loanImplementation;
    public List<Loan> callFindAll() {
        List<Loan> injectedLoan = loanImplementation.findALL();
        return injectedLoan;
    }
}
