/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author CHAYEM Samy
 */
public class Morphing {

    protected String cheminFichierEntre;
    protected List<String> listeMot;
    protected int[][] levenshtein;
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
     * Compliqué les REGEX
     */
    public void parseTexteVersMot() {
        try {
            Pattern pattern = Pattern.compile("[^\\ \\.\\*\\?\\(\\)\\[\\]\\{\\}\\^\\$\\|\\+]");
            Scanner scanner = new Scanner(new FileReader(this.cheminFichierEntre)).useDelimiter(" |, |; |# |! |^ |$ |( |) |[ |] | .");
            String mot = null;
            while (scanner.hasNext()) {
                mot = scanner.next();
                listeMot.add(mot);
                System.out.println(mot);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Morphing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Pas sur que ce soit vraiment demandé
     *
     * @param cheminFichierSorti
     */
    public void extractionMot(String cheminFichierSorti) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(cheminFichierEntre));
            for (int i = 0; i < this.listeMot.size(); i++) {
                printWriter.println(this.listeMot.get(i));
                printWriter.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Morphing.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            printWriter.close();
        }

    }

    /**
     * Le passage en tableau de caractere n'est pas obligatoire mais je prefere
     * comme cela. On utilise des tableaux, donc autant en faire de même pour
     * nos strings ^^.
     *
     * @param source
     * @param target
     * @return
     */
    public String distanceLevenshtein(String source, String target) {
        char[] chaineCharSource = source.toCharArray();
        char[] chaineCharTarget = target.toCharArray();
        int cost = 0;
        levenshtein = new int[chaineCharSource.length + 1][chaineCharTarget.length + 1];
        for (int i = 0; i < chaineCharSource.length + 1; i++) {
            levenshtein[i][0] = i;
        }
        for (int j = 0; j < chaineCharTarget.length + 1; j++) {
            levenshtein[0][j] = j;
        }

        // on commence ici à 1 afin d'éviter une exception d'index : 0-1 = -1 --> erreur.
        // C'est pour cela qu'on pose i-1 et j-1 dans la 1ere condition
        for (int i = 1; i < chaineCharSource.length + 1; i++) {
            for (int j = 1; j < chaineCharTarget.length + 1; j++) {
                if (chaineCharSource[i - 1] == chaineCharTarget[j - 1]) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                levenshtein[i][j] = minimum((levenshtein[i - 1][j] + 1), (levenshtein[i][j - 1] + 1), (levenshtein[i - 1][j - 1] + cost));
            }

        }
        return "Distance de Levenshtein : " + source + " et " + target + " est de : " + levenshtein[chaineCharSource.length][chaineCharTarget.length];
    }

    public int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
