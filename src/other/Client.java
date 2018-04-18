package other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Client extends UnicastRemoteObject implements Facturation{
	String name;
	double monCompte = 10000;
	protected Client() throws RemoteException {
		super();
	}
	public Client(String name) throws RemoteException{
		super();
		this.name=name;
	}

	public static void main(String[] args){
		Client c=null;
		try {
			c = new Client(args[0]);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		System.out.println("Recherche d'un objet serveur");

		ConnexionServeur cs = null;
		try {
			cs = (ConnexionServeur)Naming.lookup("rmi://localhost" + ":2004/Serveur");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		Service s=null;
		try {
			s=cs.logon();
			System.out.println("On a recu une ref vers service distant "+s );
			c.travailler(s); // utiliser le service 's' afin de r�aliser son propre travail
			System.out.println("Client a fini son boulot"); // mais apr�s ce print, on est bloqu� car client est unicastRemoteObject!
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	  void travailler(Service s){ // precaution � prendre: ne pas mettre cette m�thode synchronized
		try{
			for (int i=1;i<4;i++){
				int quantite=2+i;
				int res=s.setVal(quantite,this); // this renvoie un stub du client, puisque Remote
				Thread.sleep(1000);
				System.out.println("Thread "+Thread.currentThread().getName() +" cote Client: Valeur rendue par le service "+res);
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Cette m�thode sera invoquee par l objet serveur pour savoir � quel nom �tablir la facture
	// Dans un deuxieme exercice, on enverra un smart proxy de Client afin que le serveur ait d�j�
	// l information rendue par getName sans avoir besoin de faire un appel distant
	@Override
	public String getName() throws RemoteException {
		System.out.println("Thread="+Thread.currentThread().getName()+" is calling getName()");
		return name;
	}
	// Cette m�thode sera invoquee par l objet serveur pour pr�senter la facture au client
	@Override
	// facturer est synchronized car si le client a appel� plusieurs services, ceux ci
	// doivent pouvoir se faire payer sans etre en concurrence (modif de la valeur monCompte)
	// Cela ne provoque pas interblocage malgr� que  setVal soit aussi synchronized!
	// Un interblocage se produirait si travailler etait aussi synchronized, 
	// car cela empecherait le serveur d'invoquer facturer tant que le client serait
	// dans le moniteur car encore dans sa m�thode travailler
	public synchronized void  facturer(Facture f) throws RemoteException {
		monCompte-=f.montantfact;
		System.out.println("On vient de payer facture de " + f.montantfact + " pour un service de "+ f.v + ", adressee � "+getName());
		}

}