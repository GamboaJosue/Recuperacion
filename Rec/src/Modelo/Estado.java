/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.JComboBox;
import java.sql.*;


/**
 *
 * @author Usuario
 */
public class Estado {
    private String ID_Estado;
    private String Estado;
    
    
      public String getID_Estado() {
        return ID_Estado;
    }

    public void setID_Estado(String ID_Estado) {
        this.ID_Estado = ID_Estado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
     public Estado(){
    
    }
    
    public Estado(String id_comite, String comite) {
    this.ID_Estado = id_comite;
    this.Estado = comite;
    }
    
    @Override
    public String toString() {
        return Estado; 
    }
    
    public void CargarComboboxEstado(JComboBox comboBox) {
    Connection conexion = ClaseConexion.getConexion();

    comboBox.removeAllItems();
    
    try {
        Statement statemente = conexion.createStatement();
        ResultSet rs = statemente.executeQuery("SELECT * FROM Estado");
        System.out.println("asi");

        
        while (rs.next()) {
            String id = rs.getString("ID_Estado");
            String comite = rs.getString("Estado");
            comboBox.addItem(new Estado(id, comite)); 
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

  
    
}
