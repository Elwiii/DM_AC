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
 * @author CARRARA Nicolas et CHAYEM Samy
 * @param <E>
 */
public class ArbreBinaireCartesien<E extends Comparable<E>> {

    /* Constante Noeud présente aux extremités*/
    protected final NoeudArbre NIL = setNIL();

    /* Racine de l'ABC */
    protected NoeudArbre<E> root;

    /* Liste de tous les noeuds de l'arbre, pour la RECHERCHE de type SEARCH_LIST, sinon inutile*/
    protected List<NoeudArbre<E>> listNoeudArbres = new ArrayList<>();

    /* indique qu'on veut chercher un élément en parcourant la liste des noeuds */
    public static final int SEARCH_LIST = 0;

    /* indique qu'on veut chercher un élément en parcourant l'arbre binaire cartésien */
    public static final int SEARCH_TREE = 1;

    /**
     * Public Class Noeud associé à l'ABC.
     * Noeud est caracterisé par : 
     *      une clef et une priorité .
     *      un ABC_filsGauche et un ABC_filsDroit
     *      un Noeud Pere
     * @param <E> 
     */
    public class NoeudArbre<E extends Comparable<E>> {

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

    /**
     * workaround pour que NIL ait pour fils lui même
     * @return 
     */
    private NoeudArbre setNIL() {
        NoeudArbre nil = new NoeudArbre(null, -1);
        nil.filsDroit = nil;
        nil.filsGauche = nil;
        return nil;
    }

    /**
     * Constructeur ABC
     */
    public ArbreBinaireCartesien() {
        root = NIL;
    }

    /**
     * On insère noeud(clef,p) dans le abc
     *
     * @param clef
     * @param priorite
     * @throws exercice1.Exercice1Exception
     */
    public void insererClef(E clef, double priorite) throws Exercice1Exception {
        if (clef == null) {
            throw new Exercice1Exception("La clef ne peut être null");
        }

        if (rechercheClef(clef, SEARCH_TREE) != NIL) { // SEARCH_LIST marche nickel
            throw new Exercice1Exception("La clef existe déjà");
        }

        if (recherchePriorite(priorite, SEARCH_TREE) != NIL) { // SEARCH_LIST marche nickel
            throw new Exercice1Exception("La priorite existe déjà");
        }

        NoeudArbre<E> noeud = new NoeudArbre(clef, priorite);
        listNoeudArbres.add(noeud);
        //On insère d'abord dans l'AB dans tenir compte de la priorité
        insererNoeudAB(noeud);
        //On remonte ensuite le noeud en fonction de la priorité
        remonterNoeud(noeud);
    }

    /**
     *
     * @param priorite
     * @param typeRecherche
     * @return
     * @throws Exercice1Exception
     */
    protected NoeudArbre recherchePriorite(double priorite, int typeRecherche) throws Exercice1Exception {
        NoeudArbre result = NIL;
        switch (typeRecherche) {
            case SEARCH_LIST:
                result = recherchePrioriteList(priorite);
                break;
            case SEARCH_TREE:
                if (root != NIL) {
                    result = recherchePrioriteArbre(priorite, root);
                }
                break;
            default:
                throw new Exercice1Exception("type de recherche inconnu");
        }
        return result;

    }

    /**
     * recherche le noeud de priorite priorite à partir du noeud courant
     * (parcours l'arbre)
     *
     * @param priorite
     * @param noeudCourant
     * @return
     */
    private NoeudArbre recherchePrioriteArbre(double priorite, NoeudArbre noeudCourant) throws Exercice1Exception {
        NoeudArbre result = NIL;
        NoeudArbre resultGauche;
        NoeudArbre resultDroit;
        if (noeudCourant != NIL) {
            if (priorite == noeudCourant.priorite) {
                result = noeudCourant;
            } else {
                // on peut optimiser ici, en faisant deja recherche sur le noeud gauche, puis si pas de resutat, on fait recherche sur noeudDroit
                resultGauche = recherchePrioriteArbre(priorite, noeudCourant.filsGauche);
                resultDroit = recherchePrioriteArbre(priorite, noeudCourant.filsDroit);
                if (resultDroit == NIL && resultGauche != NIL) {
                    result = resultGauche;
                } else if (resultDroit != NIL && resultGauche == NIL) {
                    result = resultDroit;
                } else if (resultDroit != NIL && resultGauche != NIL) {
                    throw new Exercice1Exception("cas impossible, ça veut dire que deux noeuds ont même priorité dans l'arbre");
                } else if (resultDroit == NIL && resultGauche == NIL) {
                    // do nothing
                }
            }
        }
        return result;
    }

    /**
     * cherche le noeud de priorite priorite en parcourant la liste de noeud (au
     * lieu de parcourir l'arbre)
     *
     * @param priorite
     * @return
     */
    private NoeudArbre recherchePrioriteList(double priorite) {
        NoeudArbre result = NIL;
        int i = 0;
        while (result == NIL && i < listNoeudArbres.size()) {
            if (listNoeudArbres.get(i).priorite == priorite) {
                result = listNoeudArbres.get(i);
            }
            i++;
        }
        return result;
    }

    /**
     * recherche une clef dans l'abc
     *
     * @param clef
     * @param typeRecherche
     * @return le noeud de clef clef.
     * @throws Exercice1Exception
     */
    public NoeudArbre rechercheClef(E clef, int typeRecherche) throws Exercice1Exception {
        NoeudArbre result = NIL;
        switch (typeRecherche) {
            case SEARCH_LIST:
                result = rechercheClefList(clef);
                break;
            case SEARCH_TREE:
                if (root != NIL) {
                    result = rechercheClefArbre(clef, root);
                }
                break;
            default:
                throw new Exercice1Exception("type de recherche inconnu");
        }
        return result;
    }

    /**
     * Recherche une clef en parcourant l'arbre (et non la liste) à partir d'un
     * noeudCourant
     *
     * @param clef
     * @param noeudCourant
     * @return
     */
    private NoeudArbre rechercheClefArbre(E clef, NoeudArbre<E> noeudCourant) {
        NoeudArbre<E> result = NIL;
        if (noeudCourant.clef.compareTo(clef) == 0) {
            result = noeudCourant;
        } else if (noeudCourant.clef.compareTo(clef) < 0) {
            if (noeudCourant.filsDroit != NIL) {
                result = rechercheClefArbre(clef, noeudCourant.filsDroit);
            }
        } else if (noeudCourant.clef.compareTo(clef) > 0) {
            if (noeudCourant.filsGauche != NIL) {
                result = rechercheClefArbre(clef, noeudCourant.filsGauche);
            }
        }
        return result;
    }

    /**
     * On indique une clef dont on recherche l'existence dans l'arbre via la
     * lsit
     *
     * @param clef
     * @param typeRecherche
     * @return Le noeud auquel la clef est associée. Si la clef n'existe pas, on
     * renvoie NIL
     */
    private NoeudArbre rechercheClefList(E clef) {
        NoeudArbre<E> result = NIL;
        int i = 0;
        while (result == NIL && i < listNoeudArbres.size()) {
            if (listNoeudArbres.get(i).clef.compareTo(clef) == 0) {
                result = listNoeudArbres.get(i);
            }
            i++;
        }
        return result;
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

    public void supprimer(E clef) throws Exercice1Exception {
        NoeudArbre<E> noeud = this.rechercheClef(clef, SEARCH_TREE);
        if (noeud == NIL) {
            throw new Exercice1Exception("clef " + clef + " inexistante");
        }
        while (!(noeud.filsDroit == NIL && noeud.filsGauche == NIL)) {
            descendreNoeudUnCran(noeud);
        }

        if (noeud.pere.filsDroit == noeud) {
            noeud.pere.filsDroit = NIL;
        } else if (noeud.pere.filsGauche == noeud) {
            noeud.pere.filsGauche = NIL;
        } else if (noeud.pere == NIL){
            root = NIL;
        }
        else{
            throw new Exercice1Exception("Etat de l'arbre corrompu, le noeud " + noeud + " n'est ni le fils droit ni le fils gauche de son père");
        }

    }

    /**
     * descend le noeud d'un cran dans l'arbre en respectant la règle des arbre
     * binaire. Suppose que le noeud.pere != NIL
     *
     * @param noeud
     */
    private void descendreNoeudUnCran(NoeudArbre<E> noeud) throws Exercice1Exception {
        // père du noeud
        NoeudArbre<E> p = noeud.pere;
        // fils droit du noeud
        NoeudArbre<E> d = noeud.filsDroit;
        // fils gauche du noeud
        NoeudArbre<E> g = noeud.filsGauche;
        // noeud temporaire
        NoeudArbre<E> temp;

        if (g.priorite > d.priorite) {
            if (noeud == root) {
                root = g;
            }

            g.pere = p;
            temp = g.filsDroit;
            g.filsDroit = noeud;

            if (noeud == p.filsDroit) {
                p.filsDroit = g;
            } else {
                p.filsGauche = g;
            }

            noeud.filsGauche = temp;
            noeud.pere = g;

        } else if (g.priorite < d.priorite) {
            if (noeud == root) {
                root = d;
            }
            d.pere = p;
            temp = d.filsGauche;
            d.filsGauche = noeud;

            if (noeud == p.filsDroit) {
                p.filsDroit = d;
            } else {
                p.filsGauche = d;
            }

            noeud.filsDroit = temp;
            noeud.pere = d;
        } else {
            throw new Exercice1Exception("Arbre erronée, priorité dupliquée");
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
