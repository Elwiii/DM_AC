/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import exercice1.Exercice1Exception;
import exercice1.UnionFind;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implémentation de la structure Graphe, utilisation d'une matrice pour les
 * arêtes
 *
 *
 * A noter, pour représenter un graphe non orienté, laisser à null toute la
 * partie sous la diagonale du tableau edge.
 *
 * @author CARRARA Nicolas et CHAYEM Samy
 * @param <E>
 */
public class Graphe<E extends Comparable<E>> {

    /* arête et poids entre sommets */
    private final Integer[][] edges;

    /* sommets du graphe */
    private final E[] vertex;

    /**
     * Constructeur de la classe Graphe. Creation d'un Graphe
     *
     * @param vertex les sommets
     * @param edges la matrices réprésentative des arêtes, e(u,v) = null : pas
     * d'arrête, e(u,v) = x : poid de l'arête
     * @throws Exercice2Exception
     */
    public Graphe(E[] vertex, Integer[][] edges) throws Exercice2Exception {
        if (!(vertex.length == edges[0].length && vertex.length == edges.length)) {
            throw new Exercice2Exception("La matrice des arrêtes doit être de taille n*n, où n réprésente le nombre de sommet");
        }
        this.edges = edges;
        this.vertex = vertex;
    }

    /**
     *
     * 0 KRUSKAL (G,w) 1 E := ø 2 pour chaque sommet v de G 3 faire
     * CRÉER-ENSEMBLE (v) 4 trier les arêtes de G par ordre croissant de poids w
     * 5 pour chaque arête (u,v) de G prise par ordre de poids croissant 6 faire
     * ENSEMBLE-REPRÉSENTATIF (u) ≠ ENSEMBLE-REPRÉSENTATIF (v) 7 alors ajouter
     * l'arête (u,v) à l'ensemble E 8 UNION (u,v) 9 retourner E
     *
     * @condition : le graphe ne doit pas être orienté ! i.e la matrice doit
     * etre null en dessous ou au dessus d'une diagonale. On choisit en dessous
     * comme convention
     * @return un arbre couvrant de poids minimal dans ce graphe.
     * @throws exercice2.Exercice2Exception
     * @throws exercice1.Exercice1Exception
     */
    public Graphe<E> kurskal() throws Exercice2Exception, Exercice1Exception {
        Graphe<E> result = new Graphe(vertex, getNullTab(vertex.length));
        UnionFind<E> uf = new UnionFind();
        for (E v : vertex) {
            uf.makeset(v);
        }
        List<Edge> edgesTriees = getEdgesTriees();
        Edge edgeTemp;
        E u;
        E v;
        // je ne sais si foreach respecte le i de 0 à size, donc je préfère faire une boucle for
        for (int i = 0; i < edgesTriees.size(); i++) {
            edgeTemp = edgesTriees.get(i);
            u = vertex[edgeTemp.positionV1];
            v = vertex[edgeTemp.positionV2];
            if (uf.find(u, UnionFind.COMPRESSION) != uf.find(v, UnionFind.COMPRESSION)) {
                result.edges[edgeTemp.positionV1][edgeTemp.positionV2] = edgeTemp.poid;
                uf.union(u, v);
            }
        }
        return result;
    }

    /**
     * Methode qui genere un tableau, dont le contenu est vide, de dimension
     * n*n.
     *
     * @param n dimension du tableau
     * @return tableau null de dimension n*n
     */
    private Integer[][] getNullTab(int n) {
        return new Integer[n][n];
    }

    /**
     * Methode qui genere la liste de toutes les arêtes du graphe, triées par
     * ordre croissant des poids
     *
     * @return la liste des arêtes du graphe triées par ordre croissant des
     * poids
     */
    private List<Edge> getEdgesTriees() {
        ArrayList<Edge> edgesTriees = new ArrayList<>();
        Integer p;
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                p = this.edges[i][j];
                if (p != null) {
                    edgesTriees.add(new Edge(i, j, p));
                }
            }
        }
        Collections.sort(edgesTriees);
        return edgesTriees;

    }

    @Override
    public String toString() {
        String s = "";
        s = s + "Sommets du Graphe : \n    " + Arrays.asList(vertex) + "\n";
        for (int i = 0; i < vertex.length; i++) {
            s = s + vertex[i] + " : " + Arrays.asList(edges[i]) + "\n";
        }
        return s;
    }

    /**
     * Private Class Edge Permet d'extraire les arêtes triées dans l'ordre
     * croissant des poids
     *
     * @param <E>
     */
    private class Edge implements Comparable<Edge> {

        int positionV1;
        int positionV2;
        Integer poid;

        Edge(int v1, int v2, Integer p) {
            positionV1 = v1;
            positionV2 = v2;
            poid = p;
        }

        /**
         * Methode qui permet de comparer 2 arêtes via leur poids respectif
         *
         * @param t arête t
         * @return resultat de la comparaison 2 arêtes via leur poids respectif
         */
        @Override
        public int compareTo(Edge t) {
            // et si l'un des poids est null on fait quoi ? normalement ça n'arrive pas mais quand même ...
            return this.poid - t.poid;
        }
    }

    /**
     * Methode qui recherche le chemin pour aller d'un sommet de depart à un
     * sommet d'arrivé
     *
     * @throws exercice2.Exercice2Exception
     * @condition le graphe doit être connexe et acyclique (i.e. un arbre) et
     * non orienté (ie c'est null sous la diagonale)
     * @param positionDepart position du sommet de depart dans le tableau vertex
     * @param positionArrivee position du sommet d'arrivée dans le tableau
     * @return l'ensemble des sommets parcourus
     */
    public List<E> getPath(int positionDepart, int positionArrivee) throws Exercice2Exception {
        List<E> path = new ArrayList<>();
        getPath(positionDepart, positionArrivee, path);
        if (!path.contains(vertex[positionArrivee])) {
            throw new Exercice2Exception("chemin introuvable, votre graphe n'est probablement pas un arbre");
        }
        return path;
    }

    /**
     * Method qui recherche le chemin pour aller d'un sommet de depart à un
     * sommet d'arrivé en supposant qu'on a deja parcouru un certain nombre de
     * sommets.
     *
     * @condition le graphe doit être connexe et acyclique (i.e. un arbre) et
     * non orienté (ie c'est null sous la diagonale)
     * @param sommetParcouru les sommets parcourus jusque là
     * @param positionDepart position du sommet de depart dans le tableau vertex
     * @param positionArrivee position du sommet d'arrivée dans le tableau
     * vertex
     */
    private void getPath(int positionDepart, int positionArrivee, List<E> sommetParcouru) {
        sommetParcouru.add(vertex[positionDepart]);
        boolean found = (vertex[positionDepart].compareTo(vertex[positionArrivee]) == 0);
        int i = 0;
        while (!found && i < vertex.length) {
            /**
             * si le ième sommet est lié par une arrête à notre sommet de
             * depart, alors on regarde si on peut trouver le sommet d'arrivé à
             * partir du ième sommet
             *
             */
            if ((this.edges[positionDepart][i] != null || this.edges[i][positionDepart] != null) && !sommetParcouru.contains(vertex[i])) {
                getPath(i, positionArrivee, sommetParcouru);
                found = sommetParcouru.contains(vertex[positionArrivee]);
                if (!found) {
                    // si on arrive pas à sommetArrivé, c'est que passer par le ième sommet n'est pas viable
                    sommetParcouru.remove(vertex[i]);
                }
            }

            i++;
        }
    }

}
