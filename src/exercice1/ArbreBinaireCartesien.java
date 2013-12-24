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

    /**
     * La classe noeud de l'arbre binaire cartesien. Une clef et une priorité . 
     *
     */
    class NoeudArbre<T extends Comparable<T>> {

        T clef;
        double priorite;
        NoeudArbre<T> filsGauche;
        NoeudArbre<T> filsDroit;
        NoeudArbre<T> pere;

        public NoeudArbre(T clef, double priorite) {
            this.clef = clef;
            this.priorite = priorite;
            filsGauche = null;
            filsDroit = null;
            pere = null;
        }

    }

    protected NoeudArbre<E> root;

    public ArbreBinaireCartesien() {
        root = null;
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
     * @param root la racine du ABC intermediaire
     * @param noeud
     */
    protected void insererNoeud(NoeudArbre<E> root, NoeudArbre<E> noeud) {
        if (this.root == null) {
            this.root = noeud;
        } else {
            // le noeud doit être inséré au dessus ou en dessous du root ?
            if (root.priorite < noeud.priorite) {
                // le root est il le root du treap principale ?
                if (root == this.root) {
                    this.root = noeud;
                } else {
                    // root est le fils droit ou gauche de son père ?
                    if (root == root.pere.filsDroit) {
                        root.pere.filsDroit = noeud;
                    } else {
                        root.pere.filsGauche = noeud;
                    }
                    noeud.pere = root.pere;
                }

                root.pere = noeud;

                // on place le root a droite ou a gauche du noeud ?
                if (noeud.clef.compareTo(root.clef) < 0) {
                    noeud.filsDroit = root;
                } else {
                    noeud.filsGauche = root;
                }

            } else {
                // insère a droite du root ou du gauche
                if (noeud.clef.compareTo(root.clef) < 0) {
                    insererNoeud(root.filsGauche, noeud);
                } else {
                    insererNoeud(root.filsDroit, noeud);
                }
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
        
        if(root.filsDroit != null)
            hauteurDroite = 1 + computeHauteurIntermediaire(root.filsDroit);
        
        if(root.filsGauche != null)
            hauteurGauche = 1 + computeHauteurIntermediaire(root.filsGauche);

        return Math.max(hauteurGauche,hauteurDroite);
    }
    
    /**
     * Calcul la hauteur du ABC principal
     * @return la hauteur du ABC principal
     */
    public int computeHauteur(){
        int hauteur = 0;
        if(root !=null)
            hauteur = computeHauteurIntermediaire(root);
        return hauteur;
    }

}
