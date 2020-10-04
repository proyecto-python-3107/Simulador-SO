package AlgoritmoPlanificador;

import Clases.Proceso;
import Clases.Cola;
import javax.swing.JOptionPane;


public class SJF extends Schedule2{

    public SJF(Cola workQueue)
    {
        super(workQueue);
    }
    

    @Override
    public Proceso nextStep (int simulationTime)
    {
        cargarColaListos(simulationTime); 
        
        if(ColaListos.size() > 1) { ColaListos.OrdenarPorCorto();} 
        if(!parametro)   
        {
            System.out.println("Proceso Actual "+ProcesoActual.getProcesoID()+" - "+ProcesoActual.estado);
            /*if(simulationTime!=0 && ProcesoActual.getRemainTime() !=0 && ProcesoActual.getEstado()!="Bloqueado" ){ 
            ColaListos.agregarProceso(ProcesoActual); 
            }*/
            
            if(ColaListos.estaVacia()) {return null;}   
            parametro = true;
            setProcesoActual();  
        }
        return trabCPU(simulationTime); 
    }
    public Proceso nextStep2(int simulationTime) {
        cargarColaListos(simulationTime);
        if(!parametroI){
            if (ColaBloqueado.estaVacia()) {
                return null;
            } else{
                
            }
            parametroI=true;
            setProcesoBloqueado();
            System.out.println("Next "+ProcesoBloqueado.getProcesoID()+" - "+ProcesoBloqueado.estado);
        }
        return trabES(simulationTime);
    }

    @Override
    protected Proceso trabCPU(int simulationTime)
    {
        ProcesoActual.procTrabajado(simulationTime);
        ProcesoActual.setEstado("En ejecucion");
        if(ProcesoActual.getInterrupcion(simulationTime)==1) {
            ProcesoActual.setEstado("Bloqueado");
            lista.agregarProceso(ProcesoActual);
            //parametro = false;
        }
        if(ProcesoActual.getRemainTime()==0 || ProcesoActual.getEstado().equals("Bloqueado")) {
            parametro=false;
        } 
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
