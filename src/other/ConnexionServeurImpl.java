package other;

import other.ServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ConnexionServeurImpl extends UnicastRemoteObject implements ConnexionServeur {
	private static final long serialVersionUID = 1L;

	protected ConnexionServeurImpl() throws RemoteException {
		super(10001); // pour montrer qu'on peut forcer, si on passe un param, l'installation du serveur � l'ecoute sur un port precis
	}

	ServiceImpl simpl=new ServiceImpl();
	@Override
	public Service logon() throws RemoteException {
		return simpl;
	}	
}
