package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ClientSessionImpl extends UnicastRemoteObject implements ClientSession {

    private String name;

    public ClientSessionImpl(String name) throws RemoteException{
        this.name = name;
    }

    @Override
    public void tell(String name) throws RemoteException {
        System.out.println(name);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
