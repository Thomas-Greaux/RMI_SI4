package server;

import client.MainClient;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            System.setProperty("java.rmi.server.hostname",MainClient.ip);
            ServerTopicImpl obj = new ServerTopicImpl(MainClient.port);
            Registry r = LocateRegistry.createRegistry(MainClient.port);

            r.bind("login", obj);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
