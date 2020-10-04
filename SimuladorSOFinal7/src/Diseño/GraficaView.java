package Diseño;

import Diseño.Celdas;
import Clases.Proceso;
import Clases.Cola;
import java.util.ArrayList;


public class GraficaView {
    private static int readyX = 400; 
    private static final int readyY = 345; 
    public static ArrayList<Celdas> List = new ArrayList<Celdas>(20); 

    public static void update(Cola jobList){
        
        for(int i=0 ; i<jobList.size() ; i++)
        {
            Proceso temp = jobList.getProceso(i);
            List.add(Celdas.createReadyQueueCell(readyX, readyY, temp.ProcesoID));
            readyX += 30; 
        }
    }
    

    public static void clear(){
         List.clear();
         readyX = 400; 
     }
}
