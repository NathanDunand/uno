package uno.joueurs;

import uno.Uno;
import uno.cartes.Carte;
import uno.exceptions.ExceptionJoueur;
import uno.joueurs.strategie.Strategie;
import uno.joueurs.strategie.StrategieFacile;

public class Bot extends Joueur {

    private int difficulte;
    private Strategie strategie;

    public Bot(String nom,int numero, int difficulte, Uno u){
        super(nom,numero,u);
        this.difficulte=difficulte;
    }

    //paramètre string de fonction abstraite jouer ne sert à rien
    //la 1ère carte qui convient est jouée
    //si besoin la couleur est choisie au hasard


    @Override
    public int jouer(String coup) throws ExceptionJoueur{//retourne 0 si le bot a joué, 1 s'il a pioché
        this.strategie=new StrategieFacile(this, this.difficulte,this.u);
        int retour=0;
        int indiceCarteAJouer=this.strategie.getIndiceCarteAJouer();
        //en fonction de la difficulté du bot
        if(indiceCarteAJouer==-1){//le bot doit piocher
            this.piocher();
            retour=1;
        } else {//le bot peut jouer
            if(this.jeuPerso.getCarte(indiceCarteAJouer).getCouleur()==null) {//la carte est sans couleur c'est donc un +4 ou joker
                //choisir une couleur au hasard
                //System.out.println("BOT A JOUE UN JOKER classe Bot de couleur "+this.strategie.choisirCouleur());

                this.jeuPerso.getCarte(this.strategie.getIndiceCarteAJouer()).setCouleur(this.strategie.choisirCouleur());
            }
            this.poserCarte(this.getNumero(), indiceCarteAJouer);
            if (retour==-1){
                throw new ExceptionJoueur("");
            }
            retour=0;//tout se passe toujours bien ici
        }
        //pose une carte en fonction de la strat
        return retour;
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public int getNumero() {
        return super.getNumero();
    }

    @Override
    public void defausserCarte(int indiceCarte) {
        super.defausserCarte(indiceCarte);
    }
}
