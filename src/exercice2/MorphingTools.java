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

/**
 *
 * @author CARRARA Nicolas et CHAYEM Samy
 */
public class MorphingTools {

    /**
     * Methode qui réalise le parsage(extraction) de tous les mots du fichier
     * donné en paramétre du Constructeur On utilise la classe Scanner, comme
     * Java nous le préconise (StringTokenizer étant inusité). C'est un peu un
     * workaround pour le moment, il y a surement une manière de faire plus
     * propre mais on maitrise pas assez les regexs et scanner.
     *
     * @param pathInput
     * @return
     */
    public static List<String> parseTexteVersMot(String pathInput) {
        List<String> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileReader(pathInput));
            String mot;
            while (scanner.hasNext()) {
                mot = scanner.next().replaceAll("[!,.;?\":\\[\\]\\{\\}]", " ");
                String[] mots = mot.split(" ");
                for (String mot1 : mots) {
                    if (!mot1.equals("")) {
                        result.add(mot1);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MorphingTools.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

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
    public static int distanceLevenshtein(String source, String target) {
        char[] chaineCharSource = source.toCharArray();
        char[] chaineCharTarget = target.toCharArray();
        int cost; // cost représente si oui ou non 2 caracteres sont "egaux"

        // Matrice de taille (chaineCharSource.length + 1) * (chaineCharTarget.length + 1) q3 du TD
        int[][] levenshtein = new int[chaineCharSource.length + 1][chaineCharTarget.length + 1];

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
        return levenshtein[chaineCharSource.length][chaineCharTarget.length];
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return minimum des 3 valeurs
     */
    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

}
