package client;

import common.DistanteInterface;
import other.ConnexionServeur;
import server.ServerTopic;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainClient {

    public static String ip = "localhost";
    public static int port = 1099;
    public static int attenmp = 0;
    public static boolean testing = true;
    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            ClientSessionImpl clientSession = new ClientSessionImpl("test");

            ServerTopic topic = (ServerTopic) Naming.lookup("rmi://" + ip + ":" + port + "/login");

            topic.login(clientSession);
            topic.publish("xdddddd");
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private static void test(){
        try {
            connect();
            testing = false;
            System.out.println("It's the end of the test (as we know it)");
        } catch (Exception e) {
            test();
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private static void connect() throws RemoteException, NotBoundException{
        System.out.println("Attempt " + ++attenmp);

        Registry r = LocateRegistry.getRegistry(ip, port);
        //((DistanteInterface) r.lookup("echo")).echo();
        System.out.println(((DistanteInterface)r.lookup("sendInt")).sendInt(1).toString());
    }
}
