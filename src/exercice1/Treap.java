/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

/**
 * Implémentation de la structure de donnée probabiliste TREAP.
 *
 * @author CARRARA Nicolas
 * @param <E>
 */
public class Treap<E extends Comparable<E>> {

    /**
     * La classe noeud du treap. Une clef et une priorité entre 0 et 1 . On
     * pourrait coder autre.
     *
     */
    class NoeudTreap<T extends Comparable<T>> {

        T clef;
        double priorite;
        NoeudTreap<T> filsGauche;
        NoeudTreap<T> filsDroit;
        NoeudTreap<T> pere;

        public NoeudTreap(T clef, double priorite) {
            this.clef = clef;
            this.priorite = priorite;
            filsGauche = null;
            filsDroit = null;
            pere = null;
        }

    }

    private NoeudTreap<E> root;

    public Treap() {
        root = null;
    }

    /**
     * On choisit une priorité p au hasard et on insère noeud(clef,p) dans le
     * treap
     *
     * @param clef
     */
    public void insererClef(E clef) {
        double p = Math.random();
        insererNoeud(root, new NoeudTreap(clef, p));

    }

    /**
     * Insère un noeud dans un Treap
     *
     * @param root la racine du treap intermédiaire
     * @param noeud
     */
    private void insererNoeud(NoeudTreap<E> root, NoeudTreap<E> noeud) {
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
     * Calculer la hauteur du treap intermédiaire
     *
     * @return la hauteur du treap intermediaire
     */
    private int computeHauteurIntermediaire(NoeudTreap<E> root) {
        int hauteurGauche = 0;
        int hauteurDroite = 0;
        
        if(root.filsDroit != null)
            hauteurDroite = 1 + computeHauteurIntermediaire(root.filsDroit);
        
        if(root.filsGauche != null)
            hauteurGauche = 1 + computeHauteurIntermediaire(root.filsGauche);

        return Math.max(hauteurGauche,hauteurDroite);
    }
    
    /**
     * Calcul la hauteur du treap principal
     * @return la hauteur du treap principal
     */
    public int computeHauteur(){
        int hauteur = 0;
        if(root !=null)
            hauteur = computeHauteurIntermediaire(root);
        return hauteur;
    }

}
