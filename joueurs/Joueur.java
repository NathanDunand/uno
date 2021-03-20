package uno.joueurs;

import uno.Uno;
import uno.cartes.Carte;
import uno.exceptions.ExceptionJoueur;
import uno.pdc.PaquetDeCartes;

public abstract class Joueur {
    protected String nom;
    protected int numero;
    protected PaquetDeCartes jeuPerso;
    protected Uno u;

    public Joueur(String nom,int numero, Uno u){
        this.nom=nom;
        this.numero=numero;
        this.jeuPerso=new PaquetDeCartes();
        this.u=u;
    }

    public abstract int jouer(String coup) throws ExceptionJoueur;

    public String getNom(){
        return this.nom;
    }

    public int getNumero() {
        return this.numero;
    }

    public PaquetDeCartes getJeuPerso(){
        return this.jeuPerso;
    }

    /**
     * Le joueur ajoute une carte a son paquet
     * @param c carte à ajouter au paquet
     */
    public void ajouterCarteJeuPerso(Carte c){
        this.jeuPerso.ajouter(c);
    }

    public void piocher(){
        this.ajouterCarteJeuPerso(this.u.getPioche().piocher());
    }

    public int poserCarte(int indiceJoueur, int indiceCarte) throws ExceptionJoueur {//retourne 0 si la carte a pu être posée, 1 si le joueur pioche -1 sinon
        return this.u.poserCarteSurTalon(indiceJoueur, indiceCarte);
    }

    public int poserJoker(int indiceJoueur, int indiceCarte, char couleur) throws ExceptionJoueur{// retourne 0 si la carte a pu être posée, 1 si le joueur pioche -1 sinon
        int retour=-1;
        try{
            retour=this.u.poserJokerSurTalon(indiceJoueur, indiceCarte, couleur);
        } catch (ExceptionJoueur e){
            throw new ExceptionJoueur(e.getMessage());
        }
        return retour;
    }

    /**
     * Le joueur retire une carte de son paquet
     * @param //int indiceCarte carte à retirer du paquet
     */
    public void defausserCarte(int indiceCarte){
        assert(!this.jeuPerso.estVide()):"Erreur : le paquet perso ne peut pas être vide";
        this.jeuPerso.retirer(indiceCarte);
    }
}
