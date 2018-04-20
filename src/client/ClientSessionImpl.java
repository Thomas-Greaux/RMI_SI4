package client;

import common.ClientSession;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ClientSessionImpl extends UnicastRemoteObject implements ClientSession {

    ClientSessionImpl() throws RemoteException {
    }

    @Override
    public void tell(String name) throws RemoteException {
        System.out.print(name);
    }
}
