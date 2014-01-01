/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARRARA Nicolas et CHAYEM Samy
 */
public class VerificationGenerale {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // ABC
            ArbreBinaireCartesien<Integer> abc = new ArbreBinaireCartesien<>();
            abc.insererClef(11, 15);
            abc.insererClef(6, 2);
//            abc.insererClef(4, 3);
//            abc.insererClef(1, 2);
//            abc.insererClef(5, 8);
//            abc.insererClef(7, 1);
//            abc.insererClef(9, 4);
//            abc.insererClef(8, 9);
            abc.insererClef(15, 12);
//            abc.insererClef(12, 6);
//            abc.insererClef(16, 5);
            System.out.println(abc.computeHauteur() + " / " + abc);
//            System.out.println("Fin : " + abc.remove(8));
//            abc.remove(8);
            abc.enlever(11);
            System.out.println(abc.computeHauteur() + " / " + abc);

//            abc.insererClef(20, 30);
//            System.out.println(abc.computeHauteur() + " / " + abc);
//            abc.insererClef(12, 10);
//            System.out.println(abc.computeHauteur() + " / " + abc);
//            System.out.println("Noeud : " + abc.rechercheClef(1, ArbreBinaireCartesien.SEARCH_LIST));
//            System.out.println("Noeud");
//            for (int i = 0; i < abc.listNoeudArbres.size(); i++) {
//                System.out.println(abc.listNoeudArbres.get(i));
//            }
            // Treap
//            Treap<Integer> treap = new Treap<>();
//            treap.insererClefTreap(3);
//            System.out.println(treap.computeHauteur() + " / " + treap);
//            treap.insererClefTreap(30);
//            System.out.println(treap.computeHauteur() + " / " + treap);
//            treap.insererClefTreap(1);
//            System.out.println(treap.computeHauteur() + " / " + treap);
//            treap.insererClefTreap(6);
//            System.out.println(treap.computeHauteur() + " / " + treap);
//            treap.insererClefTreap(4);
//            System.out.println(treap.computeHauteur() + " / " + treap);
//            System.out.println("Noeud : " + treap.rechercheClef(2));
//            System.out.println("Noeud");
//            for (int i = 0; i < abc.listNoeudArbres.size(); i++) {
//                System.out.println(abc.listNoeudArbres.get(i));
//            }
//
//            // UnionFind
//            UnionFind<Integer> unionFind = new UnionFind<>();
//            unionFind.makeset(10);
//            System.out.println(unionFind);
//            unionFind.makeset(40);
//            System.out.println(unionFind);
//            unionFind.makeset(12);
//            System.out.println(unionFind);
//            unionFind.makeset(4);
//            System.out.println(unionFind);
//            //unionFind.union(10, 40);
//            System.out.println(unionFind.union(10, 40));
//            System.out.println(unionFind.union(40, 12));
//            System.out.println(unionFind.find(10));
//            System.out.println(unionFind.find(40));
//            System.out.println(unionFind.find(12));
//
//            System.out.println("Compression");
//            System.out.println(unionFind.union(10, 40, 1));
//            System.out.println(unionFind.union(40, 12, 1));
//            System.out.println(unionFind.find(10, 1));
//            System.out.println(unionFind.find(40, 1));
//            System.out.println(unionFind.find(12, 1));
        } catch (Exercice1Exception ex) {
            Logger.getLogger(VerificationGenerale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
