import static java.lang.Math.*; // zaimportowanie biblioteki umozliwiajacej pierwiastkowanie ktore jest niezbedne do obliczenia dlugosci wektora      
public class Vector2D {
    
    public double XCoordinate, YCoordinate; 
    // stworzenie domyslnego konstruktora
    Vector2D(){
        XCoordinate = 0;
        YCoordinate = 0;
    }    
    // stworzenie konstruktora z parametrami
    Vector2D(double XTest, double YTest){
        XCoordinate = XTest;
        YCoordinate = YTest;
    }
    // stworzenie metody liczacej sume wektorow
    Vector2D Sum(Vector2D parentVector){
        System.out.println("Sum:");
        Vector2D Equals = new Vector2D(XCoordinate + parentVector.XCoordinate, YCoordinate + parentVector.YCoordinate);
        Equals.vectorParameters();
        return Equals; 
    }
    // stworzenie metody liczacej roznice wektorow
    Vector2D Substract(Vector2D parentVector){
        System.out.println("Substraction:");
        Vector2D Equals = new Vector2D(XCoordinate - parentVector.XCoordinate, YCoordinate - parentVector.YCoordinate);
        Equals.vectorParameters();
        return Equals;
    }
    // stworzenie metody liczacej mnozenie wektorow przez skalar
    Vector2D Multiply(float multiplicator){
        System.out.println("Substraction:");
        Vector2D Equals = new Vector2D(XCoordinate*multiplicator, YCoordinate*multiplicator);
        Equals.vectorParameters();        
        return Equals;
    }
    // obliczenie i zwrocenie dlugosci wektora
    double vectorLenght(){  
        double Lenght = sqrt( pow(XCoordinate,2) + pow(YCoordinate,2) );
        return Lenght;
    }
    // normalizowanie wektora
    Vector2D Normalize(){
        
        Vector2D Equals = new Vector2D();
        // nie da sie stworzyc znormalizowanego wektora z wektora zerowego
        if(XCoordinate == 0 && YCoordinate == 0){
            System.out.println("Can not normalize vector which lenght equals 0");
            return Equals;
        }
        else{
            Equals.XCoordinate = XCoordinate/vectorLenght();
            Equals.YCoordinate = YCoordinate/vectorLenght();   
            System.out.println("Normalization successful");
            Equals.vectorParameters();
            return Equals;
        }
    }
    // zwrocenie parametow wektora
    void vectorParameters(){
        System.out.println("Vector [X,Y], \nX = " + XCoordinate + "\nY = " + YCoordinate);
        System.out.println("Vector lenght equals " + vectorLenght() + "\n");
    }
    
    @SuppressWarnings("unused")
	static void main(String[] args){
       //stworzenie wektorow |AB| i |CD|
       Vector2D vectorAB = new Vector2D(5,5);
       Vector2D vectorCD = new Vector2D(3,1);
       // suma wektorow
       Vector2D Sum = vectorAB.Sum(vectorCD);
       // roznica wektorow
       Vector2D Substract = vectorAB.Substract(vectorCD);
       // normalizacja wektorow
       Vector2D normalizedVectorAB = vectorAB.Normalize();
       Vector2D normalizedVectorCD = vectorCD.Normalize();
       // stworzenie zmiennych przechowujÄ…cych dlugosci wektorow
       double lenghtOfAB = vectorAB.vectorLenght();
       double lenghtOfCD = vectorCD.vectorLenght();
       // mnozenie wektorow przez skalar
       Vector2D Multiplication = vectorAB.Multiply(3);
   }
}