package uno.joueurs.strategie;

import uno.Uno;
import uno.pdc.*;
import uno.cartes.*;
import uno.joueurs.*;

public abstract class Strategie {

    protected Couleur couleur;

    protected int difficulte;
    protected Joueur joueur;
    protected Uno u;
    protected PaquetDeCartes jeuPerso;
    protected Carte carteTalon;

    public Strategie(Joueur joueur, int difficult, Uno u){
        this.difficulte=difficulte;
        this.joueur=joueur;
        this.u=u;
        this.jeuPerso=this.joueur.getJeuPerso();
        this.carteTalon=u.getPremiereCarteTalon();
    }

    public abstract int getIndiceCarteAJouer();

    public abstract Couleur choisirCouleur();
}
