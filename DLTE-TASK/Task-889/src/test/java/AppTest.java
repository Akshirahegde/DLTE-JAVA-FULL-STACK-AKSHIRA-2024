import application.db.Entities.Customer;
import application.db.Services.UserInfoServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.rest.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    private UserInfoServices services;
    //
    @Mock
    private HttpServletRequest request;
    //
    @Mock
    private HttpServletResponse response;

    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;

    @Before
    public void initiate() throws IOException {
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void testReadAll() throws ServletException, IOException {
        TransactionService readAllService = new TransactionService();
        // setting mock service
        readAllService.userInfoServices = services;

        StringBuilder builder = new StringBuilder("Deposit,0");
        builder.append("," + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer customer = new Customer("Samanyu", "samanyu123", "Karkala", "samanyu@gmail", 765429876L, 100L, transactionOne);
        Customer customer1 = new Customer("Shathaayu", "shathaayu123", "Karkala", "shathaayu@gmail", 765420876L, 190L, transactionOne);
        //Customer customer2=new Customer("Riyaan","riyaan123","Hebri","riyaan@gmail.com",7686543245L,786L,transactionOne);
        List<Customer> customers = Stream.of(customer, customer1).collect(Collectors.toList());

        when(services.callFindusername(anyString())).thenReturn(customer);

        readAllService.doGet(request, response);
        when(request.getParameter("username")).thenReturn("Samanyu");
        verify(response).setContentType("application/json");
        verify(services).callFindusername(anyString());

    }

    @Test
    public void findUsername() throws ServletException, IOException {
        TransactionService readByIdService = new TransactionService();
        // setting mock service
        readByIdService.userInfoServices = services;
        StringBuilder builder = new StringBuilder("Deposit,0");
        builder.append("," + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer customer = new Customer("Samanyu", "samanyu123", "Karkala", "samanyu@gmail", 765429876L, 100L, transactionOne);
        Customer customer1 = new Customer("Shathaayu", "shathaayu123", "Karkala", "shathaayu@gmail", 765420876L, 190L, transactionOne);
        // Customer customer2=new Customer("Riyaan","riyaan123","Hebri","riyaan@gmail.com",7686543245L,786L,transactionOne);
        List<Customer> customers = Stream.of(customer, customer1).collect(Collectors.toList());


        when(request.getParameter("username")).thenReturn("Samanyu");

        when(services.callFindusername(anyString())).thenReturn(customer1);


        readByIdService.doGet(request, response);

        verify(response).setContentType("application/json");

        verify(services).callOneUserTransact(anyString());
        assertNotEquals("expected: ", "[{\"username\":\"Samanyu\",\"password\":\"samanyu123\",\"address\":\"Karkala\",\"email\":\"samanyu@gmail\",\"contact\":765429876L,\"initialBalace\":100L,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]},{\"username\":\"Shathaayu\",\"password\":\"shathaayu123\",\"address\":\"Karkala\",\"email\":\"shathaayu@gmail\",\"contact\":765420876L,\"initialBalace\":190L,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]}]", stringWriter.toString().trim());

    }

    @Test
    public void findByDateUserName() throws ServletException, IOException {
        TransactionService readByDateAndUserNameService = new TransactionService();
        // setting mock service
        readByDateAndUserNameService.userInfoServices = services;
        StringBuilder builder = new StringBuilder("Deposit,0");
        builder.append("," + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        ArrayList<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(builder);
        Customer customer = new Customer("Samanyu", "samanyu123", "Karkala", "samanyu@gmail", 765429876L, 100L, transactionOne);
        Customer customer1 = new Customer("Shathaayu", "shathaayu123", "Karkala", "shathaayu@gmail", 765420876L, 190L, transactionOne);
        //   Customer customer2=new Customer("Riyaan","riyaan123","Hebri","riyaan@gmail.com",7686543245L,786L,transactionOne);
        String username = "Samanyu";
        String date = "18-03-2024";

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("date")).thenReturn(date);

        when(services.callTransactionByDate(username, date)).thenReturn(Collections.singletonList(username));
        // readByDateAndUserNameService.doGet(request,response);
//        verify(response).setContentType("application/json");
        verify(services).callTransactionByDate(anyString(), anyString());
        assertNotEquals("expected: ", "[{\"username\":\"Samanyu\",\"password\":\"samanyu123\",\"address\":\"Karkala\",\"email\":\"samanyu@gmail\",\"contact\":765429876L,\"initialBalace\":100L,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]},{\"username\":\"Shathaayu\",\"password\":\"shathaayu123\",\"address\":\"Karkala\",\"email\":\"shathaayu@gmail\",\"contact\":765420876L,\"initialBalace\":190L,\"transactionDetails\":[\"Deposit\",0,\"18-03-2024\"]}]", stringWriter.toString().trim());

    }

}
