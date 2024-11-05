/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Vista.FrmAgregarUsuario;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CAgregarUsuario implements MouseListener {
    private FrmAgregarUsuario vistaus;
    private Usuario ModeloUsuario;
    
    public CAgregarUsuario(Usuario modeloUsuario, FrmAgregarUsuario Vistaus){

        this.vistaus = Vistaus;
        this.ModeloUsuario = modeloUsuario;
        
        Vistaus.txtContra.addMouseListener(this);
        Vistaus.txtUsuario.addMouseListener(this);
        Vistaus.TablaUsuario.addMouseListener(this);
        Vistaus.btnAgregarUsuario.addMouseListener(this);
        Vistaus.btnActualizar.addMouseListener(this);
        Vistaus.btnEliminar.addMouseListener(this);

        
        ModeloUsuario.Mostrar(vistaus.TablaUsuario);


     }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        //Agregar
        if(e.getSource() == vistaus.btnAgregarUsuario){
            
            //Validaciones para el nombre
            try {
                String nombre = vistaus.txtUsuario.getText().trim();
                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("El nombre es obligatorio.");
                }
                
                if (!nombre.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("El nombre solo puede contener letras.");
                }
                
                //ModeloUsuario.setNombre(nombre);
            
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(vistaus, ex.getMessage());
                return;
            } 
            
           
            
            //Validación para la contraseña
            
            try {
                String contrasena = vistaus.txtContra.getText();                
                if (contrasena.isEmpty()) {
                    throw new IllegalArgumentException("El nombre es obligatorio.");
                }
                if (contrasena.length() < 7) {
                    throw new IllegalArgumentException("La contraseña debe tener más de 7 caracteres.");
                }
                
                if (!contrasena.matches(".*[a-z].*") || !contrasena.matches(".*[A-Z].*")) {
                    throw new IllegalArgumentException("La contraseña debe contener al menos una letra mayúscula y una letra minúscula.");
                }
            
                //ModeloUsuario.setContraseña(ModeloUsuario.SHA256(contrasena));
            
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(vistaus, ex.getMessage());
                return;
            }
            
            ModeloUsuario.setUsuario(vistaus.txtUsuario.getText());
            ModeloUsuario.setContraseña(ModeloUsuario.SHA256(vistaus.txtContra.getText()));
            ModeloUsuario.GuardarUsuario();     
            ModeloUsuario.Mostrar(vistaus.TablaUsuario);
            
            JOptionPane.showMessageDialog(vistaus, "Usuario Guardado");
                        
        }
        
        //Actualizar
        
         if(e.getSource() == vistaus.btnActualizar){
            
                // Validación para el nombre
                
                try {
                    String nombre = vistaus.txtUsuario.getText().trim();
                    if (nombre.isEmpty()) {
                        throw new IllegalArgumentException("El nombre es obligatorio.");
                    }
                    
                    if (!nombre.matches("[a-zA-Z]+")) {
                        throw new IllegalArgumentException("El nombre solo puede contener letras.");
                    }
                    
                    //ModeloUsuario.setNombre(nombre);
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(vistaus, ex.getMessage());
                    return;
                }
                // Validación para la contraseña
                
                try {
                    String contrasena = vistaus.txtContra.getText();
                    if (contrasena.length() < 7) {
                        throw new IllegalArgumentException("La contraseña debe tener más de 7 caracteres.");
                    }
                    
                    if (!contrasena.matches(".*[a-z].*") || !contrasena.matches(".*[A-Z].*")) {
                        throw new IllegalArgumentException("La contraseña debe contener al menos una letra mayúscula y una letra minúscula.");
                    }
                    
                    //ModeloUsuario.setContraseña(ModeloUsuario.SHA256(contrasena));
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(vistaus, ex.getMessage());
                    return;
                }
            
            ModeloUsuario.setUsuario(vistaus.txtUsuario.getText());
            ModeloUsuario.setContraseña(ModeloUsuario.SHA256(vistaus.txtContra.getText()));
            ModeloUsuario.ActualizarUsuario(vistaus.TablaUsuario);
            ModeloUsuario.Mostrar(vistaus.TablaUsuario);
            
            JOptionPane.showMessageDialog(vistaus, "Usuario actualizado.");

  
        }
        
        // Botón para eliminar al usuario
       
        if(e.getSource() == vistaus.btnEliminar){
            
             System.out.println("HOLAA");
            try{ 
            ModeloUsuario.EliminarUsuario(vistaus.TablaUsuario);
            ModeloUsuario.Mostrar(vistaus.TablaUsuario);
            }
            catch(IllegalArgumentException ex){
            System.out.println("este es el error metodo de eliminar" + ex);

        }   

        }
        
             
        if(e.getSource() == vistaus.TablaUsuario){
            ModeloUsuario.cargarDatosTabla(vistaus);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
