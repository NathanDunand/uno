package uno.cartes;

import uno.Uno;

public class CartePlus2 extends Carte{
    public CartePlus2(Uno uno){
        super(uno);
    }

    public CartePlus2(Uno uno, Couleur couleur){
        super(uno,couleur);
    }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    @Override
    public boolean peutEtrePoseeSur(CartePlus2 c) {
        return true;
    }

    @Override
    public int getValeur() {
        return 20;
    }

    @Override
    public int effet(){
        return 2;
    }

    public int getNumero(){ return 0; }

    @Override
    public String getNom() {
        return "PlusDeux";
    }
}
