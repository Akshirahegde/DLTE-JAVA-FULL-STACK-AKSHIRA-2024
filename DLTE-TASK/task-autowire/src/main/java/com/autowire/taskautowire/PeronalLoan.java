package com.autowire.taskautowire;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("personalLoan")
public class PeronalLoan implements LoanImplementation {
    @Override
    public List<Loan> findALL() {
        List<Loan> personalloan=new ArrayList<>();
        for(Loan each:loans){
            if(each.getLoanType().equals("personal")){
                personalloan.add(each);
            }
        }
        return personalloan;
    }
}
