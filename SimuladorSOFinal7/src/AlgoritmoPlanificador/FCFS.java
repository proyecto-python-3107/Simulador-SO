package AlgoritmoPlanificador;

import Clases.Proceso;
import Clases.Cola;
import Memoria.InfoNodo;
import Memoria.MemoriaPartida;
import javax.swing.JOptionPane;


public class FCFS extends Schedule2{

    
    public FCFS(Cola ColaTrabajo )
    {  
        super(ColaTrabajo); 
    }

    @Override
    public Proceso nextStep (int tiempoSimulacion){
        
        cargarColaListos(tiempoSimulacion);    
        
        if(!parametro ){
            if(ColaListos.estaVacia()) {
                return null;
            } 
            parametro = true; 
            setProcesoActual(); 
            
        }
        return trabCPU(tiempoSimulacion);
    }

    @Override
    public Proceso nextStep2(int simulationTime) {
        cargarColaListos(simulationTime);
        
        if(!parametroI){
            if (ColaBloqueado.estaVacia()) {
                return null;
            } else{
                
            }
            parametroI=true;
            setProcesoBloqueado();
            //System.out.println("Next "+ProcesoBloqueado.getProcesoID()+" - "+ProcesoBloqueado.estado);
        }
        return trabES(simulationTime);
        
    }
    
    @Override
    protected Proceso trabCPU(int tiempoSimulacion){
        /*if(ProcesoActual.getEstado().equals("Bloqueado")){ 
            System.out.println("proceso actual a bloqueado"+ProcesoActual.getProcesoID()+" --- "+ProcesoActual.getBurstTime());
            lista.agregarProceso(ProcesoActual);  // si el trabajo no está terminado, agréguelo a la cola lista nuevamente
            parametro=false;
        }*/
        ProcesoActual.setEstado("En ejecucion");
        
        ProcesoActual.procTrabajado(tiempoSimulacion); 
        
        if(ProcesoActual.getInterrupcion(tiempoSimulacion)==1) {
            ProcesoActual.setEstado("Bloqueado");
            lista.agregarProceso(ProcesoActual);
        } 
        if(ProcesoActual.getRemainTime()==0 || ProcesoActual.getEstado().equals("Bloqueado")) {
            parametro=false;
            /*MemoriaPartida mem= new MemoriaPartida();
                     
                     mem.LiberarHueco(ProcesoActual);
                     mem.imprimir();*/
        } 
        //System.out.println("trab"+ProcesoActual.getEstado()+" "+ProcesoActual.getProcesoID());
        return ProcesoActual; 
    }


    @Override
    protected Proceso trabES(int tiempoSimulacion)
    {
        //ProcesoBloqueado.stateI=false;
        ProcesoBloqueado.jobWorkedES(tiempoSimulacion); 
        if(ProcesoBloqueado.getTiempoRestanteBloq()==0) {
            ProcesoBloqueado.setEstado("Listo");
            lista.agregarProceso(ProcesoBloqueado);
            //ProcesoBloqueado.stateI=true;
            parametroI=false;
            } 
        return ProcesoBloqueado; 
        
    }
   
   
}
