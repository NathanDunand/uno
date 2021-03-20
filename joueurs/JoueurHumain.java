package uno.joueurs;

import uno.Uno;
import uno.exceptions.ExceptionJoueur;

import java.util.regex.Pattern;

public class JoueurHumain extends Joueur {

    public JoueurHumain(String nom,int numero, Uno u){
        super(nom,numero,u);
    }

    //fonction jouer, le paramètre c'est identification du coup
    //exemple "p" -> piocher
    //pour jouer une carte "2" -> joue la 2ème carte de sa main
    //si besoin de choisir une couleur, 3v pour jouer la 3ème carte et il choisit la couleur verte
    //autre touche pour passer son tour si le joueur n'a rien à jouer

    //on analyse le paramètre (on peut faire une sous-fonction dédiée pour ça)
    //réaliser le coup




    @Override
    public int jouer(String coup) throws ExceptionJoueur {//renvoie un entier qui correspond à l'indice de la carte à jouer, -1 si le coup n'est pas valide, 0 si le joueur pose une carte valide, 1 s'il pioche
        //récupération ici de l'entrée de l'humain en ligne de commande et on le fait jouer

        int indiceCarte=0;
        int coupValide=-1;
        /*Pattern pattern=Pattern.compile("(\\d+)(\\w*)");
        Matcher matcher=pattern.matcher(coup);
        //boolean isJoker=matcher.matches(); marche pas*/
        Character c=coup.charAt(0);
        boolean isCarte;
        //une carte classique, contenir un chiffre et qu'il soit valide
        if(Character.isDigit(c)){
            isCarte=true;
            //TODO vérifier que le chiffre est valide
        } else {
            isCarte=false;
        }

        //un joker, coup de longueur 2 au moins, contenir un chiffre valide et r,v,b,j
        boolean isJoker;
        if(Pattern.matches("([0-9]+[r,v,b,j])", coup)){
            //TODO vérifier que le chiffre est valide
            isJoker=true;
        } else {
            isJoker=false;
        }

        if(coup.equals("p")){//le joueur veut piocher
            this.piocher();
            coupValide=1;//le coup est une pioche, considéré comme valide
        } else if(isJoker){//jouer un joker
            //System.out.println("JOKER hum1");
            char couleur=coup.charAt(1);
            coupValide=this.poserJoker(this.numero, Character.getNumericValue(c)-1, couleur);
        } else if(isCarte){//jouer une simple carte
            //vérifier que le numéro rentré est valide
            if(this.jeuPerso.getNombreDeCartes()>Character.getNumericValue(c)-1 && Character.getNumericValue(c)-1>=0){
                indiceCarte=Character.getNumericValue(c)-1;
                coupValide=this.poserCarte(this.numero,Character.getNumericValue(c)-1);
            } else {//numero non valide
                throw new ExceptionJoueur("Erreur : numéro de carte à poser non valide");
            }
            //System.out.println("ici : "+this.jeuPerso.getCarte(Character.getNumericValue(c)-1));
        }
        return coupValide;
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
