/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercice1;

import java.util.Random;

/**
 *
 * @author CARRARA Nicolas
 */
public class VerificationHauteurTreap {
    public static void main(String[] args){
        Treap<Integer> t ;
        Random rand = new Random();
        double sommeLog = 0.0;
        int sommeHauteur = 0;
        for(int j=0;j<100;j++){
        for(int nombreElement = 1;nombreElement<100;nombreElement++){
            t = new Treap<>();
            for(int i = 0;i<nombreElement;i++){
                t.insererClefTreap(rand.nextInt(1000));
            }
            
            double log = Math.log(nombreElement);
            int hauteur = t.computeHauteur();
            sommeLog = sommeLog + log;
            sommeHauteur = sommeHauteur + hauteur;
//            System.out.println("O(log("+nombreElement+")) = O("+log+") = ? "+hauteur);
            
        }
        
            System.out.println("sommeHauteur/sommeLog = "+sommeHauteur+"/"+sommeLog+" = "+sommeHauteur/sommeLog);
            System.out.println("O(hauteur) = "+sommeHauteur/sommeLog+" * nombreElement");
        }
        
    }
}
