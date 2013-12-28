/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author CHAYEM Samy & CARRARA Nicolas
 */
public class Morphing {

    protected String cheminFichierEntre;
    protected List<String> listeMot; // List contenant l'extraction des mots d'un fichier
    protected int[][] levenshtein; // matrice
    protected int distanceLevenshtein;

    /**
     *
     * @param cheminFichierEntre Path du fichier contenant le texte a parsé
     */
    public Morphing(String cheminFichierEntre) {
        this.cheminFichierEntre = cheminFichierEntre;
        this.listeMot = new ArrayList<>();
    }

    /**
     * Methode qui réalise le parsage(extraction) de tous les mots du fichier
     * donné en paramétre du Constructeur On utilise la classe Scanner, comme
     * Java nous le préconise (StringTokenizer étant inusité)
     *
     * Problème avec les REGEX, donc pour le moment seul le separateur "espace"
     * est pris en compte
     */
    public void parseTexteVersMot() {
        try {
            // pattern qui permet avec une regex de récuperer tous les mots entre les différents séparateur
//            Pattern pattern = Pattern.compile("\\s*.\\s*"); 
            // seul le separateur "espace" est pris en compte (par défaut)
            Scanner scanner = new Scanner(new FileReader(cheminFichierEntre));
            String mot = null;
            while (scanner.hasNext()) {
                mot = scanner.next().replaceAll("[!,.?\":\\[\\]\\{\\}]", " ");
                String[] mots = mot.split(" ");
                for (int i = 0; i < mots.length; i++) {
                    if (!mots[i].equals("")) {
                        listeMot.add(mots[i]);
                        System.out.println(mots[i]);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Morphing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    /**
//     * Methode qui ecrit dans un fichier la liste des mots extraits du fichier passé en entrée
//     * Pas demandé
//     * @param cheminFichierSorti
//     */
//    public void extractionMot(String cheminFichierSorti) {
//        PrintWriter printWriter = null;
//        try {
//            printWriter = new PrintWriter(new FileWriter(cheminFichierSorti));
//            for (int i = 0; i < this.listeMot.size(); i++) {
//                printWriter.println(this.listeMot.get(i));
//                printWriter.flush();
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Morphing.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            printWriter.close();
//        }
//    }
    /**
     * Methode qui calcule la distance de levenshtein entre 2 mots. Algorithme
     * wikipedia
     *
     * Le passage en tableau de caractere n'est pas obligatoire mais je prefere
     * comme cela. On utilise des tableaux, donc autant en faire de même pour
     * nos strings ^^.
     *
     * @param source
     * @param target
     * @return la distance de levenshtein entre les 2 mots, sous la forme d'un
     * string
     */
    public String distanceLevenshtein(String source, String target) {
        char[] chaineCharSource = source.toCharArray();
        char[] chaineCharTarget = target.toCharArray();
        int cost = 0; // cost représente si oui ou non 2 caracteres sont "egaux"

        // Matrice de taille (chaineCharSource.length + 1) * (chaineCharTarget.length + 1) q3 du TD
        levenshtein = new int[chaineCharSource.length + 1][chaineCharTarget.length + 1];

        for (int i = 0; i < chaineCharSource.length + 1; i++) {
            levenshtein[i][0] = i;
        }
        for (int j = 0; j < chaineCharTarget.length + 1; j++) {
            levenshtein[0][j] = j;
        }

        // on commence ici à 1 afin d'éviter une exception d'index : 0-1 = -1 --> erreur.
        // C'est pour cela qu'on pose i-1 et j-1 dans la condition
        for (int i = 1; i < chaineCharSource.length + 1; i++) {
            for (int j = 1; j < chaineCharTarget.length + 1; j++) {
                if (chaineCharSource[i - 1] == chaineCharTarget[j - 1]) {
                    cost = 0; // pas de coût puisque les 2 caracteres sont egaux
                } else {
                    cost = 1; // coût puisque les 2 caracteres sont différents
                }
                // Correspond a la formule de récurrence de la distance de levenshtein q2 du TD
                levenshtein[i][j] = minimum((levenshtein[i - 1][j] + 1), (levenshtein[i][j - 1] + 1), (levenshtein[i - 1][j - 1] + cost));
            }
        }
        return "<" + source + ", " + target + ">, la distance de Levenshtein = " + levenshtein[chaineCharSource.length][chaineCharTarget.length];
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return minimum des 3 valeurs
     */
    public int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
