/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercice1;

/**
 *
 * @author CARRARA Nicolas
 */
public class VerificationArbre {
    public static void main(String[] args){
        ArbreBinaireCartesien<Integer> abc = new ArbreBinaireCartesien<>();
//         arbre du TD
        abc.insererClef(5, 6);
        abc.insererClef(13, 7);
//        abc.insererClef(11, 9);
//        abc.insererClef(9, 10);
        abc.insererClef(2, 3);
                abc.insererClef(10, 8);

//        abc.insererClef(6,7);
        abc.insererClef(7, 1);
        abc.insererClef(17, 0);
        System.out.println("abc : " + abc);
        
        Treap<Integer> t = new Treap<>();
        t.insererClefTreap(10);
        t.insererClefTreap(5);
        t.insererClefTreap(13);
        t.insererClefTreap(2);
        t.insererClefTreap(7);
        t.insererClefTreap(17);
        System.out.println("t : " + t);
        
        ArbreBinaireCartesien<Integer> abc2 = new ArbreBinaireCartesien<>();
        
        System.out.println("insertion de (10,8)");
        abc2.insererClef(10, 8);
        System.out.println("abc2 : " + abc2);
        
        System.out.println("insertion de (5,9)");
        abc2.insererClef(5, 9);
        System.out.println("abc2 : " + abc2);
        
        System.out.println("insertion de (13,10)");
        abc2.insererClef(13, 10);
        System.out.println("abc2 : " + abc2);
        
        System.out.println("insertion de (2,11)");
        abc2.insererClef(2, 11);
        System.out.println("abc2 : " + abc2);
        abc2.insererClef(7, 12);
        System.out.println("abc2 : " + abc2);
        abc2.insererClef(17, 13);
        System.out.println("abc2 : " + abc2);
        
        
    }
}
