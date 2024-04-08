package mybank.dao.mybankdeposit.service;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.entity.DepositAvailed;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class DepositService implements DepositInterface {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger=LoggerFactory.getLogger(DepositService.class);
@Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<DepositAvailable> listAllDeposits() throws SQLSyntaxErrorException {
        List<DepositAvailable> deposit;
        try {
            deposit = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_depositavailable",
                    new BeanPropertyRowMapper<>(DepositAvailable.class));
        }catch (DataAccessException sqlException){
            throw new SQLSyntaxErrorException();
        }
        if(deposit.size()==0) {
            logger.error(resourceBundle.getString("deposit.exception"));
            throw new DepositException(resourceBundle.getString("deposit.exception"));
        }
        else
            return deposit;
    }

    @Override
    public List<DepositAvailable> searchDepositByRoi(double roi) {
        return null;
    }

    @Override
    public Optional<DepositAvailable> searchDepositById(long Id) throws SQLSyntaxErrorException {
        Optional<DepositAvailable> deposit;

        try {
            deposit = Optional.of(jdbcTemplate.queryForObject("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_depositavailable where deposit_id=? ",
                    new Object[]{Id},
                    new BeanPropertyRowMapper<>(DepositAvailable.class)
            ));
        }catch (DataAccessException sqlException){
            logger.error(resourceBundle.getString("internal.error"));
            throw new SQLSyntaxErrorException();
        }
        if(deposit==null) {
            logger.error(resourceBundle.getString("deposit.exception"));
            throw new DepositException(resourceBundle.getString("deposit.exception"));
        }
        else
            return deposit;
    }

//    @Override
//    public Optional<DepositAvailable> searchDepositById(long Id) throws SQLSyntaxErrorException {
//
//        List<DepositAvailable> deposits;
//
//        try {
//            deposits= jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_depositavailable",
//                    new BeanPropertyRowMapper<>(DepositAvailable.class));
//        }catch (DataAccessException sqlException){
//            throw new SQLSyntaxErrorException();
//        }
//
//        Optional<DepositAvailable> depositid=deposits.stream().filter(depositAvailable -> depositAvailable.getDepositId()==Id).findFirst();
//        System.out.println(depositid);
//        if (depositid==null)
//            throw new DepositException(resourceBundle.getString("deposit.exception"));
//        return depositid;
//    }



    @Override
    public DepositAvailed availDeposit(DepositAvailed depositAvailed) {
        return null;
    }
}
