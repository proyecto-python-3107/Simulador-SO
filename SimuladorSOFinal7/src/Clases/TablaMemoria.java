package Clases;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TablaMemoria extends AbstractTableModel{

    private Cola tablaCola ; 
    private String[] columnNames = {"IdProceso","Nodo Inicial","Nodo Final","Tamaño" }; // table header
    int CantidadProcesos;
    
    public TablaMemoria( Cola cola)
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
        return 4; 
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proceso job = tablaCola.getProceso(rowIndex);
        switch(columnIndex)
        {
            case 0 : return job.ProcesoID;
            case 1 : return job.getNodoAsignadoInicio();
            case 2 : return job.getNodoAsignadoFin();
            case 3 : return job.getMemoria()+" MB";
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

    public TablaMemoria(){
        arreglo=new ArrayList<Proceso>();
    }       
    
}
