package mybank.dao.mybankdeposit.interfaces;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.entity.DepositAvailed;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;
@Repository
public interface DepositInterface {
    List<DepositAvailable> listAllDeposits() throws SQLSyntaxErrorException;
    List<DepositAvailable> searchDepositByRoi(double roi);
    Optional<DepositAvailable> searchDepositById(long Id) throws SQLSyntaxErrorException;
    DepositAvailed availDeposit(DepositAvailed depositAvailed);
}
