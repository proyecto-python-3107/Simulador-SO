package AlgoritmoPlanificador;

import Clases.Proceso;
import Clases.Cola;
import javax.swing.JOptionPane;

public class RR extends Schedule2{
    
    private int Quantum=2; 
    private int tiempoProcesado; 
    

    public RR(Cola workQueue , int quantum)
    {
        super(workQueue); 
        this.Quantum = quantum;
    }
    
  
    @Override
    public Proceso nextStep (int tiempoSimulacion){
        cargarColaListos(tiempoSimulacion); 
        if(!parametro) //si la CPU no está procesando un trabajo (RR es un algoritmo no preventivo)
        {
            System.out.println("Proceso Actual "+ProcesoActual.getProcesoID()+" - "+ProcesoActual.estado+" "+ProcesoActual.getRemainTime());
            if(tiempoSimulacion!=0 && ProcesoActual.getRemainTime()>=1 && ProcesoActual.getEstado()!="Bloqueado"){ 
                ProcesoActual.setEstado("Listo");
                ColaListos.agregarProceso(ProcesoActual);  // si el trabajo no está terminado, agréguelo a la cola lista nuevamente
            }
            if(ColaListos.estaVacia()) {
                return null;
            }
            tiempoProcesado = Quantum;  // reiniciar el tiempo cuántico para el nuevo trabajo
            parametro = true;
            setProcesoActual();  // mover el primer trabajo en la cola lista para que sea el trabajo actual
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
            System.out.println("Next "+ProcesoBloqueado.getProcesoID()+" - "+ProcesoBloqueado.estado);
        }
        return trabES(simulationTime);
    }
    /**
      * trabajar el trabajo actual en la CPU para un paso de tiempo de simulación
      * @param tiempoSimulacion tiempo actual de la simulación
      * @return el trabajo actual en el que está trabajando la CPU
    **/
    @Override
    protected Proceso trabCPU(int tiempoSimulacion){
        ProcesoActual.setEstado("En ejecucion");
        ProcesoActual.procTrabajado(tiempoSimulacion);
        if(ProcesoActual.getInterrupcion(tiempoSimulacion)==1) {
            ProcesoActual.setEstado("Bloqueado");
            lista.agregarProceso(ProcesoActual);
        }
        
        tiempoProcesado--; 
        if(tiempoProcesado == 0 || ProcesoActual.getRemainTime() ==0 || ProcesoActual.getEstado().equals("Bloqueado")) {
            parametro = false;} 
        System.out.println("trab"+ProcesoActual.getEstado()+" "+ProcesoActual.getProcesoID());
        return ProcesoActual;
    }

    
    
    @Override
    protected Proceso trabES(int tiempoSimulacion)
    {
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
