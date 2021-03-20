package uno.cartes;

import uno.Uno;

public class CarteChiffre extends Carte{
    private int valeur;

    public CarteChiffre(Uno uno){
        super(uno);
    }

    public CarteChiffre(Uno uno, Couleur couleur){
        super(uno,couleur);
    }

    public CarteChiffre(Uno uno, Couleur couleur, int valeur){
        super(uno,couleur);
        this.valeur=valeur;
    }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public int effet(){
        return 0;
    }

    public int getValeur(){
        return this.valeur;
    }

    public int getNumero(){ return 1; }

    @Override
    public String getNom() {
        return "Chiffre";
    }
}
