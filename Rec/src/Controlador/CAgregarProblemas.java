/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Vista.AgregarProblemas;
import Modelo.ClaseConexion;
import Modelo.Estado;
import Modelo.Vehiculo;
import Modelo.Problema;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CAgregarProblemas implements MouseListener {
    private AgregarProblemas vistaus;
    private Problema ModeloProblema;
    private Vehiculo ModeloVehiculo;
    private Estado ModeloEstado;

    public CAgregarProblemas(Problema modeloProblema, Vehiculo modeloVehiculo, Estado modeloEstado, AgregarProblemas Vistaus ){
         this.vistaus = Vistaus;
        this.ModeloProblema = modeloProblema;
        
        
        Vistaus.TablaUsuario.addMouseListener(this);
        Vistaus.btnAgregarUsuario.addMouseListener(this);
        Vistaus.btnActualizar.addMouseListener(this);
        Vistaus.btnEliminar.addMouseListener(this);
        
        
        if (ModeloVehiculo == null) {
            ModeloVehiculo = new Modelo.Vehiculo();
        }
        ModeloVehiculo.CargarComboboxVehiculo(vistaus.jComboBox1);


        if (ModeloEstado == null) {
            ModeloEstado = new Modelo.Estado();
        }
        ModeloEstado.CargarComboboxEstado(vistaus.jComboBox2);



        
        modeloProblema.Mostrar(vistaus.TablaUsuario);
        
         
        vistaus.jComboBox1.addActionListener(e ->{
            if(e.getSource()== vistaus.jComboBox1){
                Vehiculo selectedGrado =(Vehiculo) vistaus.jComboBox1.getSelectedItem();
                if(selectedGrado != null){
                    String idg = selectedGrado.getID_Vehiculo();
                    ModeloVehiculo.setID_Vehiculo(idg);
        System.out.println("Esto es lo que se manda a traer de la base de datos (Grado): " + selectedGrado.getID_Vehiculo()+ " con ID: " + idg);

                }
            }
        });
         
        vistaus.jComboBox2.addActionListener(e ->{
            if(e.getSource()== vistaus.jComboBox2){
                Estado selectedGrado =(Estado) vistaus.jComboBox2.getSelectedItem();
                if(selectedGrado != null){
                    String idg = selectedGrado.getID_Estado();
                    ModeloEstado.setID_Estado(idg);
                                        System.out.println("Esto es lo que se manda a traer de la base de datos (Grado): " + selectedGrado.getID_Estado()+ " con ID: " + idg);

                }
            }
        });
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
        }
        
         ModeloProblema.setProblema(vistaus.txtUsuario.getText());
         ModeloProblema.setDescripcion(vistaus.txtContra.getText());
         ModeloProblema.GuardarUsuario();     
         ModeloProblema.Mostrar(vistaus.TablaUsuario);
            
            JOptionPane.showMessageDialog(vistaus, "Usuario Guardado");
        
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
