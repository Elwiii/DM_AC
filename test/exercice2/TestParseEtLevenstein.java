/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Test de parse et levenstein.
 *
 * @author CARRARA Nicolas et CHAYEM Samy
 */
public class TestParseEtLevenstein {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        List<String> listeMot = MorphingTools.parseTexteVersMot("test/exercice2/fichierTestParse");
//        System.out.println("Liste :\n" + listeMot);
//        for (int i = 0; i < listeMot.size(); i++) {
//            for (int j = 0; j < listeMot.size(); j++) {
//                System.out.println("<" + listeMot.get(i) + "," + listeMot.get(j) + "> : " + MorphingTools.distanceLevenshtein(listeMot.get(i), listeMot.get(j)));
//            }
//        }
        List<String> etape = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add("RONGEUR");
        path.add("BONHEUR");
        path.add("BERNER");
        path.add("THERMES");
//        System.out.println("dl = " + MorphingTools.distanceLevenshtein("BERNER", "BONHEUR", etapes));
        System.out.println(chemin(path));
//        System.out.println("etapes : " + etapes);
        //  System.out.println("dl = "+MorphingTools.distanceLevenshtein("NICHE","CHIENS"));
//        System.out.println("dl = "+MorphingTools.distanceLevenshtein("TRIER","TRI"));
    }

    private static List<String> chemin(List<String> pathList) {
        List<String> etapes = new ArrayList<>();
        int i = 0;
        etapes.add(pathList.get(i).toUpperCase());
        while (i < pathList.size() - 1) {
            MorphingTools.distanceLevenshtein(pathList.get(i), pathList.get(i + 1), etapes);
            etapes.add(pathList.get(i + 1).toUpperCase());
            i = i + 1;
        }
        return etapes;
    }

}
