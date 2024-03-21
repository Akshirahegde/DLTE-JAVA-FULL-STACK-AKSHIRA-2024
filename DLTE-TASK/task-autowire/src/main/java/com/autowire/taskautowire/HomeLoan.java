package com.autowire.taskautowire;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("homeLoan")
public class HomeLoan implements LoanImplementation {
    @Override
    public List<Loan> findALL() {
    List<Loan> homeloan = new ArrayList<>();
    for (Loan each : loans) {
        if (each.getLoanType().equals("home")) {
            homeloan.add(each);
        }
    }
    return homeloan;
}
}
