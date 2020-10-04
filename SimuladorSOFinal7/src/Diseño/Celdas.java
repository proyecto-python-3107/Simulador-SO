package Diseño;

import java.awt.Color;
import javax.swing.JLabel;

public class Celdas extends JLabel{
    

    private Celdas(int x , int y , int width , int height , int jobNumber)
    {
        setSize(width , height);
        chooseColor(jobNumber);
        setLocation(x, y);
        setOpaque(true);
    }

    public static Celdas createGanttCell(int x , int y , int jobNumber) 
    {
        return new Celdas(x, y, 20, 50, jobNumber);
    }

    public static Celdas createEmptyJobCell(int x , int y)
    {
        return new Celdas(x,y,10,50,21);
    }

    public static Celdas createReadyQueueCell(int x , int y , int jobNumber)
    {
        return new Celdas(x,y, 30 , 35 ,jobNumber);
    }
    

    public static Celdas createMark(int x , int y)
    {
        return new Celdas(x,y,1,10,10);
    }
    

    private void chooseColor(int type){

    }       
}
