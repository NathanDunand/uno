package uno;

import uno.joueurs.Joueur;
import uno.exceptions.ExceptionJoueur;
import uno.cartes.*;

import java.util.Scanner;
import java.util.regex.Pattern;

public class DialogueLigneDeCommande {

    private Uno u;
    private int difficulte;

    public DialogueLigneDeCommande(Uno u){
        this.u=u;
        //TODO chaque Bot a SA difficulte propre à faire en dernier
        this.difficulte=difficulte;
        Scanner scan=new Scanner(System.in);

        //message de bienvenue, avec la syntaxe d'un coup
        System.out.println("---     UNO     ---\n\nComment jouer ?\n\nPiocher une carte : p\nJouer la x-ième carte de votre main : [un chiffre]\nJouer un joker et choisir la couleur : [un chiffre]Première lettre de la couleur voulez\nExemple, 3b pour jouer la troisième carte de ma main qui est un joker, je souhaite la couleur bleue ensuite.\n");

        int nbJoueurs=0;
        boolean flag=false;
        while (!flag){
            System.out.println("\n\nVeuillez choisir le nombre de bots : ");
            String nbJoueursString=scan.nextLine();
            if(Pattern.matches("([0-9]+)", nbJoueursString)) {
                nbJoueurs=Integer.parseInt(nbJoueursString);
                flag=true;
            } else {
                System.out.println("Erreur : veuillez saisir un nombre de Bots correct.");
                flag=false;
            }
        }

        boolean flag2=false;
        while (!flag2){
            System.out.println("\n\nVeuillez choisir la difficulté des bots\nFacile : 1\nMoyen : 2\nDifficile : 3\n");
            String difficulteString=scan.nextLine();
            if(Pattern.matches("([1-3]+)", difficulteString)) {
                this.difficulte=Integer.parseInt(difficulteString);
                flag2=true;
            } else {
                System.out.println("Erreur : veuillez saisir un niveau de difficulté correct.");
            }
        }

        //appel de initialiser
        u.initialiser(nbJoueurs++,this.difficulte);//on passe nbJoueurs++ car il faut compter l'humain en plus des bots
    }

    /**
     * Analyse si le coup est correct ou non
     * @param //coup le coup du joueur
     * @return validité du coup
     */
    public boolean isCoupCorrect(String coup){
        //cas d'une carte quelconque
        boolean retour=true;
        if(Pattern.matches("([0-9]+[r,v,b,j]?)", coup)){//regex qui fonctionne
            String entier = coup.replaceAll("[^0-9]", "");//coup="14machin" entier contient 14
            int indiceCarte=Integer.parseInt(entier);
            int nombreCarteJoueurQuiJoue=this.u.getJoueur(this.u.getIndiceJoueurQuiJoue()).getJeuPerso().getNombreDeCartes();

            if(nombreCarteJoueurQuiJoue>=indiceCarte && indiceCarte>0){//coup correct
                retour=true;
            } else {
                retour=false;
            }
        }

        return retour;
    }

    public void mettreAJour() throws ExceptionJoueur{
        Scanner scan=new Scanner(System.in);
        //est-ce que partie terminée ?
        if(u.estTermineePartie()){
            //afficher score

        }else{//partie pas terminée
            if(u.getIndiceJoueurQuiJoue()==0){//c'est l'humain qui joue
                this.afficherEtatDuJeu();

                this.afficherMain();
                System.out.println("Que faire ?");
                String coup=scan.nextLine();

                try{
                    int isCoupCorrect=0;/*=u.jouer(coup)*/;

                    if(Pattern.matches("([0-9,p]+[r,v,b,j]?)", coup)){//p pour piocher
                        //ici on a un coup qui est valide d'un point de vue syntaxique
                        //reste à savoir si la syntaxe correspond au bon type de carte

                        if(coup.equals("p")){//pioche
                            u.jouer(coup);
                            System.out.println("Vous avez pioché.");
                        } else {
                            String entier = coup.replaceAll("[^0-9]", "");//coup="14machin" entier contient 14
                            if(entier.isEmpty()){//erreur le jouer n'a pas bien joué
                                System.out.println("Erreur si vous voulez piocher, entrez p.");
                            } else {
                                int indiceCarte = Integer.parseInt(entier);


                                //carte demandé à être posée par le joueur
                                Carte veutEtrePosee = this.u.getJeuPersoJoueur(this.u.getIndiceJoueurQuiJoue()).getCarte(indiceCarte - 1);
                                //System.out.println("ON VEUT JOUER "+veutEtrePosee);
                                if (veutEtrePosee.getValeur() == 50) {//joker ou +4
                                    if (Pattern.matches("([0-9]+[r,v,b,j])", coup)) {
                                        //on peut jouer parce que c'est bien un joker, avec la bonne syntaxe
                                        isCoupCorrect = u.jouer(coup);//à renomer
                                        if (isCoupCorrect == -1) {
                                            //malgré le fait que la syntaxe soit bonne, le joueur n'a pas pu jouer car la carte n'est pas posable
                                            System.out.println("Carte invalide !");
                                        }
                                    } else {//on veut jouer joker mais pas avec bonne syntaxe
                                        System.out.println("Erreur pour jouer un joker, précisez le bon numéro de carte ainsi qu'une couleur valide.");
                                    }
                                } else {// ici on veut peut être jouer une simple carte
                                    if (Pattern.matches("([0-9]+)", coup)) {
                                        //on peut jouer parce que c'est bien une carte, avec la bonne syntaxe
                                        isCoupCorrect = u.jouer(coup);//à renomer
                                        if (isCoupCorrect == -1) {
                                            //malgré le fait que la syntaxe soit bonne, le joueur n'a pas pu jouer car la carte n'est pas posable
                                            System.out.println("Carte invalide !");
                                        }
                                    } else {//on veut jouer une carte mais avec la syntaxe d'un joker
                                        System.out.println("Erreur pour jouer une carte classique, précisez seulement le numéro de carte.");
                                    }
                                }
                            }
                        }


                    } else {
                        System.out.println("Erreur : syntaxe de coup incorrecte.");
                    }

                    while(isCoupCorrect==-1){//-1 indique que le coup n'est pas valide
                        //System.out.println("Carte posée non valide !");
                        this.afficherEtatDuJeu();

                        this.afficherMain();
                        System.out.println("Que faire ?");
                        coup=scan.nextLine();
                        //on vérifie les entrées
                        if(Pattern.matches("([0-9]+[r,v,b,j]?)", coup)){
                            //ici on a un coup qui est valide d'un point de vue syntaxique
                            //reste à savoir si la syntaxe correspond au bon type de carte

                            String entier = coup.replaceAll("[^0-9]", "");//coup="14machin" entier contient 14
                            int indiceCarte=Integer.parseInt(entier);

                            //carte demandé à être posée par le joueur
                            Carte veutEtrePosee=this.u.getJeuPersoJoueur(this.u.getIndiceJoueurQuiJoue()).getCarte(indiceCarte-1);

                            if(veutEtrePosee.getValeur()==50){//joker ou +4
                                if(Pattern.matches("([0-9]+[r,v,b,j])", coup)){
                                    //on peut jouer parce que c'est bien un joker, avec la bonne syntaxe
                                    isCoupCorrect=u.jouer(coup);//à renomer
                                    if(isCoupCorrect==-1){
                                        //malgré le fait que la syntaxe soit bonne, le joueur n'a pas pu jouer car la carte n'est pas posable
                                        System.out.println("Carte invalide !");
                                    }
                                } else {//on veut jouer joker mais pas avec bonne syntaxe
                                    System.out.println("Erreur pour jouer un joker, précisez le bon numéro de carte ainsi qu'une couleur valide.");
                                }
                            } else {// ici on veut peut être jouer une simple carte
                                if(Pattern.matches("([0-9]+)", coup)){
                                    //on peut jouer parce que c'est bien une carte, avec la bonne syntaxe
                                    isCoupCorrect=u.jouer(coup);//à renomer
                                    if(isCoupCorrect==-1){
                                        //malgré le fait que la syntaxe soit bonne, le joueur n'a pas pu jouer car la carte n'est pas posable
                                        System.out.println("Carte invalide !");
                                    }
                                } else {//on veut jouer une carte mais avec la syntaxe d'un joker
                                    System.out.println("Erreur pour jouer une carte classique, précisez seulement le numéro de carte.");
                                }
                            }
                        } else {
                            System.out.println("Erreur : syntaxe de coup incorrecte.");
                        }
                    }
                } catch (ExceptionJoueur e){
                    throw new ExceptionJoueur(e.getMessage());
                }

            } else {//BotX joue
                Joueur joueurQuiJoue=this.u.getJoueur(this.u.getIndiceJoueurQuiJoue());
                System.out.println();
                try {
                    System.out.println(joueurQuiJoue.getNom()+" réfléchit ...");
                    Thread.sleep(4000);//temporisation 4 secondes
                    try{
                        int coupBot=u.jouer("");

                        if(coupBot==0){
                            System.out.println(joueurQuiJoue.getNom()+" a joué "+this.u.getPremiereCarteTalon());
                        } else {//==1 le bot a pioché
                            System.out.println(joueurQuiJoue.getNom()+" a pioché.");
                        }
                    } catch (ExceptionJoueur e){
                        System.out.println(e.getMessage());
                    }
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void afficherEtatDuJeu(){
        //afficher état du jeu (mode debug durant le dev.), joueur suivant
        System.out.println("Carte sur le talon : "+u.getPremiereCarteTalon().toString()+"\n");
        for(Joueur j:this.u.getJoueurs()){
            int nmbrCarte=j.getJeuPerso().getNombreDeCartes();
            System.out.println(j.getNom()+" a encore "+nmbrCarte+" carte(s)");
        }
    }

    public void afficherMain(){
        StringBuilder s=u.afficherMain();
        System.out.println(s);
    }

    public static void main(String args[]){
        //voilà comment mettre en place la liaison One to One entre Uno--DLDC
        Uno u=new Uno();
        DialogueLigneDeCommande dldc=new DialogueLigneDeCommande(u);
        u.setDialogue(dldc);

        Boolean estTermineePartie=false;
        while(!estTermineePartie){
            try{
                dldc.mettreAJour();
            } catch(ExceptionJoueur e){
                System.out.println(e.getMessage());
            }
            //System.out.println("aa");
            estTermineePartie=u.estTermineePartie();
        }
        if(estTermineePartie){
            //déterminer qui est le gagnant
            Joueur jGagnant=u.getJoueur(u.getIndiceJoueurGagnant());
            System.out.println("Partie terminée ! \nLe gagnant est : "+jGagnant.getNom().toString());
            //comptage des points
            int i=0;
            for(Joueur j:u.getJoueurs()){
                try{
                    System.out.println(j.getNom()+" a "+u.getNombreDePointJoueur(i)+" point(s).");
                }catch (ExceptionJoueur e){
                    System.out.println(e.getMessage());
                }
                i++;
            }
        }
    }
}
