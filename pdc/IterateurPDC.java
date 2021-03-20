package uno.pdc;

import uno.cartes.Carte;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterateurPDC implements Iterator<Carte>{

    private ArrayList<Carte> paquet;
    private int position=0;
    public IterateurPDC(ArrayList<Carte> paquet){
        this.paquet=paquet;
    }

    @Override
    public boolean hasNext() {
        return this.paquet.size()>this.position;
    }

    @Override
    public Carte next() {
        if(this.hasNext()){
            return this.paquet.get(this.position++);
        }else{
            throw new NoSuchElementException();
        }
    }
}
