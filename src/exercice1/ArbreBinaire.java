/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

/**
 *
 * @author CHAYEM Samy
 *
 * Arbre Binaire mais je ne sais pas s'il faut en faire un ABR, donc je fais
 * l'ABR. Cependant, il doit y avoir des petites subtilités que je n'ai pas dû implementer
 */
public class ArbreBinaire {

    protected int racine;
    protected ArbreBinaire sousArbre_Gauche;
    protected ArbreBinaire sousArbre_Droit;

    public ArbreBinaire(int racine) {
        this.racine = racine;
    }

    public ArbreBinaire(int racine, ArbreBinaire arbreGauche, ArbreBinaire arbreDroit) {
//        this.racine = racine;
//        if (arbreGauche.getRacine() < racine && arbreDroit.getRacine() > getRacine()) {
//            sousArbre_Gauche = arbreGauche;
//            sousArbre_Droit = arbreDroit;
//        } else {
//            System.out.println("Tous les elements de la partie gauche doivent etre inferieur à la racine \n"
//                    + "Tous les elements de la partie droite doivent etre superieur à la racine \n");
//        }

        assert (arbreGauche.getRacine() < racine && arbreDroit.getRacine() > getRacine()) : "Tous les elements de la partie gauche doivent etre inferieur à la racine \n"
                + "Tous les elements de la partie droite doivent etre superieur à la racine \n";
        this.racine = racine;
        sousArbre_Gauche = arbreGauche;
        sousArbre_Droit = arbreDroit;
    }

    /**
     *
     * @return racine de la racine de l'arbre
     */
    public int getRacine() {
        return racine;
    }

    /**
     *
     * @return un element de type ArbreBinaire correspond au sous arbre gauche
     * de l'arbre principal
     */
    public ArbreBinaire getSousArbreGauche() {
        return sousArbre_Gauche;
    }

    /**
     *
     * @return un element de type ArbreBinaire correspond au sous arbre droit de
     * l'arbre principal
     */
    public ArbreBinaire getSousArbreDroit() {
        return sousArbre_Droit;
    }

    /**
     *
     * Affichage Trouver sur internet, pas mal
     *
     * @return String ArbreBinaire
     */
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer(); //Création d'un buffer
        if (this != null) {
            s.append(racine);  //On ajoute la racine de l'arbre
            if ((sousArbre_Gauche != null) || (sousArbre_Droit != null)) { //S'il existe un sous arbre alors
                s.append('('); //On ouvre une parenthèse
                if (this.sousArbre_Gauche != null) {
                    s.append(sousArbre_Gauche.toString());  //S'il existe un sous arbre gauche, on l'ajoute et on rappelle la méthode toString() 
                }
                s.append(", ");//On ajoute une virgule
                if (this.sousArbre_Droit != null) {
                    s.append(sousArbre_Droit.toString());  //S'il existe un sous arbre gauche, on l'ajoute et on rappelle la méthode toString() 
                }
                s.append(')'); //Enfin, on referme la parenthèse
            }
        }
        String string = new String(s); //On crée un objet de type String avec ce buffer
        return string;  //On le renvoie
    }

    /**
     *Je sais, c'est un peu bizarre de devoir remettre l'arbre en parametre, mais comme ça on peut avoir la taille d'un sous arbre qui compose l'arbre principal
     * @param arbre correspond à l'arbre dont on veut connaitre la profondeur
     * @return profondeur de l'arbre
     */
    public int profondeurArbre(ArbreBinaire arbre) {
        if (arbre == null) {
            return 0;
        } else {
            return (1 + Math.max(profondeurArbre(arbre.getSousArbreGauche()), profondeurArbre(arbre.getSousArbreDroit())));
        }
    }

    /**
     *
     * @param racine correspond à la racine dont on veut savoir l'existence dans
     * l'arbre
     * @return vrai si l'arbre contient la racine indiquée
     */
    public boolean contient(int racine) {
        if (racine == getRacine()) {
            return true; // Implique que la racine existe deja dans l'arbre
        }
        if (racine < getRacine() && getSousArbreGauche() != null) {
            return getSousArbreGauche().contient(racine);
        }
        if (racine > getRacine() && getSousArbreDroit() != null) {
            return getSousArbreDroit().contient(racine);
        }
        return false;
    }

    /**
     *
     * @param racine correspond à la racine que l'on veut inserer dans l'arbre
     */
    public void inserer(int racine) {
        if (contient(racine) == false) { // On decide de ne pas inserer une racine qui existe deja dans l'arbre
            if (racine < getRacine()) {
                if (getSousArbreGauche() != null) {
                    getSousArbreGauche().inserer(racine);
                } else {
                    sousArbre_Gauche = new ArbreBinaire(racine);
                }
            }

            if (racine > getRacine()) {
                if (getSousArbreDroit() != null) {
                    getSousArbreDroit().inserer(racine);
                } else {
                    sousArbre_Droit = new ArbreBinaire(racine);
                }
            }
        }
    }
}
