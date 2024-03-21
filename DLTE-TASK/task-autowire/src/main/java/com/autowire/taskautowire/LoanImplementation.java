package com.autowire.taskautowire;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LoanImplementation {
    List<Loan> loans= Stream.of(new Loan(123L,890L,"Raksha",8908765432L,"home"),new Loan(234L,567L,"Rakshitha",9876542345L,"personal"),new Loan(167L,678L,"Bhavya",908762345L,"home")).collect(Collectors.toList());
    List<Loan> findALL();
}
