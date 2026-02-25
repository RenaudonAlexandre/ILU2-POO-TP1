package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int nbetal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		Marche marche = new Marche(nbetal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	// Création de la class interne static //
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nbetalsMax) {
			etals = new Etal[nbetalsMax];
			for (int i=0; i<etals.length; i++) {
				etals[i]=new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalsLibre() {
			for (int i=0;i<etals.length;i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtalProduit =0;
			for (int i=0; i<etals.length;i++) {
				if(etals[i].contientProduit(produit)==true)
					nbEtalProduit ++;
			}
			Etal[] etalsContenantProduit = new Etal[nbEtalProduit];
			int indiceEtal =0;
			for (int i = 0; i < etals.length;i++) {
				if(etals[i].contientProduit(produit)) {
					etalsContenantProduit[indiceEtal]= etals[i];
					indiceEtal++;
				}
			}
			return etalsContenantProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0; i<etals.length;i++) {
				if(etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarcher() {
			int nbetalvide = 0;
			String text = " ";
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					text = etals[i].afficherEtal() + "\n";
				} else {
					nbetalvide ++;
				}
			}
			text = "Il reste " + 2 + "étals non utilisés dans le marché.";
			return text;
		}
		

	}
	
	// fin de création de la class //
	
	/*public String installerVendeur(Gaulois vendeur, String produit, int nbProduit ) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + "cherche un endroit pour vendre " + nbProduit + produit + "." );
		etals*/
	//}
}