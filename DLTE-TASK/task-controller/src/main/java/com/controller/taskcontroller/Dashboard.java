package com.controller.taskcontroller;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/collection")
public class Dashboard {

    private List<Loan> loans= Stream.of(
    new Loan( 123,"Raksha",2000.0,9900410713L),
    new Loan(321,"Rakshitha",6789.0,7654231456L)).collect(Collectors.toList());
    @GetMapping("/{index}")
    public Loan readLoan(@PathVariable("index") int index){
        return loans.get(index);
    }
    @PostMapping("/mappingPost")
    public Loan createNewOne(@RequestBody Loan object){
        loans.add(object);
        return object;
    }
}
