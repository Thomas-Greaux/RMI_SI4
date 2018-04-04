package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by DALLA-NORA ENZO in 2017, for Solar Belle Planète.
 * This code is owned by DALLA-NORA ENZO. All rights reserved.
 */
public class DistanteObj extends UnicastRemoteObject implements DistanteInterface {

    public DistanteObj(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void echo() throws RemoteException{
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hey, you just called Echo !");
    }

    @Override
    public Resultat sendInt(int param) throws RemoteException{
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Resultat(param);
    }
}
