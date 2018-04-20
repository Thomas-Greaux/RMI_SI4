package common;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerTopic extends Remote{

    void login(ClientSession a) throws RemoteException;

    void publish(String toSend) throws RemoteException;

}
