/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import java.util.List;


/**
 *
 * @author CHAYEM Samy
 */
public class TestParseEtLevenstein {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<String> listeMot = MorphingTools.parseTexteVersMot("test/exercice2/fichierTestParse");
//        List<String> listeMot = MorphingTools.parseTexteVersMot("C:\\Users\\Katia\\Desktop\\test.txt");
        System.out.println("Liste :\n" + listeMot);
        for (int i = 0; i < listeMot.size(); i++) {
            for (int j = 0; j < listeMot.size(); j++) {
                System.out.println("<"+listeMot.get(i)+","+listeMot.get(j)+"> : "+MorphingTools.distanceLevenshtein(listeMot.get(i), listeMot.get(j)));
            }
        }
    }

}
