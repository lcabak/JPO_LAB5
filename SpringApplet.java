import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.awt.event.*; // biblioteka zawierajaca obsluge myszy

// implementacje MouseListener, MouseMotionListener, ActionListener
public class SpringApplet extends javax.swing.JApplet implements MouseListener, MouseMotionListener, ActionListener{
        //prywatne pola przechowujace obiekty
        private SimEngine SimulationEngine;
        private SimTask SimulationTask;
        private Timer SimulationTime;
        private MouseEvent mEvent;
        
        // miejsca, gdzie przechowywane beda elementy interfejsu
        private TextField mass, parameterK, parameterC, lenght0;
        private Button restart;
        
        private boolean mouseIsPressed; // zmienna boolowska wskazujaca czy przycisk myszy jest wcisniety
        // wspolrzedne kursora
        private int mouseLocX;
        private int mouseLocY; 
        
    //przeciazenie metody init     
    public void init()
    {
    	//stworzenie apletu 600x600
        this.setSize(600,600);
        //stworzenie obiektow do symulacji
        SimulationEngine = new SimEngine(30,3,0.2,100,10,10,50,40,0); //masa, k,c,l0, x0, y0, ptx0,pty0, v    
        SimulationTask = new SimTask(SimulationEngine,this,0.01);
        //uruchomienie timera
        SimulationTime = new Timer();
        SimulationTime.scheduleAtFixedRate(SimulationTask, 0, 2);   
        
        // dodanie myszy, ustawienie stanu na nie-kliknieta
        mouseIsPressed = false;
        addMouseListener(this);
        addMouseMotionListener(this);
        // dodanie przycisku
        restart = new Button("Restart");
        restart.addActionListener(this);
        add(restart);
        
        // stworzenie okienek z obiektami 
        mass = new TextField("Mass",4);
        parameterK = new TextField("K",4);
        parameterC = new TextField("C",4);
        lenght0 = new TextField("l0",4);
        
        add(mass);
        add(parameterK);
        add(parameterC);
        add(lenght0);  
    }
    //przeciazenie metody paint
    public void paint(Graphics gDC){ //przeciazenie paint
        //narysowanie pola
        gDC.fillRect(0,0,600,600);
          
        //wyrysowanie "sprezyny"
        gDC.setColor(Color.white);
        gDC.drawLine((int) SimulationEngine.getPointLocX()+300, (int)SimulationEngine.getPointLocY(), (int)SimulationEngine.getMassLocX()+300, (int)SimulationEngine.getMassLocY()+(int)SimulationEngine.getLenght0());
        //narysowanie lini na ktorej zawieszona jest sprezyna
        gDC.setColor(Color.red);
        gDC.drawLine(250, 40, 360, 40);
        //narysowanie masy na sprezynie
        gDC.setColor(Color.green);
        gDC.fillOval((int)SimulationEngine.getMassLocX()+275, (int)SimulationEngine.getMassLocY()+(int)SimulationEngine.getLenght0(), 50 , 70);       
        
        // stworzenie okienek z przyciskami
        mass.setBounds(30, 50, 30, 30);
        parameterK.setBounds(30, 100, 30, 30);
        parameterC.setBounds(30, 150, 30, 30);
        lenght0.setBounds(30, 200, 30, 30);
        restart.setBounds(10,300,50,50);
    }
    
    // przeciazone importowane metody z MouseEvent
    public void mouseMoved(MouseEvent mEvent){        
    }
    
    public void mouseExited(MouseEvent mEvent){        
    }
    
    public void mouseEntered(MouseEvent mEvent){        
    }
    
    public void mouseClicked(MouseEvent mEvent){     
    }
    
    public void actionPerformed(ActionEvent aEvent){     
        
        if(aEvent.getSource() == restart){
            
            SimulationTime.cancel();
            double m = Double.parseDouble(mass.getText());
            double k = Double.parseDouble(parameterK.getText());
            double c = Double.parseDouble(parameterC.getText());
            double l0 = Double.parseDouble(lenght0.getText());
           
            SimulationEngine = new SimEngine(m,k,c,l0,10,10,50,40,25); //masa, k,c,l0, x0, y0, ptx0,pty0, v    
            SimulationTask = new SimTask(SimulationEngine, this, 0.01);
            SimulationTime = new Timer();
            SimulationTime.scheduleAtFixedRate(SimulationTask,0,1);
            repaint();
        }
    }
    
    public void mouseReleased(MouseEvent mEvent){
        // pod warunkiem ze przycisk myszy jest klikniety
        if(mouseIsPressed == true){
            
            SimulationTask = new SimTask(SimulationEngine,this,0.01);
            SimulationTime = new Timer();
            SimulationTime.scheduleAtFixedRate(SimulationTask, 0, 1);
            mouseIsPressed = false; //uwolnienie myszy
        }   
        mEvent.consume();
    }


    public void mousePressed(MouseEvent mEvent){
            mouseLocX = mEvent.getX();
            mouseLocY = mEvent.getY();
            // kontrola czy kursor znajduje sie w owalu reprezentujacym mase
            if(mouseLocX >= (int)SimulationEngine.getMassLocX()+275 && mouseLocX <= (int)SimulationEngine.getMassLocX()+325 && mouseLocY >= (int)SimulationEngine.getMassLocY() && mouseLocY <= (int)SimulationEngine.getMassLocY()-70){
            
            SimulationTime.cancel(); // reset timera
            SimulationEngine.simulationReset(); // ponowne uruchomienie symulacji
            mouseIsPressed = true; // oznacza ze mysz jest wcisnieta
            }
        mEvent.consume(); // uzycie metody consume
    }
    
    public void mouseDragged(MouseEvent arg0){
        // kontrola przeciagania myszy
        if(mouseIsPressed == true){
            //zdecydowanie gdzie jest polozona masa i ppolozenie tam kursora oraz repaint appletu
            
            SimulationEngine.setMassLocalisationY(arg0.getY());
            repaint();
        
        }
        arg0.consume();
    }    
}