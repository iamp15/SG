
package mainPackage;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.*;


public class Ventana extends JFrame{
    
    private Date f2, fExp;
    private Date fActual = new Date();
    private boolean vencida = false;
    private File licencia = new File("data/dir");
    private int ini;
    private final int trialP = 7; //MODIFICAR AQUI EL TIEMPO DE DURACIÓN DE LA LICENCIA
    
    
    //Constructor
    public Ventana(){

       
        setTitle("IAMP Summary Generator");

        setSize(1100,620);   //Establezco posicion y tamanio de la ventana
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //establezco que al cerrar la ventana se detega la ejecucion del programa
        
        addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowOpened(WindowEvent e){
                
                if(licencia.exists()){
                    try {
                        String otraFecha = loadDate();       //string para guardar la fecha de creacion de licencia           
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                        fExp = sdf.parse(otraFecha);  //convierto el string en formato date. F2 es la fecha cuando se inició la licencia
                        
                       
                    } catch (IOException ex) {
                        System.out.println("Error al cargar la fecha");
                    } catch (ParseException ex) {
                        System.out.println("Error convirtiendo fecha");
                    }
                    
                    if(fActual.after(fExp)) vencida = true;
                    
                }
                else{
                    //Mensaje que da la bienvenida al usuario y preunta si desea iniciar el periodo de prueba
                    ini = JOptionPane.showConfirmDialog(Ventana.this, "Welcome to IAMP Summary Generator trial version."
                            + "\nWould you like to start your "+trialP+ " days trial period?","IAMP Summary Generator "+trialP+" days trial "
                                    + "version",JOptionPane.OK_CANCEL_OPTION);
                    
                    if(ini==0){
                        GregorianCalendar f1 = new GregorianCalendar();
                        f1.add(Calendar.DAY_OF_MONTH,trialP);
                        String stringF1 = f1.get(Calendar.DAY_OF_MONTH)+"-"+(f1.get(Calendar.MONTH)+1)+"-"+f1.get(Calendar.YEAR)
                            +" "+f1.get(Calendar.HOUR)+":"+f1.get(Calendar.MINUTE)+":"+f1.get(Calendar.SECOND);
                           
                        try {                    
                            saveDate(stringF1);
                        } catch (IOException ex) {
                            System.out.println("Error al guardar la fecha");
                        }
                    }
                    else System.exit(0);
               
                }
                System.out.println(vencida);
                if(vencida){
                    JOptionPane.showMessageDialog(Ventana.this, "Trial period expired. Please send an e-mail to iamp15@hotmail.com for "
                            + "renewal information.");
                    System.exit(0);
                }
                
                
                
            }
            
            
            
            public void saveDate(String fecha) throws IOException{
                
                BufferedWriter wBuff = new BufferedWriter(new FileWriter(licencia));
                wBuff.write(fecha);
                wBuff.close();
                licencia.setReadOnly();
            }
            
            public String loadDate() throws IOException{
                
                BufferedReader rBuff = new BufferedReader(new FileReader(licencia));
                String linea = rBuff.readLine();
                rBuff.close();
        
                return linea;
            }
            
        });
        
        
        
        
    }
    
}
