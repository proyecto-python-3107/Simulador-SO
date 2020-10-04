/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Random;

/**
 *
 * @author Sistema
 */
public class Interrupciones {
    private static int InterrupTime;
    private static int TipoInterrupcion;
    private static String NombreDisp;
    private static int Señal;
    private static boolean EstadoSeñal;
    protected DispositivoES dispositivo;
    DispositivoES dispositivoES=new DispositivoES();
    
    public static int getInterrupTime() {
        
        
        return InterrupTime;
    }

    public static void setInterrupTime(int InterrupTime) {
        Interrupciones.InterrupTime = InterrupTime;
    }

    public static int getTipoInterrupcion() {
        return TipoInterrupcion;
    }

    public static void setTipoInterrupcion(int TipoInterrupcion) {
        Interrupciones.TipoInterrupcion = TipoInterrupcion;
    }

    public static int getSeñal() {
        return Señal;
    }

    public static void setSeñal(int Señal) {
        Interrupciones.Señal = Señal;
    }

    public static String getNombreDisp() {
        return NombreDisp;
    }

    public static void setNombreDisp(String NombreDisp) {
        Interrupciones.NombreDisp = NombreDisp;
    }

    public static boolean isEstadoSeñal() {
        return EstadoSeñal;
    }

    public static void setEstadoSeñal(boolean EstadoSeñal) {
        Interrupciones.EstadoSeñal = EstadoSeñal;
    }
    
    public Interrupciones(int i){
        int Rtime=0;
        int Rtipo=0;
        if (i==1) {
            
            Random random1= new Random();
            Rtime= 1+random1.nextInt(7);
            Random random2= new Random();
            Rtipo= 1+random1.nextInt(3);
            DispositivoES dispositivoES=new DispositivoES();
            
        }
        this.InterrupTime=Rtime;
        this.TipoInterrupcion=Rtipo;
        this.NombreDisp=dispositivoES.CargarNombreES(Rtipo);
        
        //System.out.println("TiempoInterrupcion"+Rtime);
        //System.out.println("Tipo"+Rtipo);
        //System.out.println("Tipo"+NombreDisp);
    }


}
