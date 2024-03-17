package first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name="MyFirstSerevlet",value="/first/api/*")
@WebServlet("/first/api/")
public class MyFirstServlet extends HttpServlet {
    Logger logger;
    @Override
    public void destroy() {
        //super.destroy();
        logger.info("Destroyed");
    }

    @Override
    public void init() throws ServletException {
        logger= LoggerFactory.getLogger(MyFirstServlet.class);
        logger.info("Initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String path=req.getPathInfo();
        logger.info("get method");
      // logger.info("recieved" +req.getParameter("lumpSum"));
        if(path==null||path.equals("/")) {
            logger.info("Servlets get method has executed");
        }
//        else if(path.equals("lumpSum"))
//            logger.info("Recieved lumpsum"+req.getParameter("lumpSum"));
      //  else
            //String data=
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        logger.info("post method");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPut(req, resp);
        logger.info("put method");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        logger.info("delete method");
    }
}
