package uno.pdc;

import uno.Uno;
import uno.cartes.*;

public class FabriqueCartes{

	private static PaquetDeCartes instance = new PaquetDeCartes();

	public static PaquetDeCartes getInstance(){
		return instance;
	}

	public static PaquetDeCartes getPaquetVide(){
		PaquetDeCartes p = new PaquetDeCartes();
		return p;
	}

	public static PaquetDeCartes getPaquetUno(){
		PaquetDeCartes p = new PaquetDeCartes();

		return p;
	}

	public static PaquetDeCartes getPaquetCartes(){
		PaquetDeCartes p = new PaquetDeCartes();
		Uno uno=new Uno();

		p.ajouter(new CarteChiffre(uno, Couleur.BLEU,0));//une seule carte bleue 0
		for(int i=0;i<2;i++){//2 cartes de chaque couleur
			for (int j=1;j<=9;j++){//2*9 cartes de couleurs bleues -> on arrive bien aux 19 cartes
				p.ajouter(new CarteChiffre(uno, Couleur.BLEU,j));
			}
		}

		p.ajouter(new CarteChiffre(uno, Couleur.ROUGE,0));//une seule carte rouge 0
		for(int i=0;i<2;i++){//2 cartes de chaque couleur
			for (int j=1;j<=9;j++){//2*9 cartes de couleurs rouges
				p.ajouter(new CarteChiffre(uno, Couleur.ROUGE,j));
			}
		}

		p.ajouter(new CarteChiffre(uno, Couleur.JAUNE,0));//une seule carte jaune 0
		for(int i=0;i<2;i++){//2 cartes de chaque couleur
			for (int j=1;j<=9;j++){//2*9 cartes de couleurs jaunes
				p.ajouter(new CarteChiffre(uno, Couleur.JAUNE,j));
			}
		}

		p.ajouter(new CarteChiffre(uno, Couleur.VERT,0));//une seule carte verte 0
		for(int i=0;i<2;i++){//2 cartes de chaque couleur
			for (int j=1;j<=9;j++){//2*9 cartes de couleurs vertes
				p.ajouter(new CarteChiffre(uno, Couleur.VERT,j));
			}
		}

		for(int i=0;i<2;i++){//2 carte +2 Bleues
			p.ajouter(new CartePlus2(uno,Couleur.BLEU));
		}
		for(int i=0;i<2;i++){//2 carte +2 Rouges
			p.ajouter(new CartePlus2(uno,Couleur.ROUGE));
		}
		for(int i=0;i<2;i++){//2 carte +2 Jaunes
			p.ajouter(new CartePlus2(uno,Couleur.JAUNE));
		}
		for(int i=0;i<2;i++){//2 carte +2 Vert
			p.ajouter(new CartePlus2(uno,Couleur.VERT));
		}

		for(int i=0;i<2;i++){//2 carte ChangementDeSens Bleues
			p.ajouter(new CarteChangementDeSens(uno,Couleur.BLEU));
		}
		for(int i=0;i<2;i++){//2 carte ChangementDeSens Rouges
			p.ajouter(new CarteChangementDeSens(uno,Couleur.ROUGE));
		}
		for(int i=0;i<2;i++){//2 carte ChangementDeSens Jaunes
			p.ajouter(new CarteChangementDeSens(uno,Couleur.JAUNE));
		}
		for(int i=0;i<2;i++){//2 carte ChangementDeSens Vert
			p.ajouter(new CarteChangementDeSens(uno,Couleur.VERT));
		}

		for(int i=0;i<2;i++){//2 carte PasseTonTour Bleues
			p.ajouter(new CartePasseTonTour(uno,Couleur.BLEU));
		}
		for(int i=0;i<2;i++){//2 carte PasseTonTour Rouges
			p.ajouter(new CartePasseTonTour(uno,Couleur.ROUGE));
		}
		for(int i=0;i<2;i++){//2 carte PasseTonTour Jaunes
			p.ajouter(new CartePasseTonTour(uno,Couleur.JAUNE));
		}
		for(int i=0;i<2;i++){//2 carte PasseTonTour Vert
			p.ajouter(new CartePasseTonTour(uno,Couleur.VERT));
		}

		for(int i=0;i<4;i++){//4 carte Joker
			p.ajouter(new CarteJoker(uno));
		}

		for(int i=0;i<4;i++){//4 carte +4
			p.ajouter(new CartePlus4(uno));
		}

		return p;
	}
}