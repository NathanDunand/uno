package uno.pdc;

import uno.Uno;
import uno.cartes.*;
import uno.exceptions.*;
import java.util.Iterator;

import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Collections;
import java.io.*;
import java.lang.*;
import java.util.NoSuchElementException;

/**
 * Une Paquet de carte
 *
 * @author Nathan DUNAND, Université de Lorraine
 */

public class PaquetDeCartes{

    private ArrayList<Carte> paquet;//le champ

    /**
     * Construction
     */
    public PaquetDeCartes() {
        this.paquet = new ArrayList<Carte>();
        //ArrayList de Carte
    }

    public Iterator<Carte> iterator(){
        IterateurPDC i=new IterateurPDC(this.paquet);
        return i;
    }

    /**
     * Ajoute un Paquet au Paquet
     *
     * @param pdc paquet de carte à ajouter au paquet courant
     */
    public void ajouter(PaquetDeCartes pdc) {
        this.paquet.addAll(pdc.paquet);
    }

    /**
     * Ajoute une carte au Paquet
     *
     * @param //Carte... uno.cartes nouvelle(s) Carte(s)
     */
    public void ajouter(Carte... cartes) {
        for (Carte c : cartes) {
            this.paquet.add(c);
        }
    }

    /**
     * Retire une ou plusieurs uno.cartes au Paquet
     * @param //int indiceCarte indice de la carte à retirer
     */
    public void retirer(int indiceCarte) throws NoSuchElementException{
        if(!this.estVide()){
            this.paquet.remove(indiceCarte);
        }else{
            throw new NoSuchElementException("Erreur : on ne peut pas retirer une carte d'un paquet vide.");
        }
    }

    /**
     * Retourne le nombre de Carte du Paquet
     *
     * @return le nombre de Carte du Paquet
     */
    public int getNombreDeCartes() {
        return this.paquet.size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (Carte c : this.paquet) {
            s.append(c.toString() + "\n");
        }
        return s.toString();
    }

    /**
     * Retourne vrai si le Paquet est vide
     *
     * @return vrai si le Paquet est vide
     */
    public Boolean estVide() {
        Boolean b;

        if (this.paquet.isEmpty()) {
            b = true;
        } else {
            b = false;
        }

        return b;
    }

    /**
     * Retourne la valeur totale du Paquet
     *
     * @return la valeur totale du Paquet
     */
    public int getValeur() {
        int valeur = 0;

        for (Carte c : this.paquet) {
            valeur += c.getValeur();
        }

        return valeur;
    }

    /**
     * Mélange les uno.cartes du paquet
     */
    public void melanger() {
        Collections.shuffle(this.paquet);
    }

    /**
     * Retourne les uno.cartes du paquet
     */
    public void retourner() {
        Collections.reverse(this.paquet);
    }

    /**
     * Retourne la première carte du paquet
     *
     * @return la première carte du paquet
     */
    public Carte getSommet() throws NoSuchElementException {
        if(this.paquet.size()>0){
            return this.paquet.get(this.paquet.size()-1);
        }else{
            throw new NoSuchElementException("Erreur : on ne peut pas obtenir le sommet d'un paquet vide.");
        }
    }

    public Carte getCarte(int i) throws ExceptionJoueur{
        if(this.getNombreDeCartes()>i && i>=0){
            return this.paquet.get(i);
        } else {
            throw new ExceptionJoueur("Erreur : numéro de carte incorrect.");
        }
    }

    /*public int getIndiceCarte(Carte c){
        int indice=-1;
        int i=0;
        for(Carte c2:this){
            if(c2.toString().equals(c.toString())){
                indice=
            }
        }
    }*/

    /**
     * Retourne la dernière carte du paquet
     *
     * @return la dernière carte du paquet
     */
    public Carte piocher() throws NoSuchElementException{
        if(!this.paquet.isEmpty()){
            Carte pioche=this.paquet.get(this.paquet.size()-1);
            this.paquet.remove(this.paquet.size()-1);
            return pioche;
        }else{
            throw new NoSuchElementException("Erreur : on ne peut pas piocher dans un paquet vide.");
        }
    }

    public void poserCarteSurTalon(Carte c){
        this.ajouter(c);
    }

    public void ecrire(String nomDeFichier) throws ErreurFichier {
        try {
            File file = new File(nomDeFichier);
            BufferedWriter flotFiltre;
            FileWriter flot;
            if (!file.exists()) {
                throw new ErreurFichier("Erreur : le fichier n'existe pas");
            }else{
                flot = new FileWriter(file);
                flotFiltre = new BufferedWriter(flot);
                String s;
                int compteur=0;
                for (Carte c : this.paquet) {
                    if(compteur!=0){flotFiltre.newLine();}
                    s = c.getNom()+c.getNumero() + " " + c.getCouleur();
                    flotFiltre.write(s);
                    compteur++;
                }
                flotFiltre.close();
            }
        } catch (ErreurFichier | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lire(String nomDeFichier)throws ErreurFichier{
        Uno uno=new Uno();
        try{
            File file = new File(nomDeFichier);
            BufferedReader flotFiltre;
            FileReader flot;
            if (!file.exists()) {
                throw new ErreurFichier("Erreur : le fichier n'existe pas");
            }else{//cas normal
                flot = new FileReader(nomDeFichier) ;
                flotFiltre = new BufferedReader(flot) ;
                String nom="";
                String couleur="";
                String ligne = flotFiltre.readLine() ;
                String numero="";
                char[] chars;
                Character carac;
                Boolean espace=false;
                int j=0;
                while (ligne != null) {
                    nom="";//reboot du nom de la carte présente sur la ligne
                    numero="";//reboot du numéro de la carte présente sur la ligne
                    couleur="";//reboot de la couleur de la carte présente sur la ligne
                    espace=false;//reboot de la présence d'un espace dans la ligne
                    chars=ligne.toCharArray();
                    for(int i=0;i<chars.length;i++){
                        if(chars[i]==' '){//s'il y a la présence d'un espace
                            espace=true;
                        }
                        carac=chars[i];
                        if(!Character.isDigit(chars[i]) && !espace){//si ce n'est pas un numéro et qu'on a pas encore eu d'espace -> on cherche le nom de la carte
                            nom+=chars[i];
                        }else if(Character.isDigit(chars[i])){//si on est en présence du numéro
                            numero+=chars[i];
                        }else if(!Character.isDigit(chars[i]) && espace && chars[i]!=' '){//on a un caractère mais l'espace est passé -> couleur
                            couleur+=chars[i];
                        }
                    }
                    //initialiser le paquet de carte avec ce qu'on a dans nom numero et couleur
                    if(nom.equals("Chiffre")){//TODO : il faudra ajouter le numéro quand on aura les éléments pour définir une carte par son numéro
                        if(couleur.equals("Rouge")){
                            this.ajouter(new CarteChiffre(uno, Couleur.ROUGE,Integer.parseInt(numero)));
                        }else if(couleur.equals("Vert")){
                            this.ajouter(new CarteChiffre(uno,Couleur.VERT,Integer.parseInt(numero)));
                        }else if(couleur.equals("Bleu")){
                            this.ajouter(new CarteChiffre(uno,Couleur.BLEU,Integer.parseInt(numero)));
                        }else if(couleur.equals("Jaune")){
                            this.ajouter(new CarteChiffre(uno,Couleur.JAUNE,Integer.parseInt(numero)));
                        }
                    }else if(nom.equals("ChangementDeSens")){
                        if(couleur.equals("Rouge")){
                            this.ajouter(new CarteChangementDeSens(uno,Couleur.ROUGE));
                        }else if(couleur.equals("Vert")){
                            this.ajouter(new CarteChangementDeSens(uno,Couleur.VERT));
                        }else if(couleur.equals("Bleu")){
                            this.ajouter(new CarteChangementDeSens(uno,Couleur.BLEU));
                        }else if(couleur.equals("Jaune")){
                            this.ajouter(new CarteChangementDeSens(uno,Couleur.JAUNE));
                        }else{//si la carte n'a pas de couleur ou qu'elle n'est pas valide
                            this.ajouter(new CarteChangementDeSens(uno));
                        }
                    }else if(nom.equals("PasseTonTour")){
                        if(couleur.equals("Rouge")){
                            this.ajouter(new CartePasseTonTour(uno,Couleur.ROUGE));
                        }else if(couleur.equals("Vert")){
                            this.ajouter(new CartePasseTonTour(uno,Couleur.VERT));
                        }else if(couleur.equals("Bleu")){
                            this.ajouter(new CartePasseTonTour(uno,Couleur.BLEU));
                        }else if(couleur.equals("Jaune")){
                            this.ajouter(new CartePasseTonTour(uno,Couleur.JAUNE));
                        }else{//elle n'a pas de couleur
                            this.ajouter(new CartePasseTonTour(uno));
                        }
                    }else if(nom.equals("PlusDeux")){
                        if(couleur.equals("Rouge")){
                            this.ajouter(new CartePlus2(uno,Couleur.ROUGE));
                        }else if(couleur.equals("Vert")){
                            this.ajouter(new CartePlus2(uno,Couleur.VERT));
                        }else if(couleur.equals("Bleu")){
                            this.ajouter(new CartePlus2(uno,Couleur.BLEU));
                        }else if(couleur.equals("Jaune")){
                            this.ajouter(new CartePlus2(uno,Couleur.JAUNE));
                        }else{//pas de couleur
                            this.ajouter(new CartePlus2(uno));
                        }
                    }else if(nom.equals("PlusQuatre")){
                        this.ajouter(new CartePlus4(uno));
                    }else if(nom.equals("Joker")){
                        this.ajouter(new CarteJoker(uno));
                    }

                    ligne = flotFiltre.readLine();
                }
                flotFiltre.close();
            }
        }catch(ErreurFichier | IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(this);
    }
}