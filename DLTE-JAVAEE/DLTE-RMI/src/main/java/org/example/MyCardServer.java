package org.example;

import application.db.Middlewares.DatabaseTarget;
import application.db.Services.UserInfoServices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MyCardServer extends UnicastRemoteObject implements MyCardFunctions, Serializable {
    private static Context context;
    private Registry registry;
private UserInfoServices userInfoServices;

   @Override
   public List<UserInfoServices> fetchOverLimit() throws RemoteException {
      List<UserInfoServices> userInfo =userInfoServices.callFindAll();
      List<UserInfoServices> returned= new ArrayList<>();
      for(UserInfoServices each: userInfo){
          returned.add(each);
      }
       return returned;
   }

    public MyCardServer() throws RemoteException, SQLException {
        super();
        userInfoServices=new UserInfoServices(new DatabaseTarget());
        try {
            registry= LocateRegistry.createRegistry(3030);
            Hashtable properties=new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
            properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
            context=new InitialContext(properties);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws NamingException, RemoteException, SQLException {
        MyCardServer myCardServer=new MyCardServer();
        context.bind("java:/card-filter",myCardServer);
    }
}
