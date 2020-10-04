package Diseño;

import Diseño.Celdas;
import Clases.Proceso;
import java.util.ArrayList;


public class Grafica {

    
    private static int ganttX =380 ; 
    private static final int ganttY = 85;
    private static int ganttLastJob = 0;
    public static ArrayList<Celdas> List = new ArrayList<Celdas>(100); 

    public static void addJob(Proceso job , int time){
       
       Celdas celda ;
       if(job == null) 
       {celda = Celdas.createEmptyJobCell(ganttX, ganttY);} 
       else 
       {
            if(job.ProcesoID != ganttLastJob)
            {
                ganttX += 2;
                ganttLastJob = job.ProcesoID;
            }
            celda = Celdas.createGanttCell(ganttX, ganttY, job.ProcesoID); 
       }
       ganttX += 25; 
       List.add(celda); 
       if( (time+1) % 10 == 0 ) 
       {
           List.add(Celdas.createMark(ganttX -1 , ganttY+80)); 
       }
    }

    public static void clear(){
         List.clear();
         ganttX = 420; 
         ganttLastJob = 0; 
     }
}
