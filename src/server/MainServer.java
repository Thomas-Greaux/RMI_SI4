package server;

import client.MainClient;
import streaming.Streamer;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            System.setProperty("java.rmi.server.hostname",MainClient.ip);
            ServerTopicImpl obj = new ServerTopicImpl(MainClient.port);
            Registry r = LocateRegistry.createRegistry(MainClient.port);

            r.bind("login", obj);

            Streamer streamer = new Streamer("res/input_file.txt");

            byte[] buffer;
            byte[] empty = new byte[0];

            do {
                buffer = streamer.next_bytes();
                obj.publish(new String(buffer));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while(!Arrays.equals(buffer, empty));
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
