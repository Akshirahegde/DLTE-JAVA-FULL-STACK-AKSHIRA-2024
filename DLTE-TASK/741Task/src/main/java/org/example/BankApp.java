package org.example;

import java.util.ArrayList;
import java.util.Date;

public interface BankApp {
    ArrayList<Loan> loan = new ArrayList<>();

    void filterDate(Date startDate, Date endDate);
}
