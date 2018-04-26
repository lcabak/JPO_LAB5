import java.util.TimerTask; //zaimportowanie biblioteki dla TimerTask

public class SimTask extends TimerTask { 
    //prywatne pola do przechowywania obiektow
    private SimEngine SimulationEngineTimer;
    private SpringApplet SpringAppletTimer;
    private double timeDelay;
    
    //konstruktor z parametrami
    public SimTask(SimEngine SimulationEngineTimer, SpringApplet SpringA, double delay){
        this.SimulationEngineTimer = SimulationEngineTimer;
        this.SpringAppletTimer = SpringA;
        this.timeDelay = delay;
    }
    
    //przeciazenie run
    public void run(){
        //uruchomienie kolejnego kroku symulacji
    	SimulationEngineTimer.simulationOfMovement(timeDelay);
        //odswiezenie widoku 
    	SpringAppletTimer.repaint();
    }
}
