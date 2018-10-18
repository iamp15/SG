
package mainPackage;

import java.io.*;



public class Memoria {
    
  
    
    public static void saveCount(int conteo) throws IOException{
        String data =Integer.toString(conteo);
        System.out.println();
        BufferedWriter wBuff = new BufferedWriter(new FileWriter("conteo.txt"));
        wBuff.write(data);
        wBuff.close();
    }
    
    public static String loadCount() throws IOException{
        
     
        BufferedReader rBuff = new BufferedReader(new FileReader("conteo.txt"));
        String linea = rBuff.readLine();
        rBuff.close();
        
        return linea;
    }
    
    
    
    
}
