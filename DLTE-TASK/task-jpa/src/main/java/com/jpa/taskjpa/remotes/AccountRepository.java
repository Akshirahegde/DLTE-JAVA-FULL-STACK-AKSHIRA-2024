package com.jpa.taskjpa.remotes;

import com.jpa.taskjpa.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account,Long> {
//    Account save(Account account);
//
//    List<Account> findAll();
}
