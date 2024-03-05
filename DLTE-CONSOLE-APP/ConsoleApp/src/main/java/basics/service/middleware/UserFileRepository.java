package basics.service.middleware;

import basics.service.entity.Accounts;

public interface UserFileRepository {
    List<Accounts> findAll();
    boolean verifyPassword(String userName,String password);
    void deposit(String userName,String password,double amount)
}
