package org.example;

import application.db.Middlewares.DatabaseTarget;
import application.db.Services.UserInfoServices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class MyCardOfficial {
    public static void main(String[] args) throws NamingException, RemoteException {
        Hashtable properties=new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
        Context context=new InitialContext(properties);
        MyCardFunctions userInfoServices = (MyCardFunctions)context.lookup("java:/card-filter");
        userInfoServices.fetchOverLimit().forEach(System.out::println);
    }
}
