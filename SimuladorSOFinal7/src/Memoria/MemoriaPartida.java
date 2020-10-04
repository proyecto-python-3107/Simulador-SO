/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memoria;

import Clases.Proceso;


/**
 *
 * @author joao
 */
public class MemoriaPartida {
    class Nodo{
     InfoNodo info;
     Nodo sig;
     
    }
    private Nodo raiz;
    public MemoriaPartida(){
        raiz=null;
    }
    public void insertar(InfoNodo inserta){
        Nodo nuevoNodo= new Nodo();
        nuevoNodo.info=inserta;
        if (raiz==null){
            nuevoNodo.sig=null;
            raiz=nuevoNodo;
        }
        else {
            nuevoNodo.sig=raiz;
            raiz=nuevoNodo;
            
        }
    }
    public void imprimir(){
        Nodo recorrer= raiz;
        System.out.println("Lista: ");
        while (recorrer!= null){
            System.out.println(recorrer.info.getTipo_nodo()+" - "+recorrer.info.getPunto_inicio()+" - "+recorrer.info.getLongitud_nodo());
            recorrer=recorrer.sig;
            
        }
        System.out.println();
    }
    
    public void FirstFit(Proceso proceso){
        Nodo recorre=raiz;
        Nodo respaldo1= new Nodo();
        Nodo respaldo2= new Nodo();
        boolean hitprimera=false;
        while (recorre!=null && hitprimera==false){
            if (recorre.info.getTipo_nodo()=="H"){
                respaldo1.info=recorre.info;
                respaldo2=recorre.sig;
                recorre.info.setTipo_nodo(Integer.toString(proceso.getProcesoID()));
                recorre.info.setPunto_inicio(respaldo1.info.getPunto_inicio());
                recorre.info.setLongitud_nodo(4*proceso.getMemoria());
                recorre.sig=respaldo1;
                respaldo1.info.setPunto_inicio(recorre.info.getPunto_inicio()+4*proceso.getMemoria());
                respaldo1.info.setLongitud_nodo(respaldo1.info.getLongitud_nodo()-4*proceso.getMemoria());
                respaldo1.sig=respaldo2;
                hitprimera=true;
                
            }
            else{
                recorre=recorre.sig;
            }
            
    }
        
    }
    


    public void BestFit(Proceso proceso){
        Nodo respaldo1= new Nodo();
        Nodo respaldo2= new Nodo();
        int menor_valor= raiz.info.getLongitud_nodo();
        Nodo recorre= raiz;
        while (recorre!= null){
            if (recorre.info.getLongitud_nodo()<=menor_valor && recorre.info.getTipo_nodo()=="H"){
                menor_valor=recorre.info.getLongitud_nodo();
            }
            recorre=recorre.sig;
        }
        Nodo recorre2=raiz;
        while(recorre2!=null){
            if (recorre2.info.getLongitud_nodo()==menor_valor && recorre2.info.getTipo_nodo()=="H"){
                respaldo1.info=recorre2.info;
                respaldo2=recorre2.sig;
                recorre2.info.setTipo_nodo(Integer.toString(proceso.getProcesoID()));
                recorre2.info.setPunto_inicio(respaldo1.info.getPunto_inicio());
                recorre2.info.setLongitud_nodo(4*proceso.getMemoria());
                recorre2.sig=respaldo1;
                respaldo1.info.setPunto_inicio(recorre2.info.getPunto_inicio()+4*proceso.getMemoria());
                respaldo1.info.setLongitud_nodo(respaldo1.info.getLongitud_nodo()-4*proceso.getMemoria());
                respaldo1.sig=respaldo2;
                
            }
        }
    }
    public void WorstFit(Proceso proceso){
         Nodo respaldo1=new Nodo();
        Nodo respaldo2 = new Nodo();
        int mayor_valor= raiz.info.getLongitud_nodo();
        Nodo recorre= raiz;
        while (recorre!= null){
            if (recorre.info.getLongitud_nodo()>=mayor_valor && recorre.info.getTipo_nodo()=="H"){
                mayor_valor=recorre.info.getLongitud_nodo();
            }
            recorre=recorre.sig;
        }
        Nodo recorre2=raiz;
        while(recorre2!=null){
            if (recorre2.info.getLongitud_nodo()==mayor_valor && recorre2.info.getTipo_nodo()=="H"){
                respaldo1.info=recorre2.info;
                respaldo2=recorre2.sig;
                recorre2.info.setTipo_nodo(Integer.toString(proceso.getProcesoID()));
                recorre2.info.setPunto_inicio(respaldo1.info.getPunto_inicio());
                recorre2.info.setLongitud_nodo(4*proceso.getMemoria());
                recorre2.sig=respaldo1;
                respaldo1.info.setPunto_inicio(recorre2.info.getPunto_inicio()+4*proceso.getMemoria());
                respaldo1.info.setLongitud_nodo(respaldo1.info.getLongitud_nodo()-4*proceso.getMemoria());
                respaldo1.sig=respaldo2;
                
            }
         
     }
        
        
        
    }
    public float Espacio_Memoria(){
        Nodo recorrer=raiz;
        float suma=0;
        while(recorrer.sig!=null && recorrer.info.getTipo_nodo()=="H"){
            suma= suma+ recorrer.info.getLongitud_nodo();
            recorrer=recorrer.sig;
            
        }
        return suma/4096;
        
    }
    
    
    public void LiberarHueco(Proceso proceso){
        String buscar=Integer.toString(proceso.getProcesoID());
        Nodo recorrer=raiz;
        while(recorrer.sig!=null){
            if (recorrer.info.getTipo_nodo().equals(buscar)){
                recorrer.info.setTipo_nodo(buscar);
            }
            recorrer=recorrer.sig;
        }
            
        
        
        
    }
}
