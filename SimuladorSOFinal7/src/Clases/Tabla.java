package Clases;

import java.util.ArrayList;
import javax.print.attribute.standard.JobStateReason;
import javax.swing.table.AbstractTableModel;

public class Tabla extends AbstractTableModel{

    private Cola tablaCola ; 
    private String[] columnNames = {"IdProceso","ArriveTime","BurstTime","Prioridad","StartTime","WaitingTime","RemainTime","FinishTime","Turnaround Time","Estado","%","Memoria","Interrupciones" ,"Señal"}; // table header
    int CantidadProcesos;
    
    public Tabla( Cola cola)
    {
        tablaCola = cola.traerCopia();
        this.fireTableRowsUpdated(1, 1);
    } 

    @Override
    public int getRowCount() {
        return tablaCola.size(); 
    }

    @Override
    public int getColumnCount() {
        return 14; 
    }
    
    public double getAverageWaiting()
    {  
        double average = 0 ;
        for(int i =0 ; i< tablaCola.size() ; i++)
        {
            average += (Integer) getValueAt(i, 5);
        }
        return (average/tablaCola.size());
    }
    
    
    public double getAverageTurn()
    {  
        double aveg = 0 ;
        for(int i =0 ; i< tablaCola.size() ; i++)
        {
            aveg += (Integer) getValueAt(i, 8);
        }
        return (aveg/tablaCola.size());
    } 

    public double getCantFinalizados()
    {  
        int CantF = 0 ;
        for(int i =0 ; i< tablaCola.size() ; i++)
        {
            String State= (String) getValueAt(i, 9);
            if(State.equals("Finalizado")){
                CantF++;
            }
        }
        return CantF;
        
    } 
    
    public int getTotalBurstTime()
    {  
        int CantBT = 0 ;
        for(int i =0 ; i< tablaCola.size() ; i++)
        {
            int bt= (Integer) getValueAt(i, 2);
                CantBT=CantBT+bt;
        }
        return CantBT;
        
    } 
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proceso job = tablaCola.getProceso(rowIndex);
        switch(columnIndex)
        {
            case 0 : return job.ProcesoID;
            case 1 : return job.TiempoLlegada;
            case 2 : return job.burstTime;
            case 3 : return job.state;
            case 4 : return job.getStart();
            case 5 : return job.getWaitTime(Schedule.Tiempo);    
            case 6 : return job.getRemainTime();
            case 7 : return job.getFinish();
            case 8 : return job.getTurnaround(Schedule.Tiempo);
            case 9 : return job.getEstado();
            case 10 : return job.getPercent();
            case 11 : return job.getMemoria()+" MB";
            case 12 : return job.getInterrupcion(Schedule.Tiempo);
            case 13 : return job.getSeñalP(Schedule.Tiempo);
            default: return 0;
            
        }
    }
    

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
    

    public void setValueAT(Proceso other)
    {
        int n = other.ProcesoID;
        for(int i=0 ; i<tablaCola.size() ; i++)
        {
            if(tablaCola.getProceso(i).ProcesoID == n)
            {
                tablaCola.set(i, other);
                return;
            }
        }
    }
    
    private ArrayList<Proceso>arreglo;
    public int getCantidadProcesos() {
        return CantidadProcesos;
    }

    public void setCantidadProcesos(int CantidadProcesos) {
        this.CantidadProcesos = CantidadProcesos;
    }

    public ArrayList<Proceso> getArreglo() {
        return arreglo;
    }

    public void setArreglo(ArrayList<Proceso> arreglo) {
        this.arreglo = arreglo;
    }

    public Tabla(){
        arreglo=new ArrayList<Proceso>();
    }       
    
    public void adicionar(Proceso p){
        arreglo.add(p);
    }
    public Proceso obtener(int posicion){
        return arreglo.get(posicion);
    }
    public int tamano(){
        return arreglo.size(); 
    }
    
}
