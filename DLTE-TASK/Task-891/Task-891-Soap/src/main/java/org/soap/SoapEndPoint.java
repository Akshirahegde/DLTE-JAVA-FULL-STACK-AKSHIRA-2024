package org.soap;

import javax.xml.ws.Endpoint;
import java.sql.SQLException;

public class SoapEndPoint{
    private static String url = "http://localhost:3001/username";

    public static void main(String[] args) throws SQLException {
        AccountSoap accountSoap = new AccountSoap();
        System.out.println("Web Service Hosted @"+url);
        Endpoint.publish(url,accountSoap);
    }
}
