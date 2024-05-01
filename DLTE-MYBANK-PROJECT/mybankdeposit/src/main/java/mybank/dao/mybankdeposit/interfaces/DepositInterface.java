package mybank.dao.mybankdeposit.interfaces;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.entity.DepositAvailed;
import mybank.dao.mybankdeposit.exception.DepositException;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepositInterface {
    List<DepositAvailable> listAllDeposits() throws SQLSyntaxErrorException;
    List<DepositAvailable> searchDepositByRoi(Double roi);
    Optional<DepositAvailable> searchDepositById(Long Id) throws SQLSyntaxErrorException, DepositException;
    DepositAvailed availDeposit(DepositAvailed depositAvailed);
}







