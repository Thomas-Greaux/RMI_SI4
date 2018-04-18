package server;


import client.ClientSession;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerTopic extends Remote{

    public boolean login(ClientSession a) throws RemoteException;

    public void publish(String toSend) throws RemoteException;

}
