package other;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServiceImpl extends UnicastRemoteObject implements Service{
	
	int val=1;
	public ServiceImpl() throws RemoteException {
		super();
	}
	
	@Override
	public int getVal() throws RemoteException {
		return val;
	}
	
	@Override
	public  synchronized int  setVal(int v, Facturation cname) throws RemoteException {
		// la m�thode est synchronized car interdit que plusieurs clients modifient val en meme temps
		val=val*v;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread:"+Thread.currentThread().getName()+" val  qui va �tre renvoy�e : " + val + " au Client "+cname);
		Facture f=new Facture(v,cname.getName());// getName invoqu�e via le reseau...ca peut etre penalisant ! On aimerait utiliser � la place un smartproxy (attendre prochain TP pour en parler)
		cname.facturer(f); // Le champ destinataire ne sera pas s�rialis� (voir classe Facture et ses m�thodes de de/serialisation) car cname est le bon client: pas la peine de lui redire quel est son nom !
		System.out.println("Nouvelle " + f);
		return val;
	}
}
