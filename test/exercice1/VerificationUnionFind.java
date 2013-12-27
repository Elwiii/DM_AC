/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercice1;

import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author CARRARA Nicolas
 */
public class VerificationUnionFind {
    
    public static void main(String[] args){
        try {
            UnionFind<Integer> uf = new UnionFind<>();
//            uf.makeset(1);
//            uf.makeset(3);
//            uf.makeset(4);
//            uf.makeset(7);
//            uf.makeset(2);
//            uf.makeset(5);
//            uf.makeset(6);
//            uf.makeset(8);
//            
//            System.out.println("uf : "+uf);
//            uf.makeset(8);
//            System.out.println("uf : "+uf);
//            System.out.println("uf.find(8) : "+uf.find(8));
//            System.out.println("uf.find(10) : "+uf.find(10));
//            
//            System.out.println("uf.union(1, 3) : "+uf.union(1, 3));
//            System.out.println("uf : "+uf);
//            System.out.println("uf.union(3, 4) : "+uf.union(3, 4));
//            System.out.println("uf : "+uf);
//            System.out.println("uf.union(7, 3) : "+uf.union(7, 3));
//            System.out.println("uf : "+uf);
//            uf.union(2, 6);
//            uf.union(2, 5);
//            uf.union(8, 5);
//            System.out.println("uf : "+uf);
//            
//            uf.union(5, 7);
//            System.out.println("uf : "+uf);
//            
//            System.out.println("uf.union(5, null) : "+uf.union(5, null));
            
            // test compression
            uf = new UnionFind<>();
            uf.makeset(1);
            uf.makeset(3);
            uf.makeset(4);
            uf.makeset(7);
            uf.makeset(2);
            uf.makeset(5);
            uf.makeset(6);
            uf.makeset(8);
//            System.out.println("uf : "+uf);
//            uf.makeset(8);
//            System.out.println("uf : "+uf);
//            System.out.println("uf.find(8) : "+uf.find(8,UnionFind.COMPRESSION));
//            System.out.println("uf.find(10) : "+uf.find(10,UnionFind.COMPRESSION));
//            
            System.out.println("uf.union(1, 3) : "+uf.union(1, 3,UnionFind.COMPRESSION));
            System.out.println("uf : "+uf);
            System.out.println("uf.union(3, 4) : "+uf.union(3, 4,UnionFind.COMPRESSION));
            System.out.println("uf : "+uf);
            System.out.println("uf.union(7, 3) : "+uf.union(7, 3,UnionFind.COMPRESSION));
            System.out.println("uf : "+uf);
//            uf.union(2, 6,UnionFind.COMPRESSION);
//            uf.union(2, 5,UnionFind.COMPRESSION);
//            uf.union(8, 5,UnionFind.COMPRESSION);
//            System.out.println("uf : "+uf);
//            
//            uf.union(5, 7,UnionFind.COMPRESSION);
//            System.out.println("uf : "+uf);
//            
            System.out.println("Flag end test");
        } catch (Exercice1Exception ex) {
            Logger.getLogger(VerificationUnionFind.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
