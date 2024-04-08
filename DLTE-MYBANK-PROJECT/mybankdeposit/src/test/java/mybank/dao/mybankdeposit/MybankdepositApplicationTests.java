//package mybank.dao.mybankdeposit;
//
//import mybank.dao.mybankdeposit.entity.DepositAvailable;
//import mybank.dao.mybankdeposit.exception.DepositException;
//import mybank.dao.mybankdeposit.service.DepositService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class MybankdepositApplicationTests {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    private DepositService depositService;
//
//    @Test
//    void callviewAllDepositAvailable() throws SQLSyntaxErrorException {
//        List<DepositAvailable> mockDepositList = new ArrayList<>();
//        mockDepositList.add(new DepositAvailable(1234, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
//        List<DepositAvailable> result = depositService.listAllDeposits();
//        assertEquals(2, result.size());
//        assertEquals(10.09, result.get(0).getDepositRoi());
//        assertEquals("Closed", result.get(1).getDepositType());
//
//    }
//
//
//    @Test
//    void testListAllDeposits_EmptyResult() {
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.emptyList());
//        try {
//            depositService.listAllDeposits();
//        } catch (DepositException | SQLSyntaxErrorException e) {
//            assertEquals("deposit exception", e.getMessage());
//        }
//
//    }
//
//    @Test
//    void callviewAllDepositAvailable_Fail() throws SQLSyntaxErrorException {
//        List<DepositAvailable> mockDepositList = new ArrayList<>();
//        mockDepositList.add(new DepositAvailable(1234, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
//        List<DepositAvailable> result = depositService.listAllDeposits();
//        assertEquals(4.5, result.get(1).getDepositRoi());//test will fail
//        assertEquals(9.0,result.get(0).getDepositType());
//    }
//}
//
//
