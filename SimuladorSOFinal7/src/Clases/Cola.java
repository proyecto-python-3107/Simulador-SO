package Clases;

import java.util.ArrayList;


public class Cola {
    
    private ArrayList<Proceso> ColaPrincipal; 
    private int CantProcesos; 
    
    // <editor-fold defaultstate="collapsed" desc="constructors" >

    public Cola(int number)
    {
        ColaPrincipal = new ArrayList<Proceso>(number);
        this.CantProcesos = number; 
    }
    

    public Cola(ArrayList<Proceso> list)
    {
        ColaPrincipal = new ArrayList<Proceso>(list.size());
        ColaPrincipal.addAll(list);
    }
    // </editor-fold>
    

    public void Llenar()
    { 
        for(int i=0 ; i<CantProcesos ; i++)
        {
            Proceso temp = new Proceso(i+1); 
            ColaPrincipal.add(i, temp);
        }
    }

    
    public Proceso getProceso(int num)
    {
        return ColaPrincipal.get(num);
    }
    

    public void sacarProceso(int num)
    {
        ColaPrincipal.remove(num);
    }

    public void agregarProceso(Proceso proc)
    {
        ColaPrincipal.add(proc);
    }
    

    public void set(int i , Proceso proc)
    {
        ColaPrincipal.set(i, proc);
    }
    
    public boolean estaVacia()
    {
        return (ColaPrincipal.isEmpty());
    }
    
    
    public int size()
    {
        return ColaPrincipal.size();
    }
    
    
    public void LimpiarCola()
    {
        for(int i =0 ; i< ColaPrincipal.size() ; i++)
        {
           ColaPrincipal.remove(i);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="order queue" >
    
    /**
     * order the jobs inside the queue by arrive time
     */
    public void OrdenarPorLlegada()
    {
        for(int i=0 ; i<ColaPrincipal.size()-1 ; i++)
        {
            for(int j=i+1 ; j<ColaPrincipal.size() ;j++)
            {
                Proceso j1 = ColaPrincipal.get(i);
                Proceso j2 = ColaPrincipal.get(j);
                if(j2.isFirst(j1))
                {
                    ColaPrincipal.set(i, j2);
                    ColaPrincipal.set(j, j1);
                }
            }
        }
    }
    
    /**
     *  order the jobs inside the queue by shortest burst
     */
    public void OrdenarPorCorto()
    {
        for(int i=0 ; i<ColaPrincipal.size()-1 ; i++)
        {
            for(int j=i+1 ; j<ColaPrincipal.size() ;j++)
            {
                Proceso j1 = ColaPrincipal.get(i);
                Proceso j2 = ColaPrincipal.get(j);
                if(j2.isShort(j1))
                {
                    ColaPrincipal.set(i, j2);
                    ColaPrincipal.set(j, j1);
                }
            }
        }
    }
    
    /**
     * order the jobs inside the queue by priority
     */
    public void OrdenarPorPrioridad()
    {
        for(int i=0 ; i<ColaPrincipal.size()-1 ; i++)
        {
            for(int j=i+1 ; j<ColaPrincipal.size() ;j++)
            {
                Proceso j1 = ColaPrincipal.get(i);
                Proceso j2 = ColaPrincipal.get(j);
                if(j2.isPrior(j1))
                {
                    ColaPrincipal.set(i, j2);
                    ColaPrincipal.set(j, j1);
                }
            }
        }
    }
    
    /**
     * order the jobs inside the queue by the shortest remaining time
     */
   
    
    // </editor-fold>
    
    
    public Cola traerCopia ()
    {
        
        return new Cola(ColaPrincipal);
        
    }

    
    public Cola getCopiaLimpia()
    {
        ArrayList<Proceso> list = new ArrayList<Proceso>(ColaPrincipal.size());
        for(int i=0 ; i<ColaPrincipal.size() ; i++)
        {
            Proceso temp = ColaPrincipal.get(i).getClearCopyJob();  
            list.add(temp);
        }
        return new Cola(list);
    }
     
    
    public void mostrarCola(int simulationTime)
    {
        if(ColaPrincipal.isEmpty()){System.out.println("Empty Queue");  return;}
        System.out.println("number of jobs " + ColaPrincipal.size() );
        System.out.println("# "+" Arrive "+" Burst "+" Priority "+" Start "+" Wait "+" Remain "+" Finish "+" Turn "+" % ");
        for(int i=0 ; i<ColaPrincipal.size() ; i++) 
        {
            Proceso temp = ColaPrincipal.get(i);
            System.out.print(temp.ProcesoID + "    " + temp.TiempoLlegada + "      " + temp.burstTime +"       ");
            System.out.print(temp.prioridad + "      " + temp.getStart() + "      " + temp.getWaitTime(simulationTime)+ "      ");
            System.out.print(temp.getRemainTime() + "       " + temp.getFinish() + "      " + temp.getTurnaround(simulationTime)+ "    ");
            System.out.println(temp.getPercent());
        }
    }
}