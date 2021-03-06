/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

/**
 *
 * Implémentation de la structure de donnée probabiliste TREAP.
 *
 * @author CARRARA Nicolas et CHAYEM Samy
 * @param <E>
 */
public class Treap<E extends Comparable<E>> extends ArbreBinaireCartesien<E> {

    public Treap() {
        super();
    }

    /**
     * Inserer une clef dans le TREAP
     *
     * @param clef
     * @throws exercice1.Exercice1Exception
     */
    public void insererClefTreap(E clef) throws Exercice1Exception {
        if (clef == null) {
            throw new Exercice1Exception("La clef ne peut être null");
        }
        double priorite = Math.random();
        while (recherchePriorite(priorite, SEARCH_TREE) != NIL) {
            priorite = Math.random();
        }
        NoeudArbre<E> noeud = new NoeudArbre(clef, priorite);
        this.listNoeudArbres.add(noeud); // La liste de Noeud va nous permettre de recuperer (grace a l'unicite des clefs et des priorités) les noeuds que l'on recherche, cf: methode rechercheClef
        insererNoeudAB(noeud);
        remonterNoeud(noeud);
    }

    /**
     * C'est un treap et non un ABC donc impossible de choisir la priorité lors
     * de l'insertion. Je fais ça comme ça, car je vois pas comment cacher
     * l'utilisation de cette fonction pour les TREAPs tout en la laissant
     * possible pour les ABC
     *
     * @param clef
     * @param priorite
     * @deprecated
     */
    @Override
    public void insererClef(E clef, double priorite) {
        throw new UnsupportedOperationException("Impossible de choisir la priorité lors de l'insertion d'un noeud dans un TREAP");
    }

}
