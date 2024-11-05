/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Vista.FrmClientes;
import javax.swing.JComboBox;

/**
 *
 * @author Usuario
 */
public class Cliente {

    private String ID_Cliente; 
    private String Nombre;
    private String Telefono;
    
    
     public String getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(String ID_Usuario) {
        this.ID_Cliente = ID_Usuario;
    }
    
    
     public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Usuario) {
        this.Nombre = Usuario;
    }
    
    
     public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Contraseña) {
        this.Telefono = Contraseña;
    }
    public Cliente(){
    
    }
    
    public Cliente (String uuidus, String nombre){
        this.ID_Cliente = uuidus;
        this.Nombre = nombre;
    }
    
    @Override
    public String toString(){
        return Nombre;
    }
    
    public void CargarComboboxCliente(JComboBox comboBox) {
    Connection conexion = ClaseConexion.getConexion();
    comboBox.removeAllItems();
    
    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Cliente");
        
        while (rs.next()) {
            String id = rs.getString("ID_Cliente");
            String Nombre = rs.getString("nombre");
            comboBox.addItem(new Cliente(id, Nombre)); 
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    
     public void GuardarCliente(){       
    // Creamos una variable igual a ejecutar el método de la clase de conexión       
    Connection conexion = ClaseConexion.getConexion();
    
    System.out.println("si funciona la el controlador");
    try {           
        // Creamos el PreparedStatement que ejecutará la Query           
        PreparedStatement newUs = conexion.prepareStatement(
            "Insert into Cliente (ID_Cliente, Nombre, Telefono) Values (?, ?, ?)"
        );          
        
        // Establecer valores de la consulta SQL
        newUs.setString(1, UUID.randomUUID().toString());
        newUs.setString(2, getNombre());
        newUs.setString(3, getTelefono());
        
        // Ejecutar la consulta            
        newUs.executeUpdate();
        
        
    } catch (SQLException ex) {
        System.out.println("Texto del mensaje "+ex);
        
       
    }
}
       public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase conexión       
        Connection conexion = ClaseConexion.getConexion();
        
        //Definimos el modelo de la tabla
        DefaultTableModel modeloUsuario = new DefaultTableModel();
        modeloUsuario.setColumnIdentifiers(new Object[]{"ID_Cliente", "Nombre", "Telefono"});
        
        try {
            //Creamos un statement para que se conecte con la base y realice una acción         
            Statement statement = conexion.createStatement();
            
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet          
            ResultSet rs = statement.executeQuery("SELECT * FROM Cliente");
            
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloUsuario.addRow(new Object[]{rs.getString("ID_Cliente"), 
                    rs.getString("Nombre"), 
                    rs.getString("Telefono")});
            }
            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloUsuario);
            
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
       
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
                PreparedStatement Actualizarus = conexion.prepareStatement("UPDATE Cliente set Nombre =?, Telefono =? WHERE ID_Cliente =?");
                Actualizarus.setString(1, getNombre());
                Actualizarus.setString(2, getTelefono());
                Actualizarus.setString(3, UUIDus);
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
            PreparedStatement EliminarUsuario = conexion.prepareStatement("DELETE FROM Cliente WHERE ID_cliente =?");
            EliminarUsuario.setString(1, miId);
            EliminarUsuario.executeUpdate();
        }
        catch(Exception e){
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
     public void cargarDatosTabla(FrmClientes vista) {
        
        // Obtenemos la fila seleccionada
        int filaSeleccionada = vista.TablaUsuario.getSelectedRow();
        
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUID_Usuario = vista.TablaUsuario.getValueAt(filaSeleccionada, 0).toString();
            String nombre = vista.TablaUsuario.getValueAt(filaSeleccionada, 1).toString();
            String contraseña = vista.TablaUsuario.getValueAt(filaSeleccionada, 2).toString();

            // Establece los valores en los campos de texto
            
            vista.txtNombre.setText(nombre);
            vista.txtTelefono.setText(contraseña);
        }
    }

      
}
