/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Cliente;
import Vista.FrmClientes;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CClientes implements MouseListener {
     private FrmClientes vistaus;
    private Cliente ModeloUsuario;
    
    public CClientes (Cliente Cliente, FrmClientes FrmClientes){
        this.ModeloUsuario = Cliente;
        this.vistaus = FrmClientes;
        
        FrmClientes.txtNombre.addMouseListener(this);
        FrmClientes.txtTelefono.addMouseListener(this);
        FrmClientes.TablaUsuario.addMouseListener(this);
        FrmClientes.btnAgregarUsuario.addMouseListener(this);
        FrmClientes.btnActualizar.addMouseListener(this);
        FrmClientes.btnEliminar.addMouseListener(this);
        
        ModeloUsuario.Mostrar(vistaus.TablaUsuario);

    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        //Agregar
        if(e.getSource() == vistaus.btnAgregarUsuario){
            
            String nombre = vistaus.txtNombre.getText().trim();
                    if (nombre.isEmpty()) {
                        throw new IllegalArgumentException("El nombre es obligatorio.");
                    }
                    
                    String Telefono = vistaus.txtTelefono.getText().trim();
                    if (Telefono.isEmpty()) {
                        throw new IllegalArgumentException("El Telefono es obligatorio.");
                    }

         ModeloUsuario.setNombre(vistaus.txtNombre.getText());
            ModeloUsuario.setTelefono((vistaus.txtTelefono.getText()));
            ModeloUsuario.GuardarCliente();     
            ModeloUsuario.Mostrar(vistaus.TablaUsuario);
            
            JOptionPane.showMessageDialog(vistaus, "Usuario Guardado");
            
            
        }
        if(e.getSource() == vistaus.btnActualizar){
            
                // Validación para el nombre
                
                try {
                    String nombre = vistaus.txtNombre.getText().trim();
                    if (nombre.isEmpty()) {
                        throw new IllegalArgumentException("El nombre es obligatorio.");
                    }
                    
                    String Telefono = vistaus.txtTelefono.getText().trim();
                    if (Telefono.isEmpty()) {
                        throw new IllegalArgumentException("El Telefono es obligatorio.");
                    }
                    
                    
                    if (!nombre.matches("[a-zA-Z]+")) {
                        throw new IllegalArgumentException("El nombre solo puede contener letras.");
                    }
                    
                    //ModeloUsuario.setNombre(nombre);
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(vistaus, ex.getMessage());
                    return;
                }
                
               
            
            ModeloUsuario.setNombre(vistaus.txtNombre.getText());
            ModeloUsuario.setTelefono((vistaus.txtTelefono.getText()));
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
