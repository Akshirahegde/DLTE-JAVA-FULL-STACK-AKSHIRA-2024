package lookingup;

import javax.naming.Context;
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
import java.sql.SQLException;

@WebServlet("/cards")
public class ReadingCreditCards extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection=null;
        Context context= null;
        try {
            context = new InitialContext();
            Connection connection1=null;
            DataSource dataSource=(DataSource) context.lookup("java:/OracleDS");
            connection1=dataSource.getConnection();
        }catch (SQLException e){

        }


    }
}
