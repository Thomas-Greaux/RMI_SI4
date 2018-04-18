package other;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Facture implements Serializable{

	private static final long serialVersionUID = 1L;
	double v, montantfact;
	String destinataire; // Est utile tout de meme pour la gestion faite cot� serveur

	public Facture(double v, String destinataire){
		this.v=v;
		this.montantfact=v*1.5; // on imagine que le prix � facturer est de 1.5 par unit� livr�e au client!
		this.destinataire=destinataire;
	}
	public String toString(){
		return "Facture pour "+ destinataire +" pour quantite " + v + " Montant TTC=" + montantfact;
	}
	// Pas la peine de serialiser le destinataire car le client � qui on envoie la facture
	// sait pertinemment quel est son nom! Donc, on se fait une methode de serialisation qui
	// permet d'eviter de passer cette string l� pour rien
	private void writeObject(ObjectOutputStream os) throws IOException {
		os.writeDouble(v);
		os.writeDouble(montantfact);

	}
	// symetriquement, on doit aussi fournir une methode de deserialisation qui zappera le nom!
	private void readObject(ObjectInputStream os) throws IOException {
		v=os.readDouble();
		montantfact=os.readDouble();
	}

}
