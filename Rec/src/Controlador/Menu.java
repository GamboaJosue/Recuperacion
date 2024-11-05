/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.frmMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Menu implements MouseListener {
    
    frmMenu vistaa;


    public Menu(frmMenu vista) {
        
         this.vistaa = vista;
     
        vistaa.btnAgregarUsuario.addMouseListener(this);
        vistaa.btnAgregarCliente.addMouseListener(this);
        vistaa.AgregarVehiculos.addMouseListener(this);
        vistaa.AgregarProblema.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vistaa.btnAgregarUsuario){
            Vista.FrmAgregarUsuario.initfrmAgregarusuarios();
            vistaa.dispose();
        }
        
        if(e.getSource() == vistaa.btnAgregarCliente){
            Vista.FrmClientes.initfrmCliente();
            vistaa.dispose();
        }
         if(e.getSource() == vistaa.AgregarVehiculos){
            Vista.FrmAgregarVehiculo.initVehiculo();
            vistaa.dispose();
        }
         
         
         if(e.getSource() == vistaa.AgregarProblema){
            Vista.AgregarProblemas.initAgregarProblemas();
            vistaa.dispose();
        
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
