package uno.joueurs.strategie;

import uno.Uno;
import uno.cartes.*;
import uno.pdc.*;
import uno.joueurs.Joueur;

import java.util.Iterator;

public class StrategieFacile extends Strategie{

    public StrategieFacile(Joueur joueur, int difficulte, Uno u){
        super(joueur,difficulte,u);
    }
    
    public int getIndiceCarteAJouer(){//retourne -1 s'il n'y a pas de carte à jouer dans le jeu du bot, indice prend l'indice de la dernière carte jouable dans le jeu du Bot
        int indice=0;
        Iterator<Carte> it=this.jeuPerso.iterator();
        int i=0;
        boolean b=false;
        while(it.hasNext()){
            Carte c=it.next();
            if(this.carteTalon.peutEtreRecouvertePar(c)){
                indice=i;
                b=true;
            }
            i++;
        }
        if(b){
            return indice;
        } else {
            return -1;
        }
    }

    public Couleur choisirCouleur(){
        //System.out.println("BOT A JOUE UN JOKER classe StratF");
        return Couleur.ROUGE;
    }
}
