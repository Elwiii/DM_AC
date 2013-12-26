/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

/**
 *
 * @author CHAYEM Samy
 */
public class ProgrammationDynamique {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Morphing morphing = new Morphing("C:\\Users\\Katia\\Desktop\\test.txt");
        morphing.parseTexteVersMot();
//        morphing.extractionMot("C:\\Users\\Katia\\Desktop\\Retourtest.txt"); Pas utile
        System.out.println("Liste :\n" + morphing.listeMot);
        for (int i = 0; i < morphing.listeMot.size(); i++) {
            for (int j = 0; j < morphing.listeMot.size(); j++) {
                System.out.println(morphing.distanceLevenshtein(morphing.listeMot.get(i), morphing.listeMot.get(j)));
            }
        }
    }

}
