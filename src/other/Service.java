package other;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Service extends Remote {
	int getVal() throws RemoteException;
	int setVal(int v, Facturation cname) throws RemoteException;
	// le service offert consiste � multiplier val par un facteur v, et de facturer en
	// cons�quence au client, en lui renvoyant une facture via l interface Facturation
	// que ce client expose pour recevoir cette facture
}
