/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ClaseConexion {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USUARIO = "Rec";
    private static final String CONTRASENA = ".";

    //CreaciÃ³n del metodo de conexiÃ³n que retorna la conexiÃ³n
    
    public static Connection getConexion() {
        try {
            // Cargar el driver JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");
 
            // Obtener la conexión en una variable
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            
            // Retornamos la variable que tiene la conexión
            return conexion;
            
        } catch (SQLException e) {
            System.out.println("Este es el error" + e);
              return null;
              
        } catch (ClassNotFoundException ex) {
            System.out.println("este es el error de la clase" + ex);
              return null;
        }
    }
}
