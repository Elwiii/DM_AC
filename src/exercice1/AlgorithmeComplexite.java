/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice1;

/**
 *
 * @author CHAYEM Samy
 * @deprecated
 */
public class AlgorithmeComplexite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArbreBinaire<Integer> b = new ArbreBinaire(2, new ArbreBinaire(1), new ArbreBinaire(4, new ArbreBinaire(3), new ArbreBinaire(5)));
//        ArbreBinaire b = new ArbreBinaire(2, new ArbreBinaire(1), new ArbreBinaire(4, new ArbreBinaire(1), new ArbreBinaire(5))); Fonctionne mais pas correct, car 2x 1
        ArbreBinaire<Integer> c = new ArbreBinaire(10, new ArbreBinaire(8), new ArbreBinaire(12));
        ArbreBinaire<Integer> a = new ArbreBinaire(6, b, c);
        System.out.println("Arbre b est\n\t" + b);
        System.out.println("Arbre c est\n\t" + c);
        System.out.println("Arbre a est\n\t" + a);
        System.out.println("Arbre a contient-il 7 ? " + a.contient(7));
//        System.out.println("Profondeur c est " + c.profondeurArbre());
//        System.out.println("Profondeur c est " + c.profondeurArbre(c));
        a.inserer(7);
        System.out.println("Arbre a contient-il 7 ? " + a.contient(7));
        System.out.println("Arbre a, après insertion de 7, est\n\t" + a);
    }

}
