/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercice2;

import exercice1.Exercice1Exception;
import exercice2.Exercice2Exception;
import exercice2.Graphe;
import exercice2.MorphingTools;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARRARA Nicolas
 */
public class Question7 {
    
    private static final String DEFAULT_FILE="test/exercice2/fichierTestQuestion7";
    
    public static void main(String[] args){
        String path = DEFAULT_FILE;
        if(args.length != 1){
            System.err.println("Vous n'avez pas spécifier de ficher,ce fichier a été utilisé par default : "+DEFAULT_FILE);
            //System.exit(0);
        }else{
            path = args[0];
        }
        List<String> listeMot = MorphingTools.parseTexteVersMot(path);
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
