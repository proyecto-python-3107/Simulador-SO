package Clases;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PCB extends AbstractTableModel{

    private Cola tablaCola ; 
    private String[] columnNames = {"IdProceso","PC","Estado","Memoria","Interrupciones" }; // table header
    int CantidadProcesos;
    
    public PCB( Cola cola)
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
        return 5; 
    }
    public double getCantInterrupcionesTotal()
    {  
        double CantInterr = 0 ;
        for(int i =0 ; i< tablaCola.size() ; i++)
        {
            CantInterr+= (Integer) getValueAt(i, 4);
        }
        return CantInterr;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proceso job = tablaCola.getProceso(rowIndex);
        switch(columnIndex)
        {
            case 0 : return job.ProcesoID;
            case 1 : 
                int PC=job.getPercent()*job.getPC();
                return PC;
            case 2 : return job.getEstado();
            case 3 : return job.getMemoria()+" MB";
            case 4 : return job.getCantInterrupciones();
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

    public PCB(){
        arreglo=new ArrayList<Proceso>();
    }       
    
}
