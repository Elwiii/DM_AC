/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

import java.util.ArrayList;

/**
 *
 * Utilisation du type de recherche : Path Compression
 *
 * cf http://en.wikipedia.org/wiki/Disjoint-set_data_structure
 *
 * @author CARRARA Nicolas
 * @param <E>
 */
public class UnionFind<E> {

    /**
     * ensemble des noeuds de Union find. Chaque noeud représente un élément
     * unique dans la structure.
     */
    private final ArrayList<NoeudUF<E>> nodes;

    private final NoeudUF<E> MISSING = new NoeudUF(null, null, -1);
    //private final NoeudUF<E> ROOT = new NoeudUF(null, null, -2);

    /**
     * type de recherche
     */
    public final static int COMPRESSION = 1;

    /**
     * Un noeud d'un arbre de la structure UnionFind
     *
     * @param <E>
     */
    private class NoeudUF<E> {

        E data;
        NoeudUF<E> pere;
        double priorite;

        private NoeudUF(E data, NoeudUF<E> pere, double priorite) {
            this.data = data;
            this.pere = pere;
            this.priorite = priorite;
        }

        @Override
        public String toString() {
            String result;
            if (this == MISSING) {
                result = "MISSING";
            } /*else if (this == ROOT) {
                result = "ROOT";
            } */else {
                String pereData = pere.data + "";
                if (pere.data == null) {
                    pereData = "root";
                }
                result = "(" + "" + data + ","+priorite+ " : <" + pereData + ">)";
            }

            return result;
        }
    }

    /**
     * initialise les noeuds de l'union find.
     */
    public UnionFind() {
        nodes = new ArrayList<>();
    }

    /**
     * Insère l'element, si il n'existe pas, dans Union find avec une priorité
     * aléatoire.
     *
     * @param data
     * @throws exercice1.Exercice1Exception
     */
    public void makeset(E data) throws Exercice1Exception {
        if (data == null) {
            throw new Exercice1Exception("La donnée ne peut être null");
        }
        if (getNodeOfData(data) == MISSING) {
            NoeudUF n = new NoeudUF(data, null, Math.random());
            n.pere = n;
            nodes.add(n);
        }
    }

    /**
     * Cherche le noeud de donnée data puis remonte de père en père jusqu'a
     * retrouver le noeud racine de l'arbre. Itérativement.
     *
     * @param data
     * @return la valeur de la racine de l'arbre qui contient l'element data,
     * null si cet element n'existe pas
     * @throws exercice1.Exercice1Exception
     */
    public E find(E data) throws Exercice1Exception {
        if (data == null) {
            throw new Exercice1Exception("La donnée ne peut être null");
        }
        NoeudUF<E> result = getNodeOfData(data);
        if (result != MISSING) {
            while (result.pere != result) {
                result = result.pere;
            }
        }

        return result.data;
    }

    /**
     * Cherche le noeud de donnée data puis remonte de père en père jusqu'a
     * retrouver le noeud racine de l'arbre. Applique l'ago de typeRecherche sur
     * x
     *
     * @param x
     * @param typeRecherche
     * @return la valeur de la racine de l'arbre qui contient l'element data,
     * null si cet element n'existe pas
     * @throws Exercice1Exception
     */
    public E find(E x, int typeRecherche) throws Exercice1Exception {
        if (x == null) {
            throw new Exercice1Exception("La donnée ne peut être null");
        }
        NoeudUF<E> nodeX = getNodeOfData(x);
        E result = null;
        if (nodeX != MISSING) {
            switch (typeRecherche) {
                case COMPRESSION:
                    result = findCompression(nodeX).data;
                    break;
                default:
                    throw new Exercice1Exception("Type de recherche inconnu");
            }
        }
        return result;
    }

    /**
     * Cherche le noeud root correpondant à nodeX. Applique l'algorithme de
     * compression pendant la remonté de l'arbre.
     *
     * @param nodeX
     * @return
     * @throws Exercice1Exception
     */
    private NoeudUF<E> findCompression(NoeudUF<E> nodeX) throws Exercice1Exception {
        NoeudUF<E> result = nodeX.pere;

        if (nodeX.pere != nodeX) {
            nodeX.pere = findCompression(nodeX.pere);
        } else {
            result = nodeX;
        }
        return nodeX.pere;
    }

    /**
     * fusionne deux ensembles, le root des deux arbres fusionés est celui ayant
     * la plus grande priorité des deux anciens root
     *
     * @param x
     * @param y
     * @return le nouveau root, null si x ou y n'existe pas.
     * @throws exercice1.Exercice1Exception
     */
    public E union(E x, E y) throws Exercice1Exception {
        if (x == null || y == null) {
            throw new Exercice1Exception("x et y ne peuvent être null");
        }
        E root = null;
        E rootX = find(x);
        E rootY = find(y);
        if (rootX != null && rootY != null) {
            NoeudUF<E> nodeRootX = getNodeOfData(rootX);
            NoeudUF<E> nodeRootY = getNodeOfData(rootY);
            if (nodeRootX.priorite > nodeRootY.priorite) {
                nodeRootY.pere = nodeRootX;
                root = nodeRootX.data;
            } else {
                nodeRootX.pere = nodeRootY;
                root = nodeRootY.data;
            }
        }
        return root;
    }

    /**
     * fusionne deux ensembles, le root des deux arbres fusionés est celui ayant
     * la plus grande priorité des deux anciens root. Applique l'algo de
     * typeRecherche sur x et y
     *
     * @param x
     * @param y
     * @param typeRecherche
     * @return le nouveau root, null si x ou y n'existe pas.
     * @throws Exercice1Exception
     */
    public E union(E x, E y, int typeRecherche) throws Exercice1Exception {
        if (x == null || y == null) {
            throw new Exercice1Exception("x et y ne peuvent être null");
        }
        E root = null;
        E rootX = find(x, typeRecherche);
        E rootY = find(y, typeRecherche);
        if (rootX != null && rootY != null) {
            NoeudUF<E> nodeRootX = getNodeOfData(rootX);
            NoeudUF<E> nodeRootY = getNodeOfData(rootY);
            if (nodeRootX.priorite > nodeRootY.priorite) {
                nodeRootY.pere = nodeRootX;
                root = nodeRootX.data;
            } else {
                nodeRootX.pere = nodeRootY;
                root = nodeRootY.data;
            }
        }
        return root;
    }

    /**
     * Retourne le noeud qui contient l'element data si il existe, MISSING
     * sinon.
     *
     * @param data
     * @return
     */
    private NoeudUF<E> getNodeOfData(E data) {
        assert (data != null) : "l'élement spécifié ne peut être null";
        NoeudUF<E> result = MISSING;
        NoeudUF<E> temp;
        int i = 0;
        while (result == MISSING && i < nodes.size()) {
            temp = nodes.get(i);
            if (temp.data.equals(data)) {
                result = temp;
            }
            i++;
        }
        return result;
    }

    @Override
    public String toString() {
        return this.nodes + "";
    }

}
