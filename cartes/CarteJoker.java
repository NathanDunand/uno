package uno.cartes;

import uno.Uno;

public class CarteJoker extends Carte{
    public CarteJoker(Uno uno){
        super(uno);
    }

    public CarteJoker(Uno uno, Couleur couleur){
        super(uno,couleur);
    }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    @Override
    public boolean peutEtrePoseeSur(CarteChiffre c) {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(CarteChangementDeSens c) {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(CarteJoker c) {//joker sur un joker
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(CartePasseTonTour c) {//joker sur un joker
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(CartePlus2 c) {//joker sur un joker
        return true;
    }

    @Override
    public int getValeur() {
        return 50;
    }

    @Override
    public int effet(){
        return 5;
    }

    public int getNumero(){ return 0; }

    @Override
    public String getNom() {
        return "Joker";
    }
}
