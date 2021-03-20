package uno.cartes;

import uno.Uno;

public class CarteChangementDeSens extends Carte {

    public CarteChangementDeSens(Uno uno) {
        super(uno);
    }

    public CarteChangementDeSens(Uno uno,Couleur couleur) {
        super(uno,couleur);
    }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    @Override
    public boolean peutEtrePoseeSur(CarteChangementDeSens c) {
        return true;
    }

    @Override
    public int getValeur() {
        return 20;
    }

    @Override
    public int effet(){
        return 1;
    }

    public int getNumero(){ return 0; }

    @Override
    public String getNom() {
        return "ChangementDeSens";
    }
}
