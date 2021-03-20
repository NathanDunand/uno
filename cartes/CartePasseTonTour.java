package uno.cartes;

import uno.Uno;

public class CartePasseTonTour extends Carte{
    public CartePasseTonTour(Uno uno){
        super(uno);
    }

    public CartePasseTonTour(Uno uno, Couleur couleur){
        super(uno,couleur);
    }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    @Override
    public boolean peutEtrePoseeSur(CartePasseTonTour c) {//toujours vrai
        return true;
    }

    @Override
    public int getValeur() {
        return 20;
    }

    @Override
    public int effet(){
        return 3;
    }

    public int getNumero(){ return 0; }

    @Override
    public String getNom() {
        return "PasseTonTour";
    }
}
