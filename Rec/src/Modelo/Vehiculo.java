/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import Vista.FrmAgregarVehiculo;

/**
 *
 * @author Usuario
 */
public class Vehiculo {
    
    private String ID_Vehiculo;
    private String Marca;
    private String Modeloo;
    private String Año;
    private String ID_cliente;
    
    public String getID_Vehiculo() {
        return ID_Vehiculo;
    }

    public void setID_Vehiculo(String ID_Vehiculo) {
        this.ID_Vehiculo = ID_Vehiculo;
    }
    
     public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }
     public String getModelo() {
        return Modeloo;
    }

    public void setModelo(String Modelo) {
        this.Modeloo = Modelo;
    }
     public String getAño() {
        return Año;
    }

    public void setAño(String Año) {
        this.Año = Año;
    }
     public String getID_cliente() {
        return ID_cliente;
    }

    public void setID_cliente(String ID_cliente) {
        this.ID_cliente = ID_cliente;
    }
    
     public Vehiculo(){
    
    }
    
    public Vehiculo(String id_comite, String comite) {
    this.ID_Vehiculo = id_comite;
    this.Modeloo = comite;
    }
    
    @Override
    public String toString() {
        return Modeloo; 
    }
    
    public void CargarComboboxVehiculo(JComboBox comboBox) {
    Connection conexion = ClaseConexion.getConexion();

    comboBox.removeAllItems();
    
    try {
        Statement statemente = conexion.createStatement();
        ResultSet rs = statemente.executeQuery("SELECT * FROM Vehiculo");
        
        while (rs.next()) {
            String id = rs.getString("ID_Vehiculo");
            String Modelo = rs.getString("Marca");
            comboBox.addItem(new Estado(id, Modelo)); 
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    
     public void GuardarUsuario(){       
    // Creamos una variable igual a ejecutar el método de la clase de conexión       
    Connection conexion = (Connection) ClaseConexion.getConexion();
    
    System.out.println("si funciona la el controlador");
    try {           
        // Creamos el PreparedStatement que ejecutará la Query           
        PreparedStatement newUs = conexion.prepareStatement(
            "Insert into Vehiculo (ID_Vehiculo, Marca, Modelo, Año, ID_Cliente) Values (?, ?, ?, ?, ?)"
        );          
        
        // Establecer valores de la consulta SQL
        newUs.setString(1, UUID.randomUUID().toString());
        newUs.setString(2, getMarca());
        newUs.setString(3, getModelo());
        newUs.setString(4, getAño());
        newUs.setString(5, getID_cliente());
        
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
        modeloUsuario.setColumnIdentifiers(new Object[]{"ID_Vehiculo", "Modelo", "Marca", "Año"});
        
        try {
            //Creamos un statement para que se conecte con la base y realice una acción         
            Statement statement = conexion.createStatement();
            
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet          
            ResultSet rs = statement.executeQuery("SELECT * FROM Vehiculo");
            System.out.println("este es el try "+ rs);
            
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloUsuario.addRow(new Object[]{rs.getString("ID_Vehiculo"), 
                    rs.getString("Modelo"), 
                    rs.getString("Marca"),
                    rs.getString("Año")});
                
            }
            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloUsuario);
            
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
       
       public void ActualizarUsuario(JTable tabla){
        //Creamos una variable igual a ejecutar el mÃ©todo de la clase de conexión
        Connection conexion = (Connection) ClaseConexion.getConexion();
        //obtenemos que fila selecciona el usuario
        int filaseleccionada = tabla.getSelectedRow();
        if(filaseleccionada != -1){
            //Obtenemos el id de la fila seleccionada
            String UUIDus = tabla.getValueAt(filaseleccionada, 0).toString();
            try{
                //Ejecutamos la Query
                PreparedStatement Actualizarus = conexion.prepareStatement("UPDATE Vehiculo set Marca =?, Modelo =?, Año=? WHERE ID_Vehiculo =?");
                Actualizarus.setString(1, getMarca());
                Actualizarus.setString(2, getModelo());
                Actualizarus.setString(3, getAño());

                Actualizarus.setString(4, UUIDus);
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
        Connection conexion = (Connection) ClaseConexion.getConexion();
        
        //Obtenemos que fila seleccionÃ³ el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //Eliminamos al usuario 
        try{
            PreparedStatement EliminarUsuario = conexion.prepareStatement("DELETE FROM Vehiculo WHERE ID_Vehiculo =?");
            EliminarUsuario.setString(1, miId);
            EliminarUsuario.executeUpdate();
        }
        catch(Exception e){
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
     public void cargarDatosTabla(FrmAgregarVehiculo vista) {
        
        // Obtenemos la fila seleccionada
        int filaSeleccionada = vista.TablaUsuario.getSelectedRow();
        
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String ID_Vehiculo = vista.TablaUsuario.getValueAt(filaSeleccionada, 0).toString();
            String nombre = vista.TablaUsuario.getValueAt(filaSeleccionada, 1).toString();
            String contraseña = vista.TablaUsuario.getValueAt(filaSeleccionada, 2).toString();

            // Establece los valores en los campos de texto
            
            vista.txtNombre.setText(nombre);
            vista.txtTelefono.setText(contraseña);
        }
    }

    
    
    
    
    
}
