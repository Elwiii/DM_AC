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
 * l'ABR. Cependant, il doit y avoir des petites subtilités que je n'ai pas dû
 * implementer
 * @param <E>
 * @deprecated
 */
public class ArbreBinaire<E extends Comparable<E>>{

    protected E racine;
    protected ArbreBinaire sousArbreGauche;
    protected ArbreBinaire sousArbreDroit;

    public ArbreBinaire(E racine) {
        this.racine = racine;
    }

    public ArbreBinaire(E racine, ArbreBinaire arbreGauche, ArbreBinaire arbreDroit) {
//        this.racine = racine;
//        if (arbreGauche.getRacine() < racine && arbreDroit.getRacine() > getRacine()) {
//            sousArbreGauche = arbreGauche;
//            sousArbreDroit = arbreDroit;
//        } else {
//            System.out.println("Tous les elements de la partie gauche doivent etre inferieur à la racine \n"
//                    + "Tous les elements de la partie droite doivent etre superieur à la racine \n");
//        }

        assert ((arbreGauche.racine.compareTo(racine) < 0) && (arbreDroit.racine.compareTo(racine) > 0)) : "Tous les elements de la partie gauche doivent etre inferieur à la racine \n"
                + "Tous les elements de la partie droite doivent etre superieur à la racine \n";
        this.racine = racine;
        this.sousArbreGauche = arbreGauche;
        this.sousArbreDroit = arbreDroit;
    }

    /**
     *
     * @return racine de la racine de l'arbre
     */
    public E getRacine() {
        return this.racine;
    }

    /**
     *
     * @return un element de type ArbreBinaire correspond au sous arbre gauche
     * de l'arbre principal
     */
    public ArbreBinaire getSousArbreGauche() {
        return this.sousArbreGauche;
    }

    /**
     *
     * @return un element de type ArbreBinaire correspond au sous arbre droit de
     * l'arbre principal
     */
    public ArbreBinaire getSousArbreDroit() {
        return this.sousArbreDroit;
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
            s.append(this.racine);  //On ajoute la racine de l'arbre
            if ((this.sousArbreGauche != null) || (this.sousArbreDroit != null)) { //S'il existe un sous arbre alors
                s.append('('); //On ouvre une parenthèse
                if (this.sousArbreGauche != null) {
                    s.append(this.sousArbreGauche.toString());  //S'il existe un sous arbre gauche, on l'ajoute et on rappelle la méthode toString() 
                }
                s.append(", ");//On ajoute une virgule
                if (this.sousArbreDroit != null) {
                    s.append(this.sousArbreDroit.toString());  //S'il existe un sous arbre gauche, on l'ajoute et on rappelle la méthode toString() 
                }
                s.append(')'); //Enfin, on referme la parenthèse
            }
        }
        String string = new String(s); //On crée un objet de type String avec ce buffer
        return string;  //On le renvoie
    }

    /**
     * Je sais, c'est un peu bizarre de devoir remettre l'arbre en parametre,
     * mais comme ça on peut avoir la taille d'un sous arbre qui compose l'arbre
     * principal
     *
     * @param arbre correspond à l'arbre dont on veut connaitre la profondeur
     * @return profondeur de l'arbre
     */
    public int profondeurArbre(ArbreBinaire arbre) {
        if (arbre == null) {
            return 0;
        } else {
            return (1 + Math.max(profondeurArbre(arbre.sousArbreGauche), profondeurArbre(arbre.sousArbreDroit)));
        }
    }
    
    /**
     *
     * @return profondeur de l'arbre
     */
    public int profondeurArbre() {
        if (this == null) {
            return 0;
        } else {
            return (1 + Math.max(this.sousArbreGauche.profondeurArbre(), this.sousArbreDroit.profondeurArbre()));
        }
    }

    /**
     *
     * @param valeur correspond à la valeur dont on veut savoir l'existence dans
 l'arbre
     * @return vrai si l'arbre contient la valeur indiquée
     */
    public boolean contient(E valeur) {
        if (valeur == this.racine) {
            return true; // Implique que la valeur existe deja dans l'arbre
        }
        if (valeur.compareTo(this.racine) < 0 && this.sousArbreGauche != null) {
            return sousArbreGauche.contient(valeur);
        }
        if (valeur.compareTo(this.racine) > 0 && this.sousArbreDroit != null) {
            return this.sousArbreDroit.contient(valeur);
        }
        return false;
    }

    /**
     *
     * @param valeur correspond à la valeur que l'on veut inserer dans l'arbre
     */
    public void inserer(E valeur) {
        if (contient(valeur) == false) { // On decide de ne pas inserer une valeur qui existe deja dans l'arbre
            if (valeur.compareTo(this.racine) < 0) {
                if (this.sousArbreGauche != null) {
                    this.sousArbreGauche.inserer(valeur);
                } else {
                    this.sousArbreGauche = new ArbreBinaire(valeur);
                }
            }

            if (valeur.compareTo(this.racine) > 0) {
                if (this.sousArbreDroit != null) {
                    this.sousArbreDroit.inserer(valeur);
                } else {
                    this.sousArbreDroit = new ArbreBinaire(valeur);
                }
            }
        }
    }
}
