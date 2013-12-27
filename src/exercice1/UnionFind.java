/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

import java.util.ArrayList;

/**
 * http://en.wikipedia.org/wiki/Disjoint-set_data_structure
 * http://fr.wikipedia.org/wiki/Union-Find
 *
 * @author CARRARA Nicolas
 * @param <E>
 */
public class UnionFind<E> {

    /**
     * ensemble des noeuds de Union find. Chaque noeud représente un élément unique dans la structure.
     */
    private final ArrayList<NoeudUF<E>> nodes;

    
    private final NoeudUF<E> MISSING = new NoeudUF(null, null, -1);
    private final NoeudUF<E> ROOT = new NoeudUF(null, null, -2);

    /**
     * Un noeud d'un arbre de la structure UnionFind
     *
     * @param <E>
     */
    public class NoeudUF<E> {

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
            String result ;
            if(this == MISSING){
                result = "MISSING";
            } else if(this == ROOT){
                result = "ROOT";
            }else {
                String pereData = pere.data+"";
                if(pere.data == null)
                    pereData = "root";
                result = "("+""+data +":<"+ pereData+">)";
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
     * Insère l'element, si il n'existe pas, dans Union find avec une priorité aléatoire.
     * @param data 
     */
    public void makeset(E data) {
        if (getNodeOfData(data) == MISSING) {
            nodes.add(new NoeudUF(data, ROOT, Math.random()));
        }
    }

    /**
     * Cherche le noeud de donnée data puis remonte de père en père jusqu'a
     * retrouver le noeud racine de l'arbre. Itérativement.
     *
     * @param data
     * @return la valeur de la racine de l'arbre qui contient l'element data, null si cet element n'existe pas
     */
    public E find(E data) {
        NoeudUF<E> result = getNodeOfData(data);
        if (result != MISSING) {
            while (result.pere != ROOT) {
                result = result.pere;
            }
        }

        return result.data;
    }

    /**
     * Retourne le noeud qui contient l'element data si il existe, MISSING
     * sinon.
     *
     * @param data
     * @return
     */
    private NoeudUF<E> getNodeOfData(E data) {
        assert(E!=null):"l'élement spécifié ne peut être null";
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
    
    /**
     * fusionne deux ensembles, le root des deux arbres fusionés est celui ayant la plus grande priorité des
     * deux anciens root
     * @param x
     * @param y
     * @return le nouveau root, null si x ou y n'existe pas.
     */
    public E union(E x, E y){
        E root = null;
        E rootX = find(x);
        E rootY = find(y);
        if(rootX != null && rootY!=null){
            NoeudUF<E> nodeRootX = getNodeOfData(rootX);
            NoeudUF<E> nodeRootY = getNodeOfData(rootY);
            if(nodeRootX.priorite>nodeRootY.priorite){
                nodeRootY.pere = nodeRootX;
                root = nodeRootX.data;
            }else{
                nodeRootX.pere = nodeRootY;
                root = nodeRootX.data;
            }
        }
        return root;
    }
    
    @Override
    public String toString(){
        return this.nodes+"";
    }

}
