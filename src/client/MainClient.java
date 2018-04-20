package client;

import common.ServerTopic;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainClient {

    public static final String ip = "localhost";
    public static final int port = 1099;


    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            ClientSessionImpl clientSession = new ClientSessionImpl();

            ServerTopic topic = (ServerTopic) Naming.lookup("rmi://" + ip + ":" + port + "/login");

            if(topic != null){
                topic.login(clientSession);
            }

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
