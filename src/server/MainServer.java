package server;

import client.DistanteObj;
import client.MainClient;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            DistanteObj obj = new DistanteObj(MainClient.port);
            System.setProperty("java.rmi.server.hostname",MainClient.ip);
            Registry r = LocateRegistry.createRegistry(MainClient.port);
            r.bind("echo", obj);
            r.bind("sendInt", obj);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}