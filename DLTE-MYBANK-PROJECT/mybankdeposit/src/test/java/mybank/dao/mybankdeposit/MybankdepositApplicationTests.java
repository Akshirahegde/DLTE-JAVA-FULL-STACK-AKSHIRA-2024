package mybank.dao.mybankdeposit;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.service.DepositService;
import mybank.dao.mybankdeposit.service.MyBankServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MybankdepositApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private DepositService depositService;
//    @InjectMocks
//    private MyBankServices myBankServices;

    @Test
    void ListAllDeposits() throws SQLException {
        List<DepositAvailable> expectedDeposits = new ArrayList<>();
        expectedDeposits.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
        expectedDeposits.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available t"));
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedDeposits);
        List<DepositAvailable> result = depositService.listAllDeposits();
        assertSame(expectedDeposits, result);
    }
    @Test
    void depositValues() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
        List<DepositAvailable> result = depositService.listAllDeposits();
        assertEquals(10.09, result.get(0).getDepositRoi());
        assertEquals("Closed", result.get(1).getDepositType());
    }
    @Test
    void depositInvalidValues() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
        List<DepositAvailable> result = depositService.listAllDeposits();
        assertNotEquals(11.09, result.get(0).getDepositRoi());
        assertNotEquals("Open", result.get(1).getDepositType());
    }

    @Test
    void searchDepositById_Success() throws Exception {
        Long id = 1L;
        Map<String, Object> returnedDeposits = new HashMap<>();
        returnedDeposits.put("id", new BigDecimal(id));
        returnedDeposits.put("name", "Term Deposit");
        returnedDeposits.put("roi", 9.8);
        returnedDeposits.put("type", "Type");
        returnedDeposits.put("description", "Description");
        when(jdbcTemplate.call(any(), anyList())).thenReturn(returnedDeposits);
        Optional<DepositAvailable> optionalDeposit = depositService.searchDepositById(id);
        assertTrue(optionalDeposit.isPresent());
        assertEquals(id, optionalDeposit.get().getDepositId());
    }

    @Test
    void searchDepositById_Failure() throws Exception {
        Long id = 1L;
        Map<String, Object> returnedDeposits = new HashMap<>();
        returnedDeposits.put("id", new BigDecimal(id));
        returnedDeposits.put("name", "Term Deposit");
        returnedDeposits.put("roi", 9.8);
        returnedDeposits.put("type", "Type");
        returnedDeposits.put("description", "Description");
        when(jdbcTemplate.call(any(), anyList())).thenReturn(returnedDeposits);
        Optional<DepositAvailable> optionalDeposit = depositService.searchDepositById(id);
        assertNotEquals(" Fixed deposit",optionalDeposit.get().getDepositName());
        assertEquals("Term Deposit",optionalDeposit.get().getDepositName());
    }
    @Test
    void searchDepositById_RuntimeException() {
        Long id = 1L;
        when(jdbcTemplate.call(any(), anyList())).thenThrow(new RuntimeException("ORA-20002"));
        assertThrows(RuntimeException.class, () -> depositService.searchDepositById(id));
    }

    @Test
    void callviewAllDepositAvailable() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
        List<DepositAvailable> result = depositService.listAllDeposits();
        assertEquals(2, result.size());
    }
    @Test
    void callviewAllDepositAvailable_fail() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
        List<DepositAvailable> result = depositService.listAllDeposits();
        assertNotEquals(1, result.size());
    }
@Test
public void DepositAvailableConstructorAndGetters() {
    Long depositId = 1L;
    String depositName = "Savings";
    Double depositRoi = 5.0;
    String depositType = "Fixed";
    String depositDescription = "A savings account with fixed interest rate.";
    DepositAvailable deposit = new DepositAvailable(depositId, depositName, depositRoi, depositType, depositDescription);

    assertEquals(depositId, deposit.getDepositId());
    assertEquals(depositName, deposit.getDepositName());
    assertEquals(depositRoi, deposit.getDepositRoi());
    assertEquals(depositType, deposit.getDepositType());
    assertEquals(depositDescription, deposit.getDepositDescription());
}





}














//    @Test
//    void callviewAllDepositAvailable() throws SQLSyntaxErrorException {
//        List<DepositAvailable> mockDepositList = new ArrayList<>();
//        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
//        List<DepositAvailable> result = depositService.listAllDeposits();
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    void depositSize() throws SQLSyntaxErrorException {
//        List<DepositAvailable> mockDepositList = new ArrayList<>();
//        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
//        List<DepositAvailable> result = depositService.listAllDeposits();
//        assertNotEquals(3, result.size());
//    }
//
//    @Test
//    void depositValues() throws SQLSyntaxErrorException {
//        List<DepositAvailable> mockDepositList = new ArrayList<>();
//        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
//        List<DepositAvailable> result = depositService.listAllDeposits();
//        assertEquals(10.09, result.get(0).getDepositRoi());
//        assertEquals("Closed", result.get(1).getDepositType());
//    }
//    @Test
//    void depositInvalidValues() throws SQLSyntaxErrorException {
//        List<DepositAvailable> mockDepositList = new ArrayList<>();
//        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
//        List<DepositAvailable> result = depositService.listAllDeposits();
//        assertNotEquals(11.09, result.get(0).getDepositRoi());
//        assertNotEquals("Open", result.get(1).getDepositType());
//    }
//
//
//    @Test
//    void testListAllDeposits_EmptyResult() {
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.emptyList());
//        try {
//            depositService.listAllDeposits();
//        } catch (DepositException | SQLSyntaxErrorException e) {
//            assertNotEquals("deposit exception", e.getMessage());
//        }
//
//    }
//    @Test
//    void testListAllDeposits_Result() {
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.emptyList());
//        try {
//            depositService.listAllDeposits();
//        } catch (DepositException | SQLSyntaxErrorException e) {
//            assertEquals("No deposits Available", e.getMessage());
//        }
//
//    }
//
//    @Test
//    void callviewAllDepositAvailable_Fail() throws SQLSyntaxErrorException {
//        List<DepositAvailable> mockDepositList = new ArrayList<>();
//        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
//        List<DepositAvailable> result = depositService.listAllDeposits();
//        assertNotEquals(4.5, result.get(1).getDepositRoi());
//        assertNotEquals(9.0,result.get(0).getDepositType());
//    }
//
//    @Test
//    void testSearchDepositById_Success() throws SQLSyntaxErrorException {
//        Map<String, Object> deposits = new HashMap<>();
//        deposits.put("id", BigDecimal.valueOf(1L));
//        deposits.put("name", "Savings");
//        deposits.put("roi", 3.5);
//        deposits.put("type", "Regular");
//        deposits.put("description", "Standard Savings Deposit");
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenReturn(deposits);
//        Optional<DepositAvailable> result = depositService.searchDepositById(1L);
//        // assertTrue(result.isPresent());
//        assertEquals(1L, result.get().getDepositId());
//        assertEquals("Savings", result.get().getDepositName());
//
//    }
//    @Test
//    void testSearchDepositById() throws SQLSyntaxErrorException {
//        Map<String, Object> deposits = new HashMap<>();
//        deposits.put("id", BigDecimal.valueOf(1L));
//        deposits.put("name", "Savings");
//        deposits.put("roi", 3.5);
//        deposits.put("type", "Regular");
//        deposits.put("description", "Standard Savings Deposit");
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenReturn(deposits);
//        Optional<DepositAvailable> result = depositService.searchDepositById(2L);
//        assertNotEquals("Fd", result.get().getDepositName());
//        assertNotEquals(3.5, result.get().getDepositDescription());
//    }
////--------------------
//    @Test
//    void testSearchDepositById_NotFound() throws SQLSyntaxErrorException {
//        Map<String, Object> deposits = new HashMap<>();
//        deposits.put("id", BigDecimal.valueOf(1L));
//        deposits.put("name", "Savings");
//        deposits.put("roi", 3.5);
//        deposits.put("type", "Regular");
//        deposits.put("description", "Standard Savings Deposit");
//
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenReturn(deposits);
//        Optional<DepositAvailable> result = depositService.searchDepositById(2L);
//        assertFalse(result==null);
//    }
//
//}
//---------------------------------------------------


