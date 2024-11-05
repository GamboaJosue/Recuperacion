package Modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Vista.FrmAgregarUsuario;

public class Usuario {
    private String ID_Usuario; 
    private String Usuario;
    private String Contraseña;
    
     public String getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(String ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }
    
    
     public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    
    
     public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
    
    
    
     //Método iniciar sesión    
       //El mÃ©todo devuelve un valor booleano (verdadero o falso)
       //Verdadero si existe el usuario y Falso si no existe
    
       public boolean IniciarSesion() {
           
        //Obtenemos la Conexion con la base de datos
           
        Connection conexion = ClaseConexion.getConexion();
        boolean resultado = false;

        try {
            
            //Preparamos la consulta SQL para verificar el usuario
            
            String sql = "SELECT * FROM Usuario WHERE Usuario = ? AND contraseña = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, getUsuario());
            statement.setString(2, getContraseña());

            //Ejecutamos la consulta
            
            ResultSet resultSet = statement.executeQuery();

            //Si hay un resultado, significa que el usuario existe y la contraseÃ±a es correcta
            
            if (resultSet.next()) {
                resultado = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error en el modelo: método iniciarSesion " + ex);
        }

        return resultado;
    }
       
       //Método Encriptar contraseña, Esto permitirá¡ que la contraseña se 
       //guarde de forma segura en la base y sea dificil de hackear
       
       public String SHA256(String password) {
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	}
	catch (NoSuchAlgorithmException e) {
		System.out.println(e.toString());
		return null;
	}
	byte[] hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
 
	for(byte b : hash) {
		sb.append(String.format("%02x", b));
	}
 
	return sb.toString();
       }
    
       
       public void GuardarUsuario(){       
    // Creamos una variable igual a ejecutar el método de la clase de conexión       
    Connection conexion = ClaseConexion.getConexion();
    
    System.out.println("si funciona la el controlador");
    try {           
        // Creamos el PreparedStatement que ejecutará la Query           
        PreparedStatement newUs = conexion.prepareStatement(
            "Insert into Usuario (ID_Usuario, Usuario, Contraseña) Values (?, ?, ?)"
        );          
        
        // Establecer valores de la consulta SQL
        newUs.setString(1, UUID.randomUUID().toString());
        newUs.setString(2, getUsuario());
        newUs.setString(3, getContraseña());
        
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
        modeloUsuario.setColumnIdentifiers(new Object[]{"ID_Usuario", "Usuario", "Contraseña"});
        
        try {
            //Creamos un statement para que se conecte con la base y realice una acción         
            Statement statement = conexion.createStatement();
            
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet          
            ResultSet rs = statement.executeQuery("SELECT * FROM Usuario");
            
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloUsuario.addRow(new Object[]{rs.getString("ID_Usuario"), 
                    rs.getString("Usuario"), 
                    rs.getString("contraseña")});
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
                PreparedStatement Actualizarus = conexion.prepareStatement("UPDATE Usuario set Usuario =?, contraseña =? WHERE UUID_Usuario =?");
                Actualizarus.setString(1, getUsuario());
                Actualizarus.setString(2, getContraseña());
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
            PreparedStatement EliminarUsuario = conexion.prepareStatement("DELETE FROM Usuario WHERE ID_Usuario =?");
            EliminarUsuario.setString(1, miId);
            EliminarUsuario.executeUpdate();
        }
        catch(Exception e){
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
     public void cargarDatosTabla(FrmAgregarUsuario vista) {
        
        // Obtenemos la fila seleccionada
        int filaSeleccionada = vista.TablaUsuario.getSelectedRow();
        
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUID_Usuario = vista.TablaUsuario.getValueAt(filaSeleccionada, 0).toString();
            String nombre = vista.TablaUsuario.getValueAt(filaSeleccionada, 1).toString();
            String contraseña = vista.TablaUsuario.getValueAt(filaSeleccionada, 2).toString();

            // Establece los valores en los campos de texto
            
            vista.txtUsuario.setText(nombre);
            vista.txtContra.setText(contraseña);
        }
    }
}
