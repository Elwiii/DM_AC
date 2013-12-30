/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercice2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikolai
 */
public class VerificationGraphe {
    public static void main(String[] args){
        String[] vertex = {"A","B","C"};
        Integer[][] edges = {{null,1,0},
                             {null,4,0},
                             {1,6,null}};
        
        try {
            Graphe<String> g = new Graphe(vertex,edges);
            System.out.println("g : "+g);
        } catch (Exercice2Exception ex) {
            Logger.getLogger(VerificationGraphe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
