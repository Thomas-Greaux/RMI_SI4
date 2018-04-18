package client;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientSession extends Remote{

    public void tell (String name)throws RemoteException ;
    public String getName()throws RemoteException;

}
