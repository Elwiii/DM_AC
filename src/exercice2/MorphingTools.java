/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
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
        int[][] tableauChemin = new int[chaineCharSource.length + 1][chaineCharTarget.length + 1];

        int[] minimum;

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
                minimum = minimum2((levenshtein[i - 1][j] + 1), (levenshtein[i][j - 1] + 1), (levenshtein[i - 1][j - 1] + cost));
                // Correspond a la formule de récurrence de la distance de levenshtein q2 du TD
                levenshtein[i][j] = minimum[MINIMUM];
                tableauChemin[i][j] = minimum[POSITION_MINIMUM];
            }
        }
        System.out.println(toString(tableauChemin));
        System.out.println(toString(levenshtein));
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

    public static int distanceLevenshtein2(String source, String target, List<String> etapeIntermediaire) {
        char[] chaineCharSource = source.toCharArray();
        char[] chaineCharTarget = target.toCharArray();
        int cost; // cost représente si oui ou non 2 caracteres sont "egaux"

        // Matrice de taille (chaineCharSource.length + 1) * (chaineCharTarget.length + 1) q3 du TD
        int[][] levenshtein = new int[chaineCharSource.length + 1][chaineCharTarget.length + 1];

        int[][] tableauChemin = new int[chaineCharSource.length + 1][chaineCharTarget.length + 1];

        int[] minimum;

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

                minimum = minimum2((levenshtein[i - 1][j] + 1), (levenshtein[i][j - 1] + 1), (levenshtein[i - 1][j - 1] + cost));
                // Correspond a la formule de récurrence de la distance de levenshtein q2 du TD
                levenshtein[i][j] = minimum[MINIMUM];
                tableauChemin[i][j] = minimum[POSITION_MINIMUM];
            }
        }
        System.out.println(toString(tableauChemin));
        System.out.println(toString(levenshtein));
        // calcul des étapes intermédiaire
        etapeIntermediaire = getEtapeIntermediare(chaineCharSource, chaineCharTarget, tableauChemin);

        return levenshtein[chaineCharSource.length][chaineCharTarget.length];
    }

    private static List<String> getEtapeIntermediare(char[] source, char[] target, int[][] positionMinimum) {

        class Operation {

            int operateur;
            Character operandeG;
            Character operandeD;

            Operation(int operateur, Character operandeG, Character operandeD) {
                this.operateur = operateur;
                this.operandeG = operandeG;
                this.operandeD = operandeD;
            }

            @Override
            public String toString() {
                String res = "";
                switch (operateur) {
                    case INSERT:
                        res = "I_op_Dte";
                        break;
                    case DELETE:
                        res = "D_op_Gche";
                        break;
                    case SUBSTITUTION:
                        res = "S_op_Dte_en_Gche";
                        break;
                    default:
                        // exception;
                        break;
                }
                return res + "(" + operandeG + ", " + operandeD + ")";
            }
        }

        List<Operation> operations = new ArrayList<>();

        boolean stop = false;
        int i = source.length;
        int j = target.length;
        Character operandeG = null;
        Character operandeD = null;

        while (!stop) {
            //   System.out.println(i + " , " + j);
            operandeG = null;
            operandeD = null;
            if (i - 1 >= 0) {
                operandeG = source[i - 1];
            }
            if (j - 1 >= 0) {
                operandeD = target[j - 1];
            }
            operations.add(0, new Operation(positionMinimum[i][j], operandeG, operandeD));

            switch (positionMinimum[i][j]) {
                case INSERT:
                    // on monte dans le tableau
                    i--;
                    break;
                case DELETE:
                    // on va à gauche
                    j--;
                    break;
                case SUBSTITUTION:
                    // on monte en haut à gauche
                    j--;
                    i--;
                    break;
                default:
                    // exeception;
                    break;
            }
            stop = (i == 0) && (j == 0);
        }

        System.out.println("operation : " + operations);

        List<String> etapes = new ArrayList<>();
//        String sourceString = new String(source);
//        String targetString = new String(target);
//        etapes.add(sourceString.toUpperCase());
        int decalage = 0;
        Operation op = null;
        for (int k = 0; k < operations.size(); k++) {
            op = operations.get(k);
//            String s = null;
            switch (op.operateur) {
                case INSERT:
                    break;
                case DELETE:
                    decalage--;
                    break;
                case SUBSTITUTION:
                    if (!op.operandeD.equals(op.operandeG)) {
                        source[k + decalage] = op.operandeD;
                        etapes.add(new String(source).toLowerCase());
                    } else {
                        //decalage--;
                    }
                    break;
                default:
                //exception
            }
        }
//        etapes.add(etapes.size(), targetString);
        System.out.println(etapes);
        return etapes;
    }

    public static String toString(int[][] tab) {
        String s = "";
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                s = s + " " + tab[i][j];
            }
            s = s + "\n";
        }
        return s;
    }
    private static final int MINIMUM = 0;
    private static final int POSITION_MINIMUM = 1;
    private static final int INSERT = 0;
    private static final int DELETE = 1;
    private static final int SUBSTITUTION = 2;

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return minimum des 3 valeurs
     */
    private static int[] minimum2(int a, int b, int c) {
        int[] res = {a, INSERT};
        if (b < a) {
            res[MINIMUM] = b;
            res[POSITION_MINIMUM] = DELETE;
            if (c < b) {
                res[MINIMUM] = c;
                res[POSITION_MINIMUM] = SUBSTITUTION;
            }
        } else {
            if (c < a) {
                res[MINIMUM] = c;
                res[POSITION_MINIMUM] = SUBSTITUTION;
            }
        }
        return res;

    }
}
