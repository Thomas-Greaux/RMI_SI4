package other;

import java.rmi.*;
public interface ConnexionServeur extends Remote {
	public Service logon() throws RemoteException;
}
