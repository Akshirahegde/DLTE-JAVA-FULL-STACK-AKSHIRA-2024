package org.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Update/transaction")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InitialContext initialContext=new InitialContext();
            DataSource dataSource= (DataSource)initialContext.lookup("java:/OracleDS");
            try(Connection connection=dataSource.getConnection()) {
                String transactionIDRequest= req.getParameter("transaction_id");
                String amountRequest=req.getParameter("transaction_amount");
                if (transactionIDRequest!=null && amountRequest!=null ){
                    int transaction_id=Integer.parseInt(transactionIDRequest);
                    int transaction_amount=Integer.parseInt(amountRequest);
                    String sql="UPDATE Transaction SET transaction_amount=transaction_amount+? WHERE transaction_id=?";
                    try(PreparedStatement preparedStatement=connection.prepareStatement(sql)){
                        preparedStatement.setInt(1,transaction_id);
                        preparedStatement.setInt(4,transaction_amount);
                        resp.getWriter().println("Updated");
                    }
                }else{
                    throw new ServletException("Parameter passed is null");
                }

            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

    }
}
