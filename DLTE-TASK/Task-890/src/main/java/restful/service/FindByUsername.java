package restful.service;

import application.db.Entities.Customer;
import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user/name")
public class FindByUsername extends HttpServlet {
    UserInfoServices userInfoServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("username") != null) {
            String username = req.getParameter("username");
            List customer = (List) new Customer();
            customer = userInfoServices.callFindAll();
            //customer = userInfoServices.(String.valueOf(req.getParameter("username")));
            Gson gson = new Gson();
            String details = gson.toJson(customer);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(customer.get(1));
        }


    }

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = null;
        try {
            storageTarget = new DatabaseTarget();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userInfoServices = new UserInfoServices(storageTarget);

    }
}
