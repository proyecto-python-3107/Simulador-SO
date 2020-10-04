package Clases;

import Clases.Dispatcher;
import AlgoritmoPlanificador.FCFS;
import AlgoritmoPlanificador.Schedule2;
import AlgoritmoPlanificador.RR;
import AlgoritmoPlanificador.SJF;
import Clases.Proceso;
import Clases.Cola;

public class Schedule {
 
    private static Schedule2 algoritmo; 
    public static int Tiempo;   
    public static String TipoAlgoritmo = "FCFS";  
    public static String TipoAsignacion= "FirstFit";  
    public static int Quantum; 
    public static boolean Finished = false; 
    public static boolean Stoped = true; 

    public static int getQuantum() {
        return Quantum;
    }

    public static void setQuantum(int Quantum) {
        Schedule.Quantum = Quantum;
    }

    public static void reset()
    {
       Tiempo = 0;  
       Finished = false;  
    }

    public static Cola getColaListos(){
        return algoritmo.getColaListos();
    }

    public static Proceso PasoTrabajo(){
        Proceso job, job2;
        if(Tiempo == 0) {seleccionarAlgoritmo();} 
        job = algoritmo.nextStep(Tiempo); 
        job2 = algoritmo.nextStep2(Tiempo); 
        if(algoritmo.estaFinalizado()){Finished = true;} 
        return job;
    }

    
    public static Cola getColaBloqueado()
    {
        return algoritmo.getColaBloqueado();
    }


    public static String getTipoAsignacion() {
        return TipoAsignacion;
    }

    public static void setTipoAsignacion(String TipoAsignacion) {
        Schedule.TipoAsignacion = TipoAsignacion;
    }

    
    private static void seleccionarAlgoritmo()
    {
        if(TipoAlgoritmo.equals("FCFS")) {algoritmo = new FCFS(Dispatcher.traer());} 
        else if(TipoAlgoritmo.equals("SJF")) {algoritmo = new SJF(Dispatcher.traer());} 
        else if(TipoAlgoritmo.equals("RoundRobin")) {algoritmo = new RR(Dispatcher.traer() , Quantum);} 
    }
    
    
    
    
}
