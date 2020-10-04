package Clases;

import java.util.Random;


public class Proceso {
    
    public int ProcesoID;    
    public int TiempoLlegada;
    public int burstTime;
    private int startTime;
    public int prioridad;
    public boolean state; 
    public boolean stateI; 
    public String estado; 
    private int finishTime;
    private int WaitingTime;
    private int Memoria;
    private int Interrupcion;
    private int PC;
    private int CantInterrupciones;
    private int SeñalP;
    private int NodoAsignadoInicio;
    private int NodoAsignadoFin;

    
    public int getProcesoID() {
        return ProcesoID;
    }

    public void setProcesoID(int ProcesoID) {
        this.ProcesoID = ProcesoID;
    }

    public int getTiempoLlegada() {
        return TiempoLlegada;
    }

    public void setTiempoLlegada(int arrivalTime) {
        this.TiempoLlegada = TiempoLlegada;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getEstado() {
        
        return estado;       
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMemoria() {
        return Memoria;
    }

    public void setMemoria(int Memoria) {
        this.Memoria = Memoria;
    }


    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getWaitingTime() {
        return WaitingTime;
    }

    public void setWaitingTime(int WaitingTime) {
        this.WaitingTime = WaitingTime;
    }
    
    public int tipI;
    public int timeI;
    public int tiempRestanteBloq;

    public int getTipI() {
        return tipI;
    }

    public void setTipI(int tipI) {
        this.tipI = tipI;
    }

    public int getTimeI() {
        return timeI;
    }

    public void setTimeI(int timeI) {
        this.timeI = timeI;
    }

    public int getTiempoRestanteBloq() {
        return tiempRestanteBloq;
    }

    public void setTiempoRestanteBloq(int tiempoBloqueado) {
        this.tiempRestanteBloq = tiempoBloqueado;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getNodoAsignadoInicio() {
        return NodoAsignadoInicio;
    }

    public void setNodoAsignadoInicio(int NodoAsignado) {
        this.NodoAsignadoInicio = NodoAsignado;
    }

    public int getNodoAsignadoFin() {
        return NodoAsignadoFin;
    }

    public void setNodoAsignadoFin(int NodoAsignadoFin) {
        this.NodoAsignadoFin = NodoAsignadoFin;
    }
    
    
    
    
    public int getInterrupcion(int SimulationTime) { 
        
        return Interrupcion;
    }

    public void setInterrupcion(int Interrupcion) {
        this.Interrupcion = Interrupcion;
    }

    public boolean isStateI() {
        return stateI;
    }

    public void setStateI(boolean stateI) {
        this.stateI = stateI;
    }

    public int getCantInterrupciones() {
        return CantInterrupciones;
    }

    public void setCantInterrupciones(int CantInterrupciones) {
        this.CantInterrupciones = CantInterrupciones;
    }

    public int getSeñalP(int SimulationTime) {
        return SeñalP;
    }

    public void setSeñalP(int SeñalP) {
        this.SeñalP = SeñalP;
    }

    

    public Proceso(int jobNumber)
    {
        this.ProcesoID = jobNumber;
        state = false;
        estado="Nuevo";
        Random rand = new Random();
        if(jobNumber == 1) {TiempoLlegada =0;}  
        else {TiempoLlegada = (jobNumber-1)*5+rand.nextInt(4);}  
        burstTime = rand.nextInt(25)+5;
        Memoria = rand.nextInt(63)+1;
        finishTime = 0 ;
        WaitingTime = burstTime;
        PC= rand.nextInt(80)+20;
        CantInterrupciones=0;
    }

    public Proceso(int jobNumber , int arriveTime , int BurstTime , int cantMemoria, int prioridad)
    {
        this.ProcesoID = jobNumber;
        state = false;
        estado="Nuevo";
        Random rand = new Random();
        TiempoLlegada = arriveTime;
        this.burstTime = BurstTime;
        this.Memoria= cantMemoria;
        this.prioridad= prioridad;
        finishTime = 0 ;        
        WaitingTime = BurstTime;
        PC= rand.nextInt(80)+20;
        CantInterrupciones=0;
    }
    

    public void procTrabajado(int simulationTime){
        if( burstTime == WaitingTime)  
        { startTime = simulationTime;}
        WaitingTime--;
        if(WaitingTime == 0){
            finishTime = simulationTime + 1;
            state = true;         
        }
        
        int a=0;
        if (estado=="En ejecucion"  && getRemainTime()>1) {
            Random random= new Random();
            double N;
            if(burstTime>20){
                N= Math.random();
                if(N<=0.1){
                    a=1;
                    this.CantInterrupciones++;
                } else {
                    a=0;
                }
            }else if(burstTime>12){
                N= Math.random();
                if(N<=0.02){
                    a=1;
                    this.CantInterrupciones++;
                } else {
                    a=0;
                }
            } else {
                a=0;
            }
            Interrupciones interrup=new Interrupciones(a);
            tipI=interrup.getTipoInterrupcion();
            timeI=interrup.getInterrupTime();
        this.Interrupcion=a;
        }
        
    }
    public void jobWorkedES(int simulationTime){
        if(estado=="Bloqueado"){
            this.tiempRestanteBloq =timeI;
        }
        timeI--;
        if(tiempRestanteBloq == 0){
            //estado = "Listo";     
            //System.out.println(""+timeI);
            this.setInterrupcion(0);
            stateI = true;
            state= false;
        }
    }
    

    public Proceso copyJob()
    {
        Proceso temp = new Proceso(this.ProcesoID);
        temp.TiempoLlegada = this.TiempoLlegada;
        temp.burstTime = this.burstTime;
        temp.Memoria = this.Memoria;
        temp.state = this.state;
        temp.ProcesoID = this.ProcesoID;
        temp.prioridad = this.prioridad;
        temp.setStart(this.startTime);
        temp.setFinish(this.finishTime);
        return temp;
    }
    

    public Proceso getClearCopyJob()
    {
        Proceso temp = new Proceso(this.ProcesoID);
        temp.TiempoLlegada = this.TiempoLlegada;
        temp.burstTime = this.burstTime;
        temp.Memoria = this.Memoria;
        temp.prioridad = this.prioridad;
        temp.WaitingTime = this.WaitingTime;
        return temp;
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters" >
    
    /**
     * @return percent of the done part of the job
     */
    public int getPercent() {
        return (int)((burstTime - getRemainTime())*100) / burstTime;
    }
    
    /**
     * calculate the wait time of the job
     * wait = turnaround - elapsed time.
     * @param SimulationTime simulation time since the whole simulation has started
     * @return waiting time of the job
     */
    public int getWaitTime(int SimulationTime) {

        return (getTurnaround(SimulationTime) - (burstTime - getRemainTime()));
        
    }
    
    /**
     * @return the remaining time of the job
     */
    public int getRemainTime(){
        return this.WaitingTime;
        
    }
    
    /**
     * calculate the turnaround time of the job
     * requires the simulation time if the job hasn't finished yet
     * @param SimulationTime simulation time since the whole simulation has started 
     * @return turnaround time of the job
     */
    public int getTurnaround(int SimulationTime){
        if(state){  // if job is finished
            estado="Finalizado";
            return (finishTime - TiempoLlegada );
        }
        if(SimulationTime > TiempoLlegada){ // if job arrived but hasn't finished yet
           return (SimulationTime - TiempoLlegada);
        }
        if(SimulationTime == TiempoLlegada){ // if job arrived but hasn't finished yet
           estado="Listo";
           
        }
        
        return 0; 
        
    }

    public int getFinish(){
        if(state) 
        {
            return finishTime;
        }
        return 0;
    }
    
    /**
     * @return start time of the job
     */
    public int getStart(){
        
        
        return startTime;
    }
    
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="setters" >
    
    /**
     * set remaining time of the job
     * @param remaining remaining time of the job
     */
    public void setRemainTime(int remaining){
        this.WaitingTime = remaining;
        
    }
    
    /**
     * set finish time of the job
     * @param finish finish time of the job
     */
    public void setFinish(int finish){
        this.finishTime = finish;
    }
    
    /**
     * set start time of the job
     * @param start start time of the job
     */
    public void setStart(int start){
        this.startTime = start;
    }
    
    // </editor-fold>
    

    public boolean isFirst(Proceso nuevo) {
        if(this.TiempoLlegada == nuevo.TiempoLlegada)
        {
            return (this.ProcesoID < nuevo.ProcesoID);
        }
        return (this.TiempoLlegada < nuevo.TiempoLlegada);
    }
    

    public boolean isShort(Proceso nuevo){
        if(this.burstTime == nuevo.burstTime)
        {
            return isFirst(nuevo);
        }
        return (this.burstTime < nuevo.burstTime);
    }
   
    
    public boolean isPrior(Proceso nuevo){
        if(this.prioridad == nuevo.prioridad) 
        {
            return isFirst(nuevo);
        }
        return (this.prioridad < nuevo.prioridad);
    }
    

    public void MostrarDatos()
    {
        System.out.println("Mostrar Datos");
        if(this == null) {System.out.println("Empty job"); return;}
        System.out.println("# = " + this.ProcesoID + " , arrive = " + this.TiempoLlegada + " , burst = " + this.burstTime);
    }
}

