package uno.cartes;

import java.util.Random;

/**
 * Un couleur de carte
 * @author Nathan DUNAND, Université de Lorraine
 */
public enum Couleur{

	BLEU("Bleu"),
	ROUGE("Rouge"),
	VERT("Vert"),
	JAUNE("Jaune");

	private String francais;

	/**
     * Construction
     * @param francais la couleur en français
     */
	private Couleur(String francais){
		/*assert(francais=="Bleu"):"Valeur de la couleur BLEU incorrecte";
        assert(francais=="Rouge"):"Valeur de la couleur ROUGE incorrecte";
        assert(francais=="Vert"):"Valeur de la couleur VERT incorrecte";
        assert(francais=="Jaune"):"Valeur de la couleur JAUNE incorrecte";*/

		this.francais=francais;
	}

	/**
     * Retourne la couleur en français
     * @return la couleur en français
     */
	public String getNom(){
		return this.francais;
	}

	public String toString(){
		StringBuilder s=new StringBuilder();

		s.append(this.getNom());
		return s.toString();
	}

	public Couleur choisirCouleurHasard(){
		/*Random random=new Random();
		Couleur tabCoul[]=new Couleur[]{Couleur.ROUGE,Couleur.VERT,Couleur.BLEU,Couleur.JAUNE};
		System.out.println("couleur aléatoire : "+random.nextInt(4));
		Couleur couleur=tabCoul[3];*/
		System.out.println("BOT A JOUE UN JOKER classe Couleur");
		System.out.println("couleur choisie "+Couleur.ROUGE.toString());
		return Couleur.ROUGE;
	}
}