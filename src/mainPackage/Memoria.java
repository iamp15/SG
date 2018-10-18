
package mainPackage;

import java.io.*;



public class Memoria {
    
    public static void saveCount(int conteo) throws IOException{
        String data =Integer.toString(conteo);           
            BufferedWriter wBuff = new BufferedWriter(new FileWriter("C:\\Users\\iamp1\\OneDrive\\Java\\Practicas\\IAMP_SG\\conteo.txt"));
            wBuff.write(data);
            wBuff.close();
        
        
    }
    
    public static String loadCount() throws IOException{
     
        BufferedReader rBuff = new BufferedReader(new FileReader("C:\\Users\\iamp1\\OneDrive\\Java\\Practicas\\IAMP_SG\\conteo.txt"));
        String linea = rBuff.readLine();
        rBuff.close();
        
        return linea;
    }
    
    
    
    
}
