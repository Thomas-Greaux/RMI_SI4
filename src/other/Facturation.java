package other;

import java.rmi.*;
public interface Facturation extends Remote {
	public void facturer(Facture f) throws RemoteException;
	public String getName() throws RemoteException;

}
