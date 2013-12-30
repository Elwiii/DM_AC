/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import exercice1.Exercice1Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARRARA Nicolas
 */
public class Question7 {

    private static final String DEFAULT_FILE = "test/exercice2/fichierTestQuestion7";
    
    private static final String USAGE = "USAGE : java Question7 pathFile positionDepart positionArrivee";

    public static void main(String[] args) {
        String path = DEFAULT_FILE;
        int positionDepart = 1;
        int positionArrivee = 0;
        if (args.length != 3) {
            System.err.println(USAGE+"\n\n"+"Vous n'avez pas spécifié de ficher ou vous n'avez pas spécifié la position"
                    + "\ndes sommets de départ et d'arrivé. Les paramètres suivant sont utilisé par default :\nfichier : " + DEFAULT_FILE+"\nposition départ : 1 (THERMES)\nposition arrivé : 0 (RONGEUR)\n\n");
        } else {
            path = args[0];
            positionDepart = Integer.parseInt(args[1]);
            positionArrivee = Integer.parseInt(args[2]);
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
            System.out.println("Graphe :\n" + g);
            System.out.println("Graphe couvrant de poid minimal :\n" + g.kurskal());
            g = g.kurskal();
            System.out.println("Path : "+g.getPath(positionDepart, positionArrivee));
        } catch (Exercice2Exception | Exercice1Exception ex) {
            Logger.getLogger(Question6.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
