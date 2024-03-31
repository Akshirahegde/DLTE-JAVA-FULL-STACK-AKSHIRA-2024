package second;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/test")
public class RestWebService extends HttpServlet {
    // Sample transaction data
    List<Transaction> transactions = Stream.of(
            new Transaction(1000.0, "Raksha", "Health"),
            new Transaction(10006.0, "Rakshitha", "Friend"),
            new Transaction(1230.0, "Rashmitha", "Family"),
            new Transaction(1000.0, "Swasthi", "Emergency")
    ).collect(Collectors.toList());


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transaction Details</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transaction Details</h1>");


        for (Transaction transaction : transactions) {
            writer.println("<div>");
            writer.println("<p>Amount: " + transaction.getAmountInTransaction() + "</p>");
            writer.println("<p>Name: " + transaction.getTransactionReciever() + "</p>");
            writer.println("<p>Type: " + transaction.getRemarks() + "</p>");
            writer.println("</div>");
            writer.println("<br>");
        }

        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactions.add(transaction);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transaction Insertion</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transaction Added</h1>");
        writer.println("<p>" + transaction.getTransactionReciever() + " has been added to the records.</p>");
        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}