package uno.pdc.tests;

import org.junit.jupiter.api.Test;
import uno.cartes.Carte;
import uno.pdc.FabriqueCartes;
import uno.pdc.PaquetDeCartes;

import java.util.Iterator;

class PaquetDeCartesTest {

    @Test
    void iterator() {
        PaquetDeCartes pdc1= FabriqueCartes.getPaquetCartes();
        PaquetDeCartes pdc2=new PaquetDeCartes();
        Iterator<Carte> it=pdc1.iterator();

        while(it.hasNext()){//remplissage de pdc2 en itérant avec pdc1
            pdc2.ajouter(it.next());
        }
        String s1=pdc1.toString();
        String s2=pdc2.toString();
        assert(s1.equals(s2)):"Erreur iterateur PDC";
    }

    @Test
    void getSommet() {
    }
}