package common;

import server.Resultat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by DALLA-NORA ENZO in 2017, for Solar Belle Planète.
 * This code is owned by DALLA-NORA ENZO. All rights reserved.
 */
public interface DistanteInterface extends Remote{

    void echo() throws RemoteException;

    Resultat sendInt(int param) throws RemoteException;

}
