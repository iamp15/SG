
package mainPackage;


import javax.swing.*;
import static javax.swing.JFrame.*;


public class Ventana extends JFrame{
    
    //Constructor
    public Ventana(){

        
        setTitle("Summary Generator");

        setSize(1100,620);   //Establezco posicion y tamanio de la ventana
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //establezco que al cerrar la ventana se detega la ejecucion del programa
        
    }
    
}
