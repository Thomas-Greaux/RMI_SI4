package client;

import common.DistanteInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainClient {

    public static String ip = "10.212.97.7";
    public static int port = 1099;
    public static int attenmp = 0;
    public static boolean testing = true;
    public static void main(String[] args) {
        System.out.println("Hello World!");

        while (testing){
            test();
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
