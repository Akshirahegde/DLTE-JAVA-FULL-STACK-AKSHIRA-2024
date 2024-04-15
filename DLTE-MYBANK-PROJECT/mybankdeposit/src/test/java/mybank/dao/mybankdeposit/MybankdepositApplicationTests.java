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
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.math.BigDecimal;
//import java.sql.SQLSyntaxErrorException;
//import java.util.*;
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
//        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
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
//        mockDepositList.add(new DepositAvailable(1234L, "Fixed Deposit", 10.09, "Active", "Fixed Deposit available to the customer"));
//        mockDepositList.add(new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer"));
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
//        List<DepositAvailable> result = depositService.listAllDeposits();
//        assertEquals(4.5, result.get(1).getDepositRoi());//test will fail
//        assertEquals(9.0,result.get(0).getDepositType());
//    }
//
//
//
//    @Test
//    void testSearchDepositById_Success() {
//        Map<String, Object> fakeResults = new HashMap<>();
//        fakeResults.put("id", BigDecimal.valueOf(1L));
//        fakeResults.put("name", "Savings");
//        fakeResults.put("roi", 3.5);
//        fakeResults.put("type", "Regular");
//        fakeResults.put("description", "Standard Savings Deposit");
//
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenReturn(fakeResults);
//        Optional<DepositAvailable> result = depositService.searchDepositById(1L);
//
//        assertTrue(result.isPresent());
//        assertEquals(1L, result.get().getDepositId());
//        assertEquals("Savings", result.get().getDepositName());
//        assertEquals(3.5, result.get().getDepositRoi());
//    }
//
//    @Test
//    void testSearchDepositById_Failure() {
//        Map<String, Object> fakeResults = new HashMap<>();
//        fakeResults.put("id", BigDecimal.valueOf(1L));
//        fakeResults.put("name", "Savings");
//        fakeResults.put("roi", 3.5);
//        fakeResults.put("type", "Regular");
//        fakeResults.put("description", "Standard Savings Deposit");
//
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenReturn(fakeResults);
//        Optional<DepositAvailable> result = depositService.searchDepositById(1L);
//
//        assertTrue(result.isPresent());
//        assertEquals("irregular", result.get().getDepositType());//fails
//        assertEquals("Standard Savings Deposit", result.get().getDepositDescription());
//    }
//    @Test
//    void testSearchDepositById_NotFound() {
//        Map<String, Object> fakeResults = new HashMap<>();
//        fakeResults.put("id", BigDecimal.valueOf(1L));
//        fakeResults.put("name", "Savings");
//        fakeResults.put("roi", 3.5);
//        fakeResults.put("type", "Regular");
//        fakeResults.put("description", "Standard Savings Deposit");
//
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(List.class))).thenReturn(fakeResults);
//        Optional<DepositAvailable> result = depositService.searchDepositById(2L);
//        assertTrue(result==null);
//    }
//
//}
//
//