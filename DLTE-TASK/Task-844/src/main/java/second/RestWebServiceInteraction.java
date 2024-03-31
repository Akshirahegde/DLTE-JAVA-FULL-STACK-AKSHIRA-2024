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

@WebServlet("/second/api/")
public class RestWebServiceInteraction extends HttpServlet {
    List<Transaction> transactions;

    @Override
    public void init() throws ServletException {
        transactions = Stream.of(new Transaction(1000.0, "Raksha", "Health"),
                new Transaction(10006.0, "Rakshitha", "Friend"),
                new Transaction(1230.0, "Rashmitha", "Family"),
                new Transaction(1000.0, "Swasthi", "Emergency")).collect(Collectors.toList());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//
//
//        PrintWriter writer = resp.getWriter();
//
//
//        writer.println("<!DOCTYPE html>");
//        writer.println("<html>");
//        writer.println("<head>");
//        writer.println("<title>Transactions</title>");
//        writer.println("</head>");
//        writer.println("<body>");
//        writer.println("<h1>Transactions</h1>");
//
//
//        for (Transaction transaction : transactions) {
//            writer.println("<div>");
//            writer.println("<p>Amount: " + transaction.getAmountInTransaction() + "</p>");
//            writer.println("<p>Name: " + transaction.getTransactionReciever() + "</p>");
//            writer.println("<p>Type: " + transaction.getRemarks() + "</p>");
//            writer.println("</div>");
//            writer.println("<br>");
//        }
//
//        writer.println("</body>");
//        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
        String requestMax = req.getParameter("max");
        String requestMin = req.getParameter("min");
        if (requestMax != null && requestMin != null) {
            int max = Integer.parseInt(requestMax);
            int min = Integer.parseInt(requestMin);
            Gson gson = new Gson();
            resp.setContentType("application/json");
            for (Transaction each : transactions) {
                if (each.getAmountInTransaction() > min && each.getAmountInTransaction() < max) {

                    resp.getWriter().println(gson.toJson(each));
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            Gson gson = new Gson();
            resp.setContentType("application/json");
            String json = gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Transaction transactions1 = gson.fromJson(req.getReader(), Transaction.class);
        transactions.add(transactions1);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(transactions1.getAmountInTransaction() + " " + transactions1.getTransactionReciever() + " " + transactions1.getRemarks());
    }
}
