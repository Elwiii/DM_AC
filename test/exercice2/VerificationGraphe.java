/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import exercice1.Exercice1Exception;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARRARA Nicolas et CHAYEM Samy
 */
public class VerificationGraphe {

    public static void main(String[] args) {
        try {
            String[] vertex = {     "A", "B", "C"};
            Integer[][] edges = {   {null, 1, 0}, 
                                    {null, null, 6},
                                    {null, null, null}
                                };
            Graphe graphe = new Graphe(vertex, edges);
            System.out.println(graphe.toString());
            System.out.println(graphe.kurskal());
            // Fonctionne niquel
            
            String[] vertex0 = {     "A", "B", "C"};
            Integer[][] edges0 = {   {null, 1, 0}, 
                                    {null, null, 6},
                                    {null, null, null}
                                };
            Graphe graphe0 = new Graphe(vertex0, edges0);
            System.out.println(graphe0.toString());
            System.out.println(graphe0.kurskal());
            // Fonctionne niquel

            String[] vertex1 = {    "A", "B", "C", "D", "E", "F", "G"};
            Integer[][] edges1 = {  {null, 2, 3, 3, null, null, null},
                                    {null, null, 4, null, 3, null, null},
                                    {null, null, null, 5, 1, null, null},
                                    {null, null, null, null, null, 7, null},
                                    {null, null, null, null, null, 8, null},
                                    {null, null, null, null, null, null, 9},
                                    {null, null, null, null, null, null, null}
                                  };
            Graphe graphe1 = new Graphe(vertex1, edges1);
            System.out.println(graphe1.toString());
            System.out.println(graphe1.kurskal());
            // Fonctionne niquel

            String[] vertex2 = {     "A", "B", "C", "D", "E", "F"};
            Integer[][] edges2 = {  {null, 4, null, null, null, 2},
                                    {null, null, 6, null, null, 5},
                                    {null, null, null, 3, null, 1},
                                    {null, null, null, null, 2, null},
                                    {null, null, null, null, null, 4},
                                    {null, null, null, null, null, null}
                                };
            Graphe graphe2 = new Graphe(vertex2, edges2);
            System.out.println(graphe2.toString());
            System.out.println(graphe2.kurskal());
            // Fonctionne niquel
        } catch (Exercice2Exception | Exercice1Exception ex) {
            Logger.getLogger(VerificationGraphe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
