package uno.cartes;

import uno.Uno;

public class CartePlus4 extends Carte {
    public CartePlus4(Uno uno){
        super(uno);
    }

    public CartePlus4(Uno uno, Couleur couleur){
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
    public boolean peutEtrePoseeSur(CartePlus2 c) {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(CartePasseTonTour c) {
        return true;
    }

    @Override
    public boolean peutEtrePoseeSur(CarteJoker c) {
        return true;
    }

    @Override
    public int getValeur() {
        return 50;
    }

    @Override
    public int effet(){
        return 4;
    }

    public int getNumero(){ return 0; }

    @Override
    public String getNom() {
        return "PlusQuatre";
    }
}
