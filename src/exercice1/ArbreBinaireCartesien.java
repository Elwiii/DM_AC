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

    /* constante noeud à l'extrémité */
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
                res = "(" + clef + ";" + priorite /*+"||| son père : "+pere.clef+"/"+pere.priorite*/+")" + "-> " + "[" + filsGauche + " , " + filsDroit + "]";
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
     */
    public void insererClef(E clef, double priorite) {
//        insererNoeudABC(root, new NoeudArbre(clef, priorite));
        //On insère d'abord dans l'AB dans tenir compte de la priorité
        NoeudArbre<E> noeud = new NoeudArbre(clef, priorite);
        insererNoeudAB(noeud);
        remonterNoeud(noeud);
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

//    /**
//     * Insère un noeud dans un ABC
//     *
//     * @param rootABC la racine du ABC
//     * @param noeud
//     */
//    protected void insererNoeudABC(NoeudArbre<E> rootABC, NoeudArbre<E> noeud) {
//        //On insère d'abord dans l'AB dans tenir compte de la priorité
////        insererNoeudAB();
//        //On fait remonter        
//    }
//    /**
//     * Insère un noeud dans un ABC intermediaire
//     *
//     * @param noeudPivot la racine du ABC intermediaire
//     * @param noeud
//     */
//    protected void insererNoeud(NoeudArbre<E> noeudPivot, NoeudArbre<E> noeud) {
//        // le noeud doit être inséré au dessus ou en dessous du rootPivot ?
//        if (noeudPivot.priorite < noeud.priorite) {
//            insererAuDessusPivot(noeudPivot, noeud);
//        } else {
//            insererEnDessousPivot(noeudPivot, noeud);
//        }
//    }
//    /**
//     * @deprecated 
//     * @param noeudPivot
//     * @param noeud 
//     */
//    private void insererAuDessusPivot(NoeudArbre<E> noeudPivot, NoeudArbre<E> noeud) {
//        // le pivot est il le root du treap ?
//        if (noeudPivot == root) {
//            root = noeud;
//        } else {
//            // le pivot est le fils droit ou gauche de son père ?
//            if (noeudPivot == noeudPivot.pere.filsDroit) {
//                noeudPivot.pere.filsDroit = noeud;
//            } else {
//                noeudPivot.pere.filsGauche = noeud;
//            }
//
//            noeud.pere = noeudPivot.pere;
//            
//        }
//        
//        
//        
//        // placement du pivot sous le noeud
//        // est on dans l'étape initiale( où l'arbre est vide )?
//        if (noeudPivot != NIL) {
//            // on place le pivot a droite ou a gauche du noeud ?
//            if (noeud.clef.compareTo(noeudPivot.clef) < 0) {
//                noeud.filsDroit = noeudPivot;
//            } else {
//                noeud.filsGauche = noeudPivot;
//            }
//            
//            noeudPivot.pere = noeud;
//        }
//    }
//    
//    /**
//     * @deprecated
//     * @param noeudPivot
//     * @param noeud 
//     */
//    private void insererEnDessousPivot(NoeudArbre<E> noeudPivot, NoeudArbre<E> noeud) {
//        // insère a droite du root ou du gauche ?
//            if (noeud.clef.compareTo(noeudPivot.clef) < 0) {
//                // le root est il une feuille ?
//                if (noeudPivot.filsGauche != NIL) {
//                    insererNoeudABC(noeudPivot.filsGauche, noeud);
//                } else {
//                    noeudPivot.filsGauche = noeud;
//                    noeud.pere = noeudPivot;
//                }
//            } else {
//                // le root est il une feuille ?
//                if (noeudPivot.filsDroit != NIL) {
//                    insererNoeudABC(noeudPivot.filsDroit, noeud);
//                } else {
//                    noeudPivot.filsDroit = noeud;
//                    noeud.pere = noeudPivot;
//                }
//            }
//    }
}
