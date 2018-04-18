package other;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Serveur {
	public static void main(String[] args){
		System.out.println("Creation de l'objet  serveur");
/*		if (System.getSecurityManager() == null) { 
			System.setSecurityManager(new java.rmi.RMISecurityManager()); 
		}
*/		
		ConnexionServeur objserveur=null;
		try {
			objserveur = new ConnexionServeurImpl();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Referencement dans le registry...");
		try {
			Registry registry = LocateRegistry.getRegistry(2004);
			System.out.println("on a lacces au registry ecoutant sur 2004");
			registry.rebind("Serveur", objserveur);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("Attente d invocation, Ctrl-C pour stopper!");
	}
}
