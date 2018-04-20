package common;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientSession extends Remote {
    void tell(String name) throws RemoteException;
}
