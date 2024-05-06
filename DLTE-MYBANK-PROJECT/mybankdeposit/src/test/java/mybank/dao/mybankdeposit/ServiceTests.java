package mybank.dao.mybankdeposit;

import mybank.dao.mybankdeposit.entity.MyBankCustomer;
import mybank.dao.mybankdeposit.service.DepositService;
import mybank.dao.mybankdeposit.service.MyBankServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private DepositService depositService;
    @InjectMocks
    private MyBankServices myBankServices;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void myBankCustomerGettersAndSetters() {
        Long customerId = 1L;
        String customerName = "Samanyu";
        String customerAddress = "Perdoor";
        String customerStatus = "Active";
        Long customerContact = 1234567890L;
        String username = "samanyu";
        String password = "samanyu123";
        Integer attempts = 0;
        Integer maxAttempts = 3;
        MyBankCustomer customer = new MyBankCustomer();

        customer.setCustomerId(customerId);
        customer.setCustomerName(customerName);
        customer.setCustomerAddress(customerAddress);
        customer.setCustomerStatus(customerStatus);
        customer.setCustomerContact(customerContact);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setAttempts(attempts);

        assertEquals(customerId, customer.getCustomerId());
        assertEquals(customerName, customer.getCustomerName());
        assertEquals(customerAddress, customer.getCustomerAddress());
        assertEquals(customerStatus, customer.getCustomerStatus());
        assertEquals(customerContact, customer.getCustomerContact());
        assertEquals(username, customer.getUsername());
        assertEquals(password, customer.getPassword());
        assertEquals(attempts, customer.getAttempts());
        assertEquals(maxAttempts, customer.getMaxAttempts());
    }
    @Test
    public void myBankCustomerGettersAndSetters_fail() {
        Long customerId = 1L;
        String customerName = "Samanyu";
        String customerAddress = "Perdoor";
        String customerStatus = "Active";
        Long customerContact = 1234567890L;
        String username = "samanyu";
        String password = "samanyu123";
        Integer attempts = 0;
        Integer maxAttempts = 3;
        MyBankCustomer customer = new MyBankCustomer();

        customer.setCustomerId(customerId);
        customer.setCustomerName(customerName);
        customer.setCustomerAddress(customerAddress);
        customer.setCustomerStatus(customerStatus);
        customer.setCustomerContact(customerContact);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setAttempts(attempts);

        assertNotEquals(customerId, customer.getAttempts());
        assertNotEquals(customerName, customer.getUsername());
        assertNotEquals(customerAddress, customer.getCustomerContact());
        assertNotEquals(customerStatus, customer.getPassword());
        assertNotEquals(customerContact, customer.getCustomerStatus());
    }
    @Test
    public void UserDetailsMethods() {
        MyBankCustomer customer = new MyBankCustomer();
        assertTrue(customer.isAccountNonExpired());
        assertTrue(customer.isAccountNonLocked());
        assertTrue(customer.isCredentialsNonExpired());
        assertTrue(customer.isEnabled());
    }
    @Test
    public void SignUp() {
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setUsername("Patwardhan");
        mockCustomer.setPassword("divija123");
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        MyBankCustomer result = myBankServices.signUp(mockCustomer);
        assertNotNull(result);
    }
    @Test
    void SignUp_Success() {
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setUsername("Patwardhan");
        mockCustomer.setPassword("divija123");
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        MyBankCustomer result = myBankServices.signUp(mockCustomer);
        assertEquals("Patwardhan", result.getUsername());
    }
    @Test
    void LoadUserByUsername_success() {
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setUsername("Patwardhan");
        mockCustomer.setPassword("divija123");
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class)))
                .thenReturn(mockCustomer);
        assertDoesNotThrow(() -> {
            try {
                UserDetails userDetails = myBankServices.loadUserByUsername("Patwardhan");
                assertEquals("Patwardhan", userDetails.getUsername());
            } catch (Exception e) {
            }
        });
    }
    @Test
    void testLoadUserByUsername_fail() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class)))
                .thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> myBankServices.loadUserByUsername("anusha"));
    }
    @Test
    void FindByUsername() {
        List<MyBankCustomer> mockCustomersList = new ArrayList<>();
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setUsername("Patwardhan");
        mockCustomersList.add(mockCustomer);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(mockCustomersList);
        MyBankCustomer result = myBankServices.findByUsername("Patwardhan");
        assertNotNull(result);
        assertEquals("Patwardhan", result.getUsername());
    }
    @Test
    void UpdateAttempts() {
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setUsername("Patwardhan");
        mockCustomer.setAttempts(4);
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        assertDoesNotThrow(() -> myBankServices.updateAttempts(mockCustomer));
    }
    @Test
    void testUpdateStatus() {
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setUsername("Patwardhan");
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        assertDoesNotThrow(() -> myBankServices.updateStatus(mockCustomer));
    }
    @Test
    void testGetCustomerName() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class))).thenReturn("Divija");
        String customerName = myBankServices.getCustomerName("Divija");
        assertEquals("Divija", customerName);
    }
    @Test
    void GetCustomerName_fail() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class))).thenReturn("Patwardhan");
        String customerName = myBankServices.getCustomerName("Divija");
        assertNotEquals("Divija", customerName);
    }



}




