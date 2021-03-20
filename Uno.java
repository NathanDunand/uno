package uno;

import uno.cartes.Carte;
import uno.cartes.Couleur;
import uno.exceptions.ExceptionJoueur;
import uno.pdc.FabriqueCartes;
import uno.pdc.PaquetDeCartes;
import uno.joueurs.Bot;
import uno.joueurs.Joueur;
import uno.joueurs.JoueurHumain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Uno {
    private boolean sensHoraire;
    private int joueurQuiJoue;

    private ArrayList<Joueur> joueurs;
    private PaquetDeCartes talon;
    private PaquetDeCartes pioche;

    private DialogueLigneDeCommande d;
    private int difficulteBot;

    public Uno(){
        this.joueurs=new ArrayList<Joueur>();
        this.sensHoraire=true;
    }

    public void setDialogue(DialogueLigneDeCommande d){
        this.d=d;
    }

    public void initialiser(int nbJoueurs, int difficulteBot){//initialise pas tout ici, il faut faire des fonctions pour cela
        assert(nbJoueurs>0):"Erreur : le nombre de Bot doit être strictement supérieur à 0";

        this.difficulteBot=difficulteBot;
        this.talon=new PaquetDeCartes();//talon vide au début de la partie
        this.pioche= FabriqueCartes.getPaquetCartes();//PDC complet
        this.pioche.melanger();
        //System.out.println(this.pioche.toString());

        creerLesJoueurs(nbJoueurs);
        distribuerCartes();
        int numeroJoueurQuiJoueEnPremier=choisirQuiJoue(choisirQuiDistribue());
    }

    public int getNbJoueurs(){
        return this.joueurs.size();
    }

    public Joueur getJoueur(int indice){
        return this.joueurs.get(indice);
    }

    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }

    public void creerLesJoueurs(int nbJoueurs){
        String nom="Joueur1";
        this.joueurs.add(0,new JoueurHumain(nom,0,this));//le seul joueur humain
        for(int i=1;i<=nbJoueurs;i++){
            nom="Bot"+i;
            this.joueurs.add(i,new Bot(nom,i,difficulteBot,this));//le reste des uno.joueurs sont des bots
        }
    }

    public void distribuerCartes(){
        assert(!this.pioche.estVide()):"Erreur : pioche vide";

        int numeroJoueurQuiDistribue=choisirQuiDistribue();

        for(int i=0;i<7;i++){//7 uno.cartes à distribuer
            for(int j=0;j<this.joueurs.size();j++){//chaque joueur
                Carte c=this.pioche.piocher();
                this.joueurs.get(j).ajouterCarteJeuPerso(c);//au joueur j on ajoute une carte à son paquetPerso
            }
        }
        //une fois la distribution terminée, la carte suivante est la première du talon
        this.talon.ajouter(this.pioche.piocher());
    }

    public int choisirQuiDistribue(){
        Random rand=new Random();
        int numeroJoueurQuiDistribue=rand.nextInt(this.joueurs.size());//numéro du joueur qui va distribuer
        while(numeroJoueurQuiDistribue==0){//ne peut pas =0
            numeroJoueurQuiDistribue=rand.nextInt(this.joueurs.size());
        }
        return numeroJoueurQuiDistribue;
    }

    public int choisirQuiJoue(int numeroJoueurQuiDistribue){
        //TODO a tester pour les tailles de l'AL et les sens horaires et anti horaire de jeu
        if(sensHoraire){
            return (numeroJoueurQuiDistribue+1)%this.joueurs.size();
        }else{
            return (numeroJoueurQuiDistribue-1+this.joueurs.size())%this.joueurs.size();
        }
    }

    public PaquetDeCartes getPioche(){
        return this.pioche;
    }

    public PaquetDeCartes getTalon(){
        return this.talon;
    }

    public Carte getPremiereCarteTalon(){
        Carte c=this.talon.getSommet();
        if(c.getValeur()==50){//joker
            c.setCouleur(Couleur.BLEU);
        }
        return c;
    }

    public void setSensHoraire(boolean b){
        this.sensHoraire=b;
    }

    public StringBuilder afficherMain(){
        PaquetDeCartes main=this.getJoueur(0).getJeuPerso();
        Iterator<Carte> it=main.iterator();
        StringBuilder s=new StringBuilder();
        s.append("\nVotre jeu "+this.getJoueur(0).getNom()+" :\n");
        int i=0;

        while(it.hasNext()){
            i++;
            s.append(i+" "+it.next()+"\n");
        }
        //s.append("\nPiocher une carte : p\nJouer la x-ième carte de votre main : [un chiffre]\nJouer un joker et choisir la couleur : [un chiffre]Première lettre de la couleur voulue");
        return s;
    }

    public int jouer(String coup) throws ExceptionJoueur{//retourne 0 si le joueur a joué, 1 s'il a pioché -1 sinon
        int retour=0;
        try{
            retour=this.joueurs.get(this.joueurQuiJoue).jouer(coup);
            if(retour!=-1){//le joueur a posé une carte
                this.joueurSuivant();
            }
        } catch (ExceptionJoueur e){
            throw new ExceptionJoueur(e.getMessage());
        }

        return retour;
    }

    public int getIndiceJoueurQuiJoue(){
        return this.joueurQuiJoue;
    }

    public void joueurSuivant(){//TODO sens horaire et anti horaire
        if(this.sensHoraire){
            this.joueurQuiJoue=(this.joueurQuiJoue+1)%this.getNbJoueurs();
        } else {
            this.joueurQuiJoue=(this.joueurQuiJoue-1+this.getNbJoueurs())%this.getNbJoueurs();
        }

        /*if(this.joueurQuiJoue<this.getNbJoueurs()-1){
            this.joueurQuiJoue++;
        }else{
            this.joueurQuiJoue=0;
        }*/
    }

    public int getIndiceJoueurSuivant(){
        int retour=0;

        if(this.sensHoraire){
            retour=(this.joueurQuiJoue+1)%this.getNbJoueurs();
        } else {
            retour=(this.joueurQuiJoue-1+this.getNbJoueurs())%this.getNbJoueurs();
        }

        return retour;

        /*if(this.joueurQuiJoue<this.getNbJoueurs()-1){
            retour=this.joueurQuiJoue;
            retour++;
        }else{
            retour=0;
        }
        return retour;*/
    }

    /*public Carte piocher(){
        return this.pioche.piocher();
    }*/

    public boolean estTermineePartie(){
        Boolean b=false;
        for(Joueur j:this.joueurs){
            if(j.getJeuPerso().getNombreDeCartes()==0){
                b=true;
            }
        }
        return b;
    }

    public int getNombreDePointJoueur(int indiceJoueur) throws ExceptionJoueur{
        int total=0;
        for(int i=0;i<this.getJoueur(indiceJoueur).getJeuPerso().getNombreDeCartes();i++){
            PaquetDeCartes pdc=this.getJoueur(indiceJoueur).getJeuPerso();
            try{
                Carte c=pdc.getCarte(i);
                total+=c.getValeur();
            }catch (ExceptionJoueur e){
                throw new ExceptionJoueur(e.getMessage());
            }
        }
        return total;
    }

    /**
     * Retourne l'indice du joueur qui a gagné
     * @return l'indice du joueur qui a gagné, -1 si aucun joueur n'a gagné
     */
    public int getIndiceJoueurGagnant(){
        int retour=-1;
        for(Joueur j:this.joueurs){
            if(j.getJeuPerso().getNombreDeCartes()==0){
                retour=j.getNumero();
            }
        }
        return retour;
    }

    public PaquetDeCartes getJeuPersoJoueur(int indiceJoueur){
        return this.getJoueur(indiceJoueur).getJeuPerso();
    }

    public int poserCarteSurTalon(int indiceJoueur, int indiceCarte) throws ExceptionJoueur{//retourne 0 si la carte a pu être posée, -1 sinon
        int retour=-1;
        try {
            Carte c = this.getJeuPersoJoueur(indiceJoueur).getCarte(indiceCarte);//carte que le joueur veut jouer

            if (this.getPremiereCarteTalon().peutEtreRecouvertePar(c)) {
                int effet = c.effet();
                if (effet == 1) {//changement sens
                    this.setSensHoraire(!this.sensHoraire);//on inverse le cours du jeu
                } else if (effet == 2) {// +2
                    //faire piocher deux fois au joueur suivant
                    Joueur joueurSuivant = this.getJoueur(getIndiceJoueurSuivant());
                    joueurSuivant.piocher();
                    joueurSuivant.piocher();
                } else if (effet == 3) {// passe ton tour
                    this.joueurSuivant();
                } else if (effet == 4) {// +4
                    //piocher 4 fois
                    Joueur joueurSuivant = this.getJoueur(getIndiceJoueurSuivant());
                    joueurSuivant.piocher();
                    joueurSuivant.piocher();
                    joueurSuivant.piocher();
                    joueurSuivant.piocher();
                } else if (effet == 5) {// Joker classique
                    //pas vraiment d'effet ici
                }
                this.talon.ajouter(c);
                this.getJeuPersoJoueur(indiceJoueur).retirer(indiceCarte);
                retour = 0;
                //System.out.println("POSEE "+retour);
            } else {
                retour = -1;
            }
        } catch (ExceptionJoueur e){
            throw new ExceptionJoueur(e.getMessage());
        }

        return retour;
    }

    public int poserJokerSurTalon(int indiceJoueur, int indiceCarte, char coul) throws ExceptionJoueur{
        int retour=0;
        try {
            Carte joker = this.getJeuPersoJoueur(indiceJoueur).getCarte(indiceCarte);
            Couleur couleur = null;

            if (coul == 'b') {
                couleur = Couleur.BLEU;
            }
            if (coul == 'v') {
                couleur = Couleur.VERT;
            }
            if (coul == 'r') {
                couleur = Couleur.ROUGE;
            }
            if (coul == 'j') {
                couleur = Couleur.JAUNE;
            } else {
                //throw exception
            }
            joker.setCouleur(couleur);
            //System.out.println("carte : "+joker);

            if (this.getPremiereCarteTalon().peutEtreRecouvertePar(joker)) {
                this.talon.ajouter(joker);
                this.getJeuPersoJoueur(indiceJoueur).retirer(indiceCarte);
                retour = 0;
                //System.out.println("POSEE "+retour);
            } else {
                retour = -1;
            }
        } catch (ExceptionJoueur e){
            throw new ExceptionJoueur(e.getMessage());
        }

        return retour;
    }
}
