package mybank.dao.mybankdeposit.interfaces;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.exception.DepositException;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepositInterface {
    List<DepositAvailable> listAllDeposits() ;
    Optional<DepositAvailable> searchDepositById(Long Id) throws SQLSyntaxErrorException, DepositException;
    Long getDepositIdByDepositName(String depositName);
}







