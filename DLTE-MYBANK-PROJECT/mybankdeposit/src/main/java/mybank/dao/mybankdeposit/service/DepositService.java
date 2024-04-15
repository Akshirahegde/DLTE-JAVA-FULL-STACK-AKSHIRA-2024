package mybank.dao.mybankdeposit.service;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.entity.DepositAvailed;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

@Service
public class DepositService implements DepositInterface {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(DepositService.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<DepositAvailable> listAllDeposits() throws SQLSyntaxErrorException {
        List<DepositAvailable> deposit;
        try {
            deposit = jdbcTemplate.query("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_depositavailable",
                    new BeanPropertyRowMapper<>(DepositAvailable.class));
        } catch (DataAccessException sqlException) {
            throw new SQLSyntaxErrorException();
        }
        if (deposit.size() == 0) {
            logger.error(resourceBundle.getString("deposit.exception"));
            throw new DepositException(resourceBundle.getString("deposit.exception"));
        } else
            return deposit;
    }

    @Override
    public List<DepositAvailable> searchDepositByRoi(Double roi) {
        return null;
    }

    @Override
    public Optional<DepositAvailable> searchDepositById(Long id) throws DepositException {
        try {
            DepositAvailable depositAvailable = new DepositAvailable();
            Optional<DepositAvailable> deposit = Optional.of(depositAvailable);

            deposit.get().setDepositId(id);
            CallableStatementCreator creator = con -> {
                CallableStatement statement = con.prepareCall("{call read_depositsId(?,?,?,?,?,?)}");
                statement.setLong(1, id);
                statement.registerOutParameter(2, Types.NUMERIC);
                statement.registerOutParameter(3, Types.DOUBLE);
                statement.registerOutParameter(4, Types.VARCHAR);
                statement.registerOutParameter(5, Types.VARCHAR);
                statement.registerOutParameter(6, Types.VARCHAR);
                return statement;
            };

            List<SqlParameter> returned = Arrays.asList(
                    new SqlParameter(Types.NUMERIC),
                    new SqlOutParameter("id", Types.NUMERIC),
                    new SqlOutParameter("roi", Types.DOUBLE),
                    new SqlOutParameter("name", Types.VARCHAR),
                    new SqlOutParameter("type", Types.VARCHAR),
                    new SqlOutParameter("description", Types.VARCHAR)
            );

            Map<String, Object> returnedDeposits = jdbcTemplate.call(creator, returned);
            System.out.println(returnedDeposits);
            BigDecimal id_out = (BigDecimal) returnedDeposits.get("id");
            if (id_out != null) {
                Long longValue = id_out.longValue();
                depositAvailable.setDepositId(longValue);
            }
            depositAvailable.setDepositName((String) returnedDeposits.get("name"));
            depositAvailable.setDepositRoi((Double) returnedDeposits.get("roi"));
            depositAvailable.setDepositType((String) returnedDeposits.get("type"));
            depositAvailable.setDepositDescription((String) returnedDeposits.get("description"));
            return Optional.of(depositAvailable);
        } catch (DataAccessException exception) {
            if (exception.getLocalizedMessage().contains("ORA-20002"))
                throw new DepositException(resourceBundle.getString("deposit.exception"));
            if (exception.getLocalizedMessage().contains("ORA-20003"))
                throw new DepositException(resourceBundle.getString("internal.server.error"));
        }

       return Optional.empty();
    }



    @Override
    public DepositAvailed availDeposit(DepositAvailed depositAvailed) {
        return null;
    }

}





































































































//   @Override
//    public Optional<DepositAvailable> searchDepositById(long Id) throws SQLSyntaxErrorException {
//        Optional<DepositAvailable> deposit;
//
//        try {
//            deposit = Optional.of(jdbcTemplate.queryForObject("select deposit_id,deposit_name,deposit_roi,deposit_type,deposit_description from mybank_app_depositavailable where deposit_id=? ",
//                    new Object[]{Id},
//                    new BeanPropertyRowMapper<>(DepositAvailable.class)
//            ));
//        }catch (DataAccessException sqlException){
//            logger.error(resourceBundle.getString("internal.error"));
//            throw new SQLSyntaxErrorException();
//        }
//        if(deposit==null) {
//            logger.error(resourceBundle.getString("deposit.exception"));
//            throw new DepositException(resourceBundle.getString("deposit.exception"));
//        }
//        else
//            return deposit;
//    }

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
















































































































