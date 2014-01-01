/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import exercice1.Exercice1Exception;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Trouvons un arbre couvrant de poids minimal dans le graphe complet pondéré
 * dont les noueuds sont les mots extraits et les poids des arêtes sont les
 * distances d'editions
 *
 * @author CARRARA Nicolas et CHAYEM Samy
 */
public class Question6 {

    public static void main(String[] args) {
        List<String> listeMot = MorphingTools.parseTexteVersMot("test/exercice2/fichierTestParse");
        Integer[][] edges = new Integer[listeMot.size()][listeMot.size()];
        String[] vertex = new String[listeMot.size()];

        for (int i = 0; i < listeMot.size(); i++) {
            vertex[i] = listeMot.get(i);
            for (int j = i; j < listeMot.size(); j++) {
                edges[i][j] = MorphingTools.distanceLevenshtein(listeMot.get(i), listeMot.get(j));
            }
        }
        try {
            Graphe<String> g = new Graphe(vertex, edges);
            System.out.println("" + g);
            System.out.println("" + g.kurskal());
        } catch (Exercice2Exception | Exercice1Exception ex) {
            Logger.getLogger(Question6.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
