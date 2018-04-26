import static java.lang.Math.*; // zaimportowanie biblioteki umozliwiajacej pierwiastkowanie ktore jest niezbedne do obliczenia dlugosci wektora
public class SimEngine {
    
    //prywatne pole przechowujace niezbedne wpolczynniki
    private double mass, k, c, L0, massLocX, massLocY, pointLocX, pointLocY, massV, massA, time, temporaryNumber;
    
           double g = 9.81; //przyblizone przyspieszenie grawitacyjne na Ziemi 
    
    //metody sluzace do zmieniania wartosci wspolczynnikow wraz z kontrola wartosci
    double setMass(double massTest){
        
        if(massTest < 0){
            System.out.println("Mass can't be negative.");
            return mass;
        }
        else{
            mass = massTest;
            System.out.println("Hanged mass equals: ");
            return mass;
        }
    }

    double setKParameter(double kTest){
        
        if(kTest < 0){
            System.out.println("k parameter can't be negative.");
            return k;
        }
        else{
            k = kTest;
            System.out.println("k parameter equals: ");
            return k;
        }
    }    
    
     double setDamping(double cTest){
        
        if(cTest < 0){
            System.out.println("Damping can't be negative.");
            return c;
        }
        else{
            c = cTest;
            System.out.println("Damping equals: ");
            return c;
        }
    }  
     
         double setL0(double l0Test){
        
        if(l0Test < 0){
            System.out.println("L0 can't be negative.");
            return L0;
        }
        else{
            L0 = l0Test;
            System.out.println("L0 equals: ");
            return L0;
        }
    }      
    
     double setMassLocalisationX(double x0){
        if(x0 < 0){
            System.out.println("Localisation can't be negative.");
            return massLocX;
        }
        else{
            massLocX = x0;
            System.out.println("Localisation od mass equals: ");
            return massLocX;
        }
    }   

     double setMassLocalisationY(double y0){
        if(y0 < 0){
            System.out.println("Localisation can't be negative.");
            return massLocY;
        }
        else{
            massLocY = y0;
            System.out.println("Localisation od mass equals: ");
            return massLocY;
        }
    }

     double setPointLocX(double xPoint0){
        if(xPoint0 < 0){
            System.out.println("Localisation can't be negative.");
            return pointLocX;
        }
        else{
            pointLocX = xPoint0;
            System.out.println("Localisation od mass equals: ");
            return pointLocX;
        }
    } 

     double setPointLocY(double yPoint0){
        if(yPoint0 < 0){
            System.out.println("Localisation can't be negative.");
            return pointLocY;
        }
        else{
            pointLocY = yPoint0;
            System.out.println("Localisation od mass equals: ");
            return pointLocY;
        }
    } 
     
    double setMassSpeed(double v0){
        if(v0 < 0){
            System.out.println("Speed can't be negative.");
            return massV;
        }
        else{
            massV = v0;
            System.out.println("Speed of mass equals: ");
            return massV;
        }
    }
    // metody zwracajace zmienne prywatne
    double getMassLocX(){
        return massLocX;
    }
    
    double getMassLocY(){
        return massLocY;
    }
    
    double getPointLocX(){
        return pointLocX;
    }
    
    double getPointLocY(){
        return pointLocY;
    }
    
    double getLenght0(){
        return L0;
    }
    //metoda sluzaca do wyliczania polozenia masy na sprezynie  
    public void simulationOfMovement(double simulationStep) {
    	time = simulationStep;
        //wzory fizyczne
    	massA = g-(k*massLocY/mass)-(c*massV/mass); 
    	massV = massV + massA*time;
    	temporaryNumber = temporaryNumber + massV*time + (massA*pow(time,2))/2;
    	massLocY = temporaryNumber;
    	
    }
    
    // konstruktor zawierajacy wszystkie mozliwe parametry ukladu
    SimEngine(double massTest, double kTest, double cTest, double l0Test, double x0, double y0, double xPoint0, double yPoint0, double v0){
        
        mass = massTest;
        k = kTest;
        c = cTest;
        L0 = l0Test;
        massLocX = x0;
        massLocY = y0;
        xPoint0 = x0;
        pointLocX = xPoint0;
        pointLocY = yPoint0;
        massV = v0;
        massA = 0;
        time = 0;  
    }      
   // wywolywanie ponownie symulacji
    public void simulationReset(){
        massV = 0;
        massA = 0;
    }
    //wywolanie testu klasy Vector2D
    public static void main(String[] args){
    SpringApplet Test = new SpringApplet(); //stworzenie apletu
    Test.init();
    }
}