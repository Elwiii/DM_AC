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
            System.out.println("\n******************ABC******************************\n");
            // ABC
            ArbreBinaireCartesien<Integer> abc = new ArbreBinaireCartesien<>();
            abc.insererClef(11, 15);
            abc.insererClef(6, 2);
            abc.insererClef(4, 3);
            abc.insererClef(1, 0);
            abc.insererClef(5, 8);
            abc.insererClef(7, 1);
            abc.insererClef(9, 4);
            abc.insererClef(8, 9);
            abc.insererClef(15, 12);
            abc.insererClef(12, 6);
            abc.insererClef(16, 5);
            System.out.println(abc.computeHauteur() + " / " + abc);
            int i;
            i = 8;
            abc.supprimer(i);
            System.out.println("Suppression de " + i + " : ");
            System.out.println(abc.computeHauteur() + " / " + abc);

            System.out.println("\n******************TREAP******************************\n");
            // Treap
            Treap<Integer> treap = new Treap<>();
            treap.insererClefTreap(3);
            System.out.println(treap.computeHauteur() + " / " + treap);
            treap.insererClefTreap(30);
            System.out.println(treap.computeHauteur() + " / " + treap);
            treap.insererClefTreap(1);
            System.out.println(treap.computeHauteur() + " / " + treap);
            treap.insererClefTreap(6);
            System.out.println(treap.computeHauteur() + " / " + treap);
            treap.insererClefTreap(4);
            System.out.println(treap.computeHauteur() + " / " + treap);
            i = 1;
            treap.supprimer(i);
            System.out.println("Suppression de " + i + " : ");
            System.out.println(treap.computeHauteur() + " / " + treap);

            System.out.println("\n******************UNION FIND******************************\n");
            // UnionFind
            UnionFind<Integer> unionFind = new UnionFind<>();
            unionFind.makeset(10);
            System.out.println(unionFind);
            unionFind.makeset(40);
            System.out.println(unionFind);
            unionFind.makeset(12);
            System.out.println(unionFind);
            unionFind.makeset(4);
            UnionFind u = unionFind;
            System.out.println(unionFind);
            System.out.println("SANS l'algo \"Compression\" :");
            System.out.println(unionFind.union(10, 40));
            System.out.println(unionFind.union(40, 12));
            System.out.println(unionFind.find(10));
            System.out.println(unionFind.find(40));
            System.out.println(unionFind.find(12));

            System.out.println("AVEC l'algo \"Compression\" :");
            System.out.println(u.union(10, 40, 1));
            System.out.println(u.union(40, 12, 1));
            System.out.println(u.find(10, 1));
            System.out.println(u.find(40, 1));
            System.out.println(u.find(12, 1));
        } catch (Exercice1Exception ex) {
            Logger.getLogger(VerificationGenerale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
