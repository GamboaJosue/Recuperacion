/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.JTable;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import Vista.AgregarProblemas;
import java.util.UUID;


/**
 *
 * @author Usuario
 */
public class Problema {
    
    private String ID_Problema;
    private String Problema;
    private String Descripcion;
    private String ID_Vehiculo;
    private String ID_Estado;
    
    public String getID_Problema() {
        return ID_Problema;
    }

    public void setID_Problema(String ID_Problema) {
        this.ID_Problema = ID_Problema;
    }
    
    
     public String getProblema() {
        return Problema;
    }
    public void setProblema(String Problema) {
        this.Problema = Problema;
    }
    
    
     public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
     public String getID_Vehiculo() {
        return ID_Vehiculo;
    }

    public void setID_Vehiculo(String ID_Vehiculo) {
        this.ID_Vehiculo = ID_Vehiculo;
    }
    
    
    public String getID_Estado() {
        return ID_Estado;
    }

    public void setID_Estado(String ID_Estado) {
        this.ID_Estado = ID_Estado;
    }
    
    
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase conexión       
        Connection conexion = ClaseConexion.getConexion();
        
        //Definimos el modelo de la tabla
        DefaultTableModel modeloUsuario = new DefaultTableModel();
        modeloUsuario.setColumnIdentifiers(new Object[]{"ID_Problema", "Problema", "Descripcion"});
        
        try {
            //Creamos un statement para que se conecte con la base y realice una acción         
            Statement statement = conexion.createStatement();
            
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet          
            ResultSet rs = statement.executeQuery("SELECT * FROM Problemas");
            
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloUsuario.addRow(new Object[]{rs.getString("ID_Problema"), 
                    rs.getString("Problema"), 
                    rs.getString("Descripcion")});
            }
            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloUsuario);
            
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    //Método Cargar Datos en la tabla
    
    public void cargarDatosTabla(AgregarProblemas vista) {
        
        // Obtenemos la fila seleccionada
        int filaSeleccionada = vista.TablaUsuario.getSelectedRow();
        
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String ID_Problema = vista.TablaUsuario.getValueAt(filaSeleccionada, 0).toString();
            String Problema = vista.TablaUsuario.getValueAt(filaSeleccionada, 1).toString();
            String Descripcion = vista.TablaUsuario.getValueAt(filaSeleccionada, 2).toString();
            String ID_Vehiculo = vista.TablaUsuario.getValueAt(filaSeleccionada, 3).toString();
            String ID_Estado = vista.TablaUsuario.getValueAt(filaSeleccionada, 4).toString();



            

            // Establece los valores en los campos de texto
            
            vista.txtUsuario.setText(Problema);
            vista.txtContra.setText(Descripcion);
            vista.jComboBox1.setSelectedItem(ID_Vehiculo);
            vista.jComboBox2.setSelectedItem(ID_Estado);
            
        }
    }
    
    
    // Método Guardar Usuario
    
    public void GuardarUsuario(){       
    // Creamos una variable igual a ejecutar el método de la clase de conexión       
    Connection conexion = ClaseConexion.getConexion();
    
    try {           
        // Creamos el PreparedStatement que ejecutará la Query           
        PreparedStatement newUs = conexion.prepareStatement(
            "Insert into Problemas (ID_Problema, Problema, Descripcion, ID_Vehiculo, ID_Estado) Values (?, ?, ?, ?, ?)"
        );          
        
        // Establecer valores de la consulta SQL
        newUs.setString(1, UUID.randomUUID().toString());
        newUs.setString(2, getProblema());
        newUs.setString(3, getDescripcion());
        newUs.setString(4, getID_Vehiculo());
        newUs.setString(5, getID_Estado());
        
        // Ejecutar la consulta            
        newUs.executeUpdate();
        
    } catch (SQLException ex) {
        
                        System.out.println("este es el error en el metodo de actualizar" + ex);

       
    }
}

    
    // Método Actualizar Usuario
    
    public void ActualizarUsuario(JTable tabla){
        //Creamos una variable igual a ejecutar el mÃ©todo de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila selecciona el usuario
        int filaseleccionada = tabla.getSelectedRow();
        if(filaseleccionada != -1){
            //Obtenemos el id de la fila seleccionada
            String UUIDus = tabla.getValueAt(filaseleccionada, 0).toString();
            try{
                //Ejecutamos la Query
                PreparedStatement Actualizarus = conexion.prepareStatement("UPDATE Problemas set Problema =?, Descripcion =?, ID_Vehiculo=?, ID_Estado=? WHERE ID_Problema =?");
                Actualizarus.setString(5, getID_Problema());
                Actualizarus.setString (1, getProblema());
                Actualizarus.setString(2, getDescripcion());
                Actualizarus.setString(3, getID_Vehiculo());
                Actualizarus.setString(4, getID_Estado());
                Actualizarus.executeUpdate();
            }
            catch(Exception e){
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        }
        else {
            System.out.println("no funciona actualizar");
        }

    }
    
    
    // Método Eliminar Usuario
    
    public void EliminarUsuario(JTable tabla){
        
        //Creamos una variable igual a ejecutar el mÃ©todo de la clase de conexiÃ³n
        Connection conexion = ClaseConexion.getConexion();
        
        //Obtenemos que fila seleccionÃ³ el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //Eliminamos al usuario 
        try{
            PreparedStatement EliminarUsuario = conexion.prepareStatement("DELETE FROM Problemas WHERE ID_Problema =?");
            EliminarUsuario.setString(1, miId);
            EliminarUsuario.executeUpdate();
        }
        catch(Exception e){
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
}
