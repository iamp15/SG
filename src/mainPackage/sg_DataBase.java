
package mainPackage;

import java.sql.*;



public class sg_DataBase {
    
    
    
    public static void saveCar(String make, String model){
        
        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/iamp_sg?autoReconnect=true&useSSL=false", "root", "");
            PreparedStatement state1 = con1.prepareStatement("INSERT INTO CARROS(MARCA,MODELO) VALUES(?,?) ON DUPLICATE KEY UPDATE MODELO = MODELO AND MARCA = MARCA");
            state1.setString(1, make);
            state1.setString(2, model);
            state1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al guardar en base de datos (carro)");
        }
        
    }
    
    public static void saveName(String name){
        
        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/iamp_sg?autoReconnect=true&useSSL=false", "root", "");
            PreparedStatement state1 = con1.prepareStatement("INSERT INTO NOMBRES(NOMBRE) VALUES(?) ON DUPLICATE KEY UPDATE NOMBRE = NOMBRE");
            state1.setString(1, name);
            state1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al guardar en base de datos (nombre)");
        }
        
    }
    
    public static ResultSet loadNames(){
        ResultSet r1 = null;
        
        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/iamp_sg?autoReconnect=true&useSSL=false", "root", "");
            Statement state1 = con1.createStatement();
            r1 = state1.executeQuery("SELECT * FROM NOMBRES");

        } catch (SQLException ex) {
            System.out.println("Error al cargar la base de datos (nombre)");
        }
        return r1;
    }
    
    public static ResultSet loadmm(){
        ResultSet r1 = null;
        
        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/iamp_sg?autoReconnect=true&useSSL=false", "root", "");
            Statement state1 = con1.createStatement();
            r1 = state1.executeQuery("SELECT * FROM CARROS");

        } catch (SQLException ex) {
            System.out.println("Error al cargar la base de datos (marca y modelo)");
        }
        return r1;
    }
    
    public static ResultSet getModels(String make){
        ResultSet r1 = null;
        
        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/iamp_sg?autoReconnect=true&useSSL=false", "root", "");
            PreparedStatement state1 = con1.prepareStatement("SELECT MODELO FROM carros WHERE marca=?");
            state1.setString(1, make);
            r1 = state1.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error al cargar los modelos especificados");
        }
        return r1;
           
    }
    
    public static ResultSet selectMake(String modelo){
        
        ResultSet r1 = null;
        
        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/iamp_sg?autoReconnect=true&useSSL=false", "root", "");
            PreparedStatement state1 = con1.prepareStatement("SELECT Marca FROM carros WHERE modelo=?");
            state1.setString(1, modelo);
            r1 = state1.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error al cargar marca especificada");
        }
        return r1;
        
        
    }
    
//    public static ResultSet loadModels(){       
//        ResultSet r1 = null;
//        
    
    
//        try {
//            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/iamp_sg?autoReconnect=true&useSSL=false", "root", "");
//            Statement state1 = con1.createStatement();
//            r1 = state1.executeQuery("SELECT modelo FROM CARROS");
//
//        } catch (SQLException ex) {
//            System.out.println("Error al cargar la base de datos (modelo)");
//        }
//        return r1;
//    }
    
}
