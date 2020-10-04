package Clases;

import Clases.Cola;

/**
 ** 
 * 
 * 
 * 
 * 
 * La cola principal es la lista principal de trabajos en la simulación, esta clase se usa
 * para mantener la cola principal y una cola temporal que se usa para guardar una copia del
 * cola principal que se utilizará cuando el usuario desee reiniciar la misma simulación con
 * los mismos datos de trabajos.
 */
public class Dispatcher {
 
    private static Cola ColaListo;  // lista principal de trabajos de la simulación
    private static Cola ColaEspera;  // copia temporal de la lista principal para reiniciar la simulación
    

    public static Cola crearNuevaCola(int numTrabajos){
        ColaListo = new Cola(numTrabajos);
        ColaListo.Llenar(); 
        ColaEspera = ColaListo.getCopiaLimpia(); 
        return ColaListo;
    }

    public static void Limpiar()
    {
        ColaListo = ColaEspera.getCopiaLimpia();
    }

    public static void añadir(Cola lista)
    {
        ColaListo = lista.getCopiaLimpia();
        ColaEspera = ColaListo.getCopiaLimpia();
    }

    public static Cola traer(){
        return ColaListo.traerCopia();
    }
    
}
