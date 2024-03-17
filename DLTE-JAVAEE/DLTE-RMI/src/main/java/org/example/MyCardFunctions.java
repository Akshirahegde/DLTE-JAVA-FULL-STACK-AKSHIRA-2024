package org.example;

import application.db.Services.UserInfoServices;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyCardFunctions extends Remote {
    List<UserInfoServices> fetchOverLimit()throws RemoteException;
}
