/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

/**
 * Implémentation de la structure de donnée probabiliste ARBRE CARTESIEN.
 *
 * @author CARRARA Nicolas
 * @param <E>
 */
public class ArbreBinaireCartesien<E extends Comparable<E>> {

    /* constant noeud à l'extrémité */
    private final NoeudArbre NIL = new NoeudArbre(null, -1);

    /* la racine de l'abc */
    protected NoeudArbre<E> root;

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
                res = "(" + clef + ";" + priorite + ")" + "-> " + "[" + filsGauche + " , " + filsDroit + "]";
            }
            return res;
        }
    }

    public ArbreBinaireCartesien() {
        root = NIL;
    }

    /**
     * On choisit une priorité p au hasard et on insère noeud(clef,p) dans le
     * treap
     *
     * @param clef
     * @param priorite
     */
    public void insererClef(E clef, double priorite) {
        insererNoeud(root, new NoeudArbre(clef, priorite));
    }

    /**
     * Insère un noeud dans un ABC intermediaire
     *
     * @param rootPivot la racine du ABC intermediaire
     * @param noeud
     */
    protected void insererNoeud(NoeudArbre<E> rootPivot, NoeudArbre<E> noeud) {
        // le noeud doit être inséré au dessus ou en dessous du rootIntermediaire ?
        if (rootPivot.priorite < noeud.priorite) {
            insererAuDessusPivot(rootPivot, noeud);
        } else {
            insererEnDessousPivot(rootPivot, noeud);
        }
    }

    private void insererAuDessusPivot(NoeudArbre<E> noeudPivot, NoeudArbre<E> noeud) {
// le noeud doit être inséré au dessus du rootIntermediaire
        System.out.println("flag1");
        // le root est il le root du treap principale ?
        if (noeudPivot == root) {
//                System.out.println("flag2");
            root = noeud;

        } else {
            // root est le fils droit ou gauche de son père ?
            if (noeudPivot == noeudPivot.pere.filsDroit) {
                noeudPivot.pere.filsDroit = noeud;
            } else {
                noeudPivot.pere.filsGauche = noeud;
            }

            noeud.pere = noeudPivot.pere;

            noeudPivot.pere = noeud;

        }

        // est on dans l'étape initiale( où l'arbre est vide )?
        if (noeudPivot != NIL) {
            // on place le root a droite ou a gauche du noeud ?
            if (noeud.clef.compareTo(noeudPivot.clef) < 0) {
                noeud.filsDroit = noeudPivot;
            } else {
                noeud.filsGauche = noeudPivot;
            }
        }
    }
    
    private void insererEnDessousPivot(NoeudArbre<E> noeudPivot, NoeudArbre<E> noeud) {
        // insère a droite du root ou du gauche ?
            if (noeud.clef.compareTo(noeudPivot.clef) < 0) {
                // le root est il une feuille ?
                if (noeudPivot.filsGauche != NIL) {
                    insererNoeud(noeudPivot.filsGauche, noeud);
                } else {
                    noeudPivot.filsGauche = noeud;
                    noeud.pere = noeudPivot;
                }
            } else {
                // le root est il une feuille ?
                if (noeudPivot.filsDroit != NIL) {
                    insererNoeud(noeudPivot.filsDroit, noeud);
                } else {
                    noeudPivot.filsDroit = noeud;
                    noeud.pere = noeudPivot;
                }
            }
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
