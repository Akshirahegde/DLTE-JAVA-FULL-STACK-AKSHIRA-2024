package org.rest;


import application.db.Middlewares.DatabaseTarget;
import application.db.Remotes.StorageTarget;
import application.db.Services.UserInfoServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/transaction/service/*")
public class TransactionService extends HttpServlet {
    public UserInfoServices userInfoServices;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            StorageTarget storageTarget = new DatabaseTarget();
            UserInfoServices userInfoServices = new UserInfoServices(storageTarget);
            if (req.getParameter("username") != null) {
                List<List> transaction = new ArrayList<>();
                transaction = (List<List>) userInfoServices.callFindusername((req.getParameter("username")));
                for (int index = 0; index < transaction.size(); index++) {
                    resp.getWriter().println(transaction.get(index));
                }
            } else if (req.getParameter("username") != null && req.getParameter("date") != null) {
                List<List> transaction = new ArrayList<>();
                transaction = userInfoServices.callTransactionByDate(String.valueOf(req.getParameter("username")), String.valueOf(req.getParameter("date")));
                for (int index = 0; index < transaction.size(); index++) {
                    resp.getWriter().println(transaction.get(index));
                }
            } else {
                List<List> transaction = new ArrayList<>();
                transaction = userInfoServices.callFindAll();
                for (int index = 0; index < transaction.size(); index++) {
                    resp.getWriter().println(transaction.get(index));
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
    }
}
