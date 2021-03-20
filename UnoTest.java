package uno;

import org.junit.jupiter.api.Test;
import uno.joueurs.Joueur;
import uno.joueurs.JoueurHumain;
import uno.pdc.PaquetDeCartes;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UnoTest {

    @Test
    void initialiser() {
        Uno uno=new Uno();
        uno.initialiser(5,1);//on initialise 5 joueurs

        assert(uno.getNbJoueurs()==5):"Erreur : creerLesJoueurs nombre de joueur incorrect";
        assert(uno.getJoueur(0).getNom().equals("Joueur1")):"Erreur : Joueur1 n'a pas le bon nom";
        assert(uno.getJoueur(1).getNom().equals("Bot1")):"Erreur : Bot1 n'a pas le bon nom";
        assert(uno.getJoueur(2).getNom().equals("Bot2")):"Erreur : Bot2 n'a pas le bon nom";
        assert(uno.getJoueur(3).getNom().equals("Bot3")):"Erreur : Bot3 n'a pas le bon nom";
        assert(uno.getJoueur(4).getNom().equals("Bot4")):"Erreur : Bot4 n'a pas le bon nom";

        ArrayList<Joueur> joueurs= uno.getJoueurs();
        for(Joueur j:joueurs){
            PaquetDeCartes pdc=j.getJeuPerso();
            pdc.getNombreDeCartes();
            assert(j.getJeuPerso().getNombreDeCartes()==7):"Erreur : nombre de carte";
        }

        assert(uno.choisirQuiJoue(0)==1):"Erreur : choisirQuiJoue";
        assert(uno.choisirQuiJoue(4)==0):"Erreur : choisirQuiJoue";
        assert(uno.choisirQuiJoue(2)==3):"Erreur : choisirQuiJoue";

        uno.setSensHoraire(false);//sens anti-horaire

        assert(uno.choisirQuiJoue(0)==4):"Erreur : choisirQuiJoue";
        assert(uno.choisirQuiJoue(4)==3):"Erreur : choisirQuiJoue";
        assert(uno.choisirQuiJoue(1)==0):"Erreur : choisirQuiJoue";
    }

    @Test
    void getNbJoueurs() {
    }

    @Test
    void creerLesJoueurs() {
        Uno uno=new Uno();
        uno.creerLesJoueurs(5);//on créer 5 joueurs

        assert(uno.getNbJoueurs()==5):"Erreur : creerLesJoueurs nombre de joueur incorrect";
        assert(uno.getJoueur(0).getNom().equals("Joueur1")):"Erreur : Joueur1 n'a pas le bon nom";
        assert(uno.getJoueur(1).getNom().equals("Bot1")):"Erreur : Bot1 n'a pas le bon nom";
        assert(uno.getJoueur(2).getNom().equals("Bot2")):"Erreur : Bot2 n'a pas le bon nom";
        assert(uno.getJoueur(3).getNom().equals("Bot3")):"Erreur : Bot3 n'a pas le bon nom";
        assert(uno.getJoueur(4).getNom().equals("Bot4")):"Erreur : Bot4 n'a pas le bon nom";
    }

    @Test
    void distribuerCartes() {
        Uno uno=new Uno();
        uno.initialiser(5,1);

        ArrayList<Joueur> joueurs= uno.getJoueurs();
        for(Joueur j:joueurs){
            PaquetDeCartes pdc=j.getJeuPerso();
            pdc.getNombreDeCartes();
            assert(j.getJeuPerso().getNombreDeCartes()==7):"Erreur : nombre de carte";
        }
    }

    @Test
    void choisirQuiDistribue() {
    }

    @Test
    void choisirQuiJoue() {
        Uno uno=new Uno();
        uno.creerLesJoueurs(5);//on créer les joueurs, pas d'appel à initialiser() car dedans on utilise choisirQuiJoue()
        uno.setSensHoraire(true);

        assert(uno.choisirQuiJoue(0)==1):"Erreur : choisirQuiJoue";
        assert(uno.choisirQuiJoue(4)==0):"Erreur : choisirQuiJoue";
        assert(uno.choisirQuiJoue(2)==3):"Erreur : choisirQuiJoue";

        uno.setSensHoraire(false);//sens anti-horaire

        assert(uno.choisirQuiJoue(0)==4):"Erreur : choisirQuiJoue";
        assert(uno.choisirQuiJoue(4)==3):"Erreur : choisirQuiJoue";
        assert(uno.choisirQuiJoue(1)==0):"Erreur : choisirQuiJoue";
    }
}