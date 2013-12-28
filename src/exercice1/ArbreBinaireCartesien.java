/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de la structure de donnée ARBRE CARTESIEN.
 *
 * @author CARRARA Nicolas
 * @param <E>
 */
public class ArbreBinaireCartesien<E extends Comparable<E>> {

    /* constante noeud à l'extrémité */
    private final NoeudArbre NIL = new NoeudArbre(null, -1);

    /* la racine de l'abc */
    protected NoeudArbre<E> root;
    List<E> listClef = new ArrayList<>(); // Liste de toutes les clés de l'arbre, UNICITE
    List<Double> listPriorite = new ArrayList<>(); // Liste de toutes les priorités de l'arbre, UNICITE
    List<NoeudArbre<E>> listNoeudArbres = new ArrayList<>(); // Liste de tous les noeuds de l'arbre, RECHERCHE

    /**
     * La classe noeud de l'arbre binaire cartesien. Une clef et une priorité .
     *
     * @param <E>
     */
    protected class NoeudArbre<E extends Comparable<E>> {

        E clef;
        double priorite;
        NoeudArbre<E> filsGauche;
        NoeudArbre<E> filsDroit;
        NoeudArbre<E> pere;

        protected NoeudArbre(E clef, double priorite) {
            this.clef = clef;
            this.priorite = priorite;
            filsGauche = NIL;
            filsDroit = NIL;
            pere = NIL;
        }

        @Override
        public String toString() {
            String res = "NIL";
            if (this != NIL) {
                res = "(" + clef + "; " + priorite /*+"||| son père : "+pere.clef+"/"+pere.priorite*/ + ")" + " -> " + "[" + filsGauche + ", " + filsDroit + "]";
            }
            return res;
        }
    }

    public ArbreBinaireCartesien() {
        root = NIL;
    }

    /**
     * On choisit une priorité p au hasard et on insère noeud(clef,p) dans le
     * treap.
     *
     * @param clef
     * @param priorite
     * @throws exercice1.Exercice1Exception
     */
    public void insererClef(E clef, double priorite) throws Exercice1Exception {
        if (clef == null) {
            throw new Exercice1Exception("La clef ne peut être null");
        }
//        insererNoeudABC(root, new NoeudArbre(clef, priorite));
        //On insère d'abord dans l'AB dans tenir compte de la priorité
        if (this.listClef.contains(clef) || this.listPriorite.contains(priorite)) { // On utilise des listes pour eviter de reparcourir tout l'arbre. Le parcour d'une liste est très simple, et la vitesse de calcul est augmentée.
            throw new Exercice1Exception("La clé : " + clef + ", et/ou la priorité : " + priorite + " existent deja dans l'arbre. Elles doivent etre uniques");
        } else {
            NoeudArbre<E> noeud = new NoeudArbre(clef, priorite);
            this.listNoeudArbres.add(noeud); // La liste de Noeud va nous permettre de recuperer (grace a l'unicite des clefs et des priorités) les noeuds que l'on recherche, cf: methode rechercheClef
            this.listClef.add(clef);
            this.listPriorite.add(priorite);
            insererNoeudAB(noeud);
            remonterNoeud(noeud);
        }
    }

    /**
     * On indique une clef dont on recherche l'existence dans l'arbre.
     * 
     * @param clef
     * @return Le noeud auquel la clef est associée. Si la clef n'existe pas, on renvoie NIL
     */
    public NoeudArbre rechercheClef(E clef) { 
        for(int i = 0; i < this.listNoeudArbres.size(); i++){ //L'utilisation d'une liste de noeuds simplifie la recherche.
            if(this.listNoeudArbres.get(i).clef == clef){
                return this.listNoeudArbres.get(i);
            }
        }
        return NIL;
    }

    /**
     * insertion dans l'ABC sans tenir compte de la priorité (on l'insère aux
     * feuilles), itérativement
     *
     * @param noeud
     */
    protected void insererNoeudAB(NoeudArbre<E> noeud) {
        boolean stop = false;
        NoeudArbre<E> pivot = root;
        NoeudArbre<E> pivotPere = NIL;
        // l'arbre est il vide ?
        if (root == NIL) {
            root = noeud;
        } else {
            // tant qu'on a pas atteint une feuille, on cherche la feuille où on doit placer le pivot.
            while (!stop) {
                pivotPere = pivot;
                //Doit on insérer à droite ou à gauche du pivot ?
                if (noeud.clef.compareTo(pivot.clef) < 0) {
                    // on insère à gauche
                    pivot = pivot.filsGauche;
                    // on a atteint une feuille
                    if (pivot == NIL) {
                        pivotPere.filsGauche = noeud;
                        noeud.pere = pivotPere;
                        stop = true;
                    }
                } else {
                    // on insère à droite
                    pivot = pivot.filsDroit;
                    // on a atteint une feuille
                    if (pivot == NIL) {
                        pivotPere.filsDroit = noeud;
                        noeud.pere = pivotPere;
                        stop = true;
                    }
                }
            }
        }
    }

    /**
     * remonte le noeud dans l'abre tant que sa priorité est supérieur celle de
     * son père.
     *
     * @param noeud le noeud à remonter
     */
    protected void remonterNoeud(NoeudArbre<E> noeud) {
        while (noeud.pere.priorite < noeud.priorite && noeud.pere != NIL) {
            remonterNoeudUnCran(noeud);
        }
    }

    /**
     * remonte le noeud d'un cran dans l'arbre en respectant la règle des arbre
     * binaire. Suppose que le noeud.pere != NIL
     *
     * @param noeud
     */
    private void remonterNoeudUnCran(NoeudArbre<E> noeud) {
        assert (noeud.pere != NIL) : "Noeud.pere = NIL";
        // père du noeud
        NoeudArbre<E> p = noeud.pere;
        // fils droit du noeud
        NoeudArbre<E> d = noeud.filsDroit;
        // fils gauche du noeud
        NoeudArbre<E> g = noeud.filsGauche;
        // grand père du noeud
        NoeudArbre<E> gp = noeud.pere.pere;

        if (noeud == p.filsDroit) {
            // la clef du noeud est plus grande que celle de son père.
            noeud.filsGauche = p;
            p.filsDroit = g;
            g.pere = p;

        } else {
            if (noeud == p.filsGauche) {
                // la clef du noeud est plus petite que celle de son père.
                noeud.filsDroit = p;
                p.filsGauche = d;
                d.pere = p;
            } else {
                // Cas impossible
                System.err.println("Etat incohérent : le noeud doit être forcement le fils gauche ou droit de son père");
            }
        }

        if (gp != NIL) {
            if (p == gp.filsDroit) {
                gp.filsDroit = noeud;
            } else {
                if (p == gp.filsGauche) {
                    gp.filsGauche = noeud;
                } else {
                    // Cas impossible
                    System.err.println("Etat incohérent : le père du noeud doit être forcement le fils gauche ou droit du grand père du noeud");
                }
            }
        } else {
            // le grand père est NIL => le pere était root, donc notre noeud devient root
            root = noeud;
        }
        // le grand père du noeud devient son père
        noeud.pere = gp;
        // le noeud devient le père de son père.
        p.pere = noeud;
    }

    /**
     * Calculer la hauteur du ABC intermediaire
     *
     * @return la hauteur du ABC intermediaire
     */
    private int computeHauteurIntermediaire(NoeudArbre<E> root) {
        int hauteurGauche = 0;
        int hauteurDroite = 0;

        if (root.filsDroit != NIL) {
            hauteurDroite = 1 + computeHauteurIntermediaire(root.filsDroit);
        }

        if (root.filsGauche != NIL) {
            hauteurGauche = 1 + computeHauteurIntermediaire(root.filsGauche);
        }

        return Math.max(hauteurGauche, hauteurDroite);
    }

    /**
     * Calcul la hauteur du ABC principal
     *
     * @return la hauteur du ABC principal
     */
    public int computeHauteur() {
        int hauteur = 0;
        if (root != NIL) {
            hauteur = computeHauteurIntermediaire(root);
        }
        return hauteur;
    }

    @Override
    public String toString() {
        return root.toString();
    }

}
