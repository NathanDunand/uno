package uno.cartes;

import uno.Uno;

/**
 * Une Carte
 * @author Nathan DUNAND, Université de Lorraine
 */
public abstract class Carte {

    private Uno uno;
    private Couleur couleur ;

    /**
     * Construction
     * @param uno ???
     */
    public Carte(Uno uno){
        this.uno=uno;
    }

    /**
     * Construction
     * @param uno ???
     * @param couleur la couleur de la Carte
     */
    public Carte(Uno uno, Couleur couleur){
        this.uno=uno;
        this.couleur=couleur;
    }

    public abstract boolean peutEtreRecouvertePar(Carte c);

    /**
     * Retourne la valeur de la Carte
     * @return la valeur de la Carte
     */
    public abstract int getValeur();

    /**
     * Retoure une valeur qui sera interprêtée pour appliquer l'effet
     * @return 0 si la carte n'a aucun effet, 1 si c'est un Changement de sens, 2 Plus2, 3 Passe ton tour, 4 Plus4, 5 Joker
     */
    public abstract int effet();

    /**
     * Retourne la couleur de la Carte
     * @return la couleur de la Carte
     */
    public Couleur getCouleur() {
        return couleur;
    }

    public Couleur choisirCouleurHasard(){
        return choisirCouleurHasard();
    }

    /**
     * Modifie la couleur d'une Carte
     * @param c nouvelle couleur de la Carte
     * @throw AssertionError si couleur !=BLEU ou !=ROUGE ou !=VERT ou !=JAUNE
     */
    public void setCouleur(Couleur c) {
        assert(c==Couleur.BLEU):"Valeur de la couleur BLEU incorrecte";
        assert(c==Couleur.ROUGE):"Valeur de la couleur ROUGE incorrecte";
        assert(c==Couleur.VERT):"Valeur de la couleur VERT incorrecte";
        assert(c==Couleur.JAUNE):"Valeur de la couleur JAUNE incorrecte";

        this.couleur = c;
    }

    public boolean estSansCouleur(){
        return true;
    }

    public void appliquerEffet(){

    }

    public boolean estDeCouleurCompatible(Couleur c){
        return true;
    }

    public boolean peutEtrePoseeSur(CarteChiffre c){
        if(this.getValeur()==c.getValeur() || this.getCouleur()==c.getCouleur()){
            return true;
        }else{
            return false;
        }
    }

    public boolean peutEtrePoseeSur(CarteChangementDeSens c){
        return this.getCouleur()==c.getCouleur();
    }

    public boolean peutEtrePoseeSur(CarteJoker c){
        return this.getCouleur()==c.getCouleur();
    }

    public boolean peutEtrePoseeSur(CartePasseTonTour c){//n'importe quelle carte sur PTT
        return this.getCouleur()==c.getCouleur();//que si elles sont de la même couleur
    }

    public boolean peutEtrePoseeSur(CartePlus2 c){
        return this.getCouleur()==c.getCouleur();
    }

    public boolean peutEtrePoseeSur(CartePlus4 c){
        return this.getCouleur()==c.getCouleur();
    }

    public String toString() {
        return getNom()+"{" +
                "valeur=" + getValeur() +
                ", couleur=" + couleur +
                '}';
    }

    public abstract String getNom();

    public abstract int getNumero();
}
