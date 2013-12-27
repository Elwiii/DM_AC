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
public class VerificationUnionFind {
    
    public static void main(String[] args){
        UnionFind<Integer> uf = new UnionFind<>();
        System.out.println("uf : "+uf);
        uf.makeset(1);
        System.out.println("uf : "+uf);
        uf.makeset(3);
        uf.makeset(4);
        uf.makeset(7);
        uf.makeset(2);
        uf.makeset(5);
        uf.makeset(6);
        uf.makeset(8);
        System.out.println("uf : "+uf);
        uf.makeset(8);
        System.out.println("uf : "+uf);
        System.out.println("uf.find(8) : "+uf.find(8));
        System.out.println("uf.find(10) : "+uf.find(10));
    }
    
}
