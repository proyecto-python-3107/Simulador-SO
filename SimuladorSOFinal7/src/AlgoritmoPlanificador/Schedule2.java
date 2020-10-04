package AlgoritmoPlanificador;

import Clases.Proceso;
import Clases.Cola;
import Clases.Schedule;
import Memoria.InfoNodo;
import Memoria.MemoriaPartida;
import javax.swing.JOptionPane;

public abstract class Schedule2 {
    
    protected Cola lista ;  
    protected Cola ColaListos; 
    protected Cola ColaBloqueado;
    protected Proceso ProcesoActual;  
    protected Proceso ProcesoBloqueado;  
    protected boolean parametro ; 
    protected boolean parametroI ; 
    protected int signal;
    protected String señal;
    protected String asignacionType;
    private MemoriaPartida Memoriapartida;
    

    public Schedule2(Cola ArregloProcesos){
        ColaListos = new Cola(ArregloProcesos.size());
        ColaBloqueado= new Cola(ColaListos.size());
        ProcesoActual = new Proceso(21); 
        ProcesoBloqueado = new Proceso(22); 
        parametro = false; 
        parametroI = false; 
        lista = ArregloProcesos.traerCopia(); 
        lista.OrdenarPorLlegada();
        ColaListos.OrdenarPorLlegada();
        asignacionType=Schedule.getTipoAsignacion();
        
    }
    
   
    public abstract Proceso nextStep (int simulationTime);
    public abstract Proceso nextStep2 (int simulationTime);

    protected Proceso trabCPU(int simulationTime)
    {  
        ProcesoActual.procTrabajado(simulationTime);
        return ProcesoActual;
    }
    protected Proceso trabES(int simulationTime)
    {
        ProcesoBloqueado.jobWorkedES(simulationTime);
        return ProcesoBloqueado;
    }

    public Cola getColaListos ()
    {
        return ColaListos.traerCopia();
    }

    public Cola getColaBloqueado() {
        return ColaBloqueado.traerCopia();
    }
   

    public boolean estaFinalizado()
    {
        return (lista.estaVacia() && ColaListos.estaVacia()&& ColaBloqueado.estaVacia() && !parametro &&  ProcesoActual.getRemainTime() == 0 &&  ProcesoBloqueado.getRemainTime() == 0);
    }

    
    protected void cargarColaListos(int simulationTime)
    {
        for (int i = 0 ; i<lista.size() ; i++){
            Proceso temp = lista.getProceso(i);
  
            if(temp.TiempoLlegada == simulationTime || temp.isStateI()==true && temp.estado!="Bloqueado")  
            {
                CargarMemoria(temp);
                ColaListos.agregarProceso(temp);  
                ColaListos.OrdenarPorLlegada();
                lista.sacarProceso(i); 
                i--; 
            }
        }
        for (int i = 0 ; i<lista.size() ; i++){
            Proceso temp = lista.getProceso(i);
            if(temp.estado.equals("Bloqueado")&& temp.getProcesoID()!=21){
                if(temp.getTipI()==2){
                    señal=JOptionPane.showInputDialog(null, "Desea continuar [C]", "Interrupcion del Proceso ID "+ProcesoActual.getProcesoID(), JOptionPane.WARNING_MESSAGE);
                    if (señal.equals("C")) {
                        ColaBloqueado.agregarProceso(temp);  
                        lista.sacarProceso(i); 
                    } else{
                        temp.setSeñalP(-1);
                        temp.setInterrupcion(0);
                        temp.setRemainTime(0);
                        temp.setEstado("Finalizado");
                        lista.sacarProceso(i); 
                    }
                }else{
                        ColaBloqueado.agregarProceso(temp);  
                        lista.sacarProceso(i); 
                }
                
                i--; 
            }
        }
        
        for (int i = 0 ; i<ColaBloqueado.size() ; i++){
            Proceso temp2 = ColaBloqueado.getProceso(i);
            if(temp2.getEstado()=="Listo"){
                lista.agregarProceso(temp2); 
                ColaBloqueado.sacarProceso(i);
                i--;
            }
        }
        if (ColaListos.size()!=0) {
            for (int i = 0 ; i<ColaListos.size() ; i++){
            Proceso temp2 = ColaListos.getProceso(i);
            
        }
        }
        
    }
    
    int var=0;

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }
    
    
    protected void CargarMemoria(Proceso temp){
                System.out.println("proceso"+temp.getProcesoID());
                
                if(asignacionType.equals("FirstFit")) {
                     MemoriaPartida mem= new MemoriaPartida();
                     InfoNodo a1= new InfoNodo("P",var,temp.getMemoria());
                     temp.setNodoAsignadoInicio(var); 
                     this.var=temp.getMemoria()+1+var;
                     temp.setNodoAsignadoFin(var-1);
                     mem.insertar(a1);
                     mem.FirstFit(temp);
                     mem.imprimir();
                     } 
                else if(asignacionType.equals("BestFit")) {
                    MemoriaPartida mem= new MemoriaPartida();
                     InfoNodo a1= new InfoNodo("P",var,temp.getMemoria());
                     this.var=temp.getMemoria()+1+var;
                     mem.insertar(a1);
                     mem.BestFit(temp);
                     mem.imprimir();
                } 
                else if(asignacionType.equals("WorstFit")) {
                    MemoriaPartida mem= new MemoriaPartida();
                     InfoNodo a1= new InfoNodo("P",var,temp.getMemoria());
                     this.var=temp.getMemoria()+1+var;
                     mem.insertar(a1);
                     mem.WorstFit(temp);
                     mem.imprimir();
                } 
    }
    
    protected void setProcesoActual(){
        ProcesoActual = ColaListos.getProceso(0); 
        ColaListos.sacarProceso(0);  
    }
    protected void setProcesoBloqueado(){
        ProcesoBloqueado = ColaBloqueado.getProceso(0); 
        ColaBloqueado.sacarProceso(0);  
    }
 
    
}
