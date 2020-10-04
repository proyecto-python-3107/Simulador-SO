/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Sistema
 */
public class DispositivoES {
    public int DispositivoID;    
    public String Nombre;

    public int getDispositivoID() {
        return DispositivoID;
    }

    public void setDispositivoID(int DispositivoID) {
        this.DispositivoID = DispositivoID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String CargarNombreES(int tipo){
        switch(tipo){
            case 1: return this.Nombre="Impresora";
            case 2: return this.Nombre="Teclado";
            case 3: return this.Nombre="Discc";
            default: return null;
        }
    }
}
