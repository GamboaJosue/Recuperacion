/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Vehiculo;
import Vista.FrmAgregarVehiculo;
import Modelo.Cliente;
import javax.swing.JOptionPane;




/**
 *
 * @author Usuario
 */
public class CAgregarVehiculo implements MouseListener{
    
    private FrmAgregarVehiculo vistaus;
    private Vehiculo ModeloUsuario;
    private Cliente ModeloCliente;
    
    public CAgregarVehiculo(Vehiculo Vehiculo, FrmAgregarVehiculo FrmAgregarVehiculoo, Cliente Clientee){
        
        this.ModeloUsuario = Vehiculo;
        this.vistaus = FrmAgregarVehiculoo;
        
        this.ModeloCliente = Clientee;

        
        this.vistaus.cbCliente.addMouseListener(this);
        this.ModeloCliente.CargarComboboxCliente(vistaus.cbCliente);
        
        FrmAgregarVehiculoo.TablaUsuario.addMouseListener(this);
        FrmAgregarVehiculoo.btnActualizar.addMouseListener(this);
        FrmAgregarVehiculoo.btnAgregarUsuario.addMouseListener(this);
        FrmAgregarVehiculoo.btnEliminar.addMouseListener(this);
        FrmAgregarVehiculoo.txtNombre.addMouseListener(this);
        FrmAgregarVehiculoo.txtTelefono.addMouseListener(this);
        FrmAgregarVehiculoo.cbCliente.addMouseListener(this);
        FrmAgregarVehiculoo.txtAño.addMouseListener(this);
        
        ModeloUsuario.Mostrar(vistaus.TablaUsuario);

    

        
        vistaus.cbCliente.addActionListener(e ->{
            if(e.getSource()== vistaus.cbCliente){
                Cliente selectedGrado =(Cliente) vistaus.cbCliente.getSelectedItem();
                if(selectedGrado != null){
                    String idg = selectedGrado.getID_Cliente();
                    ModeloCliente.setID_Cliente(idg);
                    System.out.println("Esto es lo que se manda a traer de la base de datos (Grado): " + selectedGrado.getID_Cliente()+ " con ID: " + idg);
                }
            }
        });
        
       
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
            ModeloUsuario.setMarca(vistaus.txtNombre.getText());
            ModeloUsuario.setModelo((vistaus.txtTelefono.getText()));
            ModeloUsuario.setAño((vistaus.txtAño.getText()));


            ModeloUsuario.GuardarUsuario();
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
                
               
            
            ModeloUsuario.setMarca(vistaus.txtNombre.getText());
            ModeloUsuario.setModelo((vistaus.txtTelefono.getText()));
            ModeloUsuario.setAño((vistaus.txtAño.getText()));
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
