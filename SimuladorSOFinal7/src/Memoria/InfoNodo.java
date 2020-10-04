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
public class InfoNodo {
    private String tipo_nodo;
    private int punto_inicio;
    private int longitud_nodo;
    

    public InfoNodo(String tipo_nodo, int punto_inicio, int longitud_nodo) {
        this.tipo_nodo = tipo_nodo;
        this.punto_inicio = punto_inicio;
        this.longitud_nodo = longitud_nodo;
    }

    public String getTipo_nodo() {
        return tipo_nodo;
    }

    public void setTipo_nodo(String tipo_nodo) {
        this.tipo_nodo = tipo_nodo;
    }

    public int getPunto_inicio() {
        return punto_inicio;
    }

    public void setPunto_inicio(int punto_inicio) {
        this.punto_inicio = punto_inicio;
    }

    public int getLongitud_nodo() {
        return longitud_nodo;
    }

    public void setLongitud_nodo(int longitud_nodo) {
        this.longitud_nodo = longitud_nodo;
    }
    
}
