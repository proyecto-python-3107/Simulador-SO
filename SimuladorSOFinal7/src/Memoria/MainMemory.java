/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memoria;

/**
 *
 * @author joao
 */
public class MainMemory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       InfoNodo a1= new InfoNodo("H",0,12288);
       InfoNodo a2= new InfoNodo("S",12288,4096);
       MemoriaPartida mem= new MemoriaPartida();
       mem.insertar(a1);
       mem.insertar(a2);
       mem.imprimir();
       
    }
    
}
