package Controlador;

import Modelo.Usuario;
import Vista.FrmLogin;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Login implements MouseListener {
    
    Usuario ModeloUsuario;
    FrmLogin Vistalogin;
    
    public Login(Usuario usuarios, FrmLogin Login){
        
        this.ModeloUsuario = usuarios;
        this.Vistalogin = Login;
        
        Login.btnEntrar.addMouseListener(this);
        Login.lblContra.addMouseListener(this);
        Login.lblUsuario.addMouseListener(this);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == Vistalogin.btnEntrar) {
            
            ModeloUsuario.setUsuario(Vistalogin.lblUsuario.getText());
            ModeloUsuario.setContraseña(ModeloUsuario.SHA256(Vistalogin.lblContra.getText()));
        
           

        // Validación de la contraseña en el login
        try {
                String contrasena = Vistalogin.lblContra.getText();
                ModeloUsuario.setContraseña(ModeloUsuario.SHA256(contrasena));

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(Vistalogin, ex.getMessage());
                return;
            } 
        
        boolean comprobar = ModeloUsuario.IniciarSesion();
        
        if (comprobar) {
            JOptionPane.showMessageDialog(Vistalogin, "¡Bienvenido!");
            Vista.frmMenu.initfrmMenu(); 
            Vistalogin.dispose();
        }
        else {
            JOptionPane.showMessageDialog(Vistalogin, "Credenciales inválidas");
             }
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
