/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.Habitacion;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Iann
 */
public class HabitacionDAO {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","Número","Piso","Descripción","Precio","Estado","Tipo habitación"};
        String [] registro = new String[7];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from habitacion where piso like '%" + buscar + "%' order by idhabitacion";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
           
            while(rs.next()){
                registro [0]=rs.getString("idhabitacion");
                registro [1]=rs.getString("numero");
                registro [2]=rs.getString("piso");
                registro [3]=rs.getString("descripcion");
                registro [4]=rs.getString("precio");
                registro [5]=rs.getString("estado");
                registro [6]=rs.getString("tipohabitacion");

                totalregistros=totalregistros+1;
                modelo.addRow(registro);

            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
    public DefaultTableModel mostrarVista(String buscar) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","Número","Piso","Descripción","Precio","Estado","Tipo habitación"};
        String [] registro = new String[7];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from habitacion where numero like '%" + buscar + "%' and estado = 'Disponible' order by idhabitacion";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
           
            while(rs.next()){
                registro [0]=rs.getString("idhabitacion");
                registro [1]=rs.getString("numero");
                registro [2]=rs.getString("piso");
                registro [3]=rs.getString("descripcion");
                registro [4]=rs.getString("precio");
                registro [5]=rs.getString("estado");
                registro [6]=rs.getString("tipohabitacion");

                totalregistros=totalregistros+1;
                modelo.addRow(registro);

            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
    public boolean insertar(Habitacion tbl){
        sSQL = "insert into habitacion (numero, piso, descripcion, precio, estado, tipohabitacion) " +
               "values (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, tbl.getNumero());
            pst.setString(2, tbl.getPiso());
            pst.setString(3, tbl.getDescripcion());
            pst.setDouble(4, tbl.getPrecio());
            pst.setString(5, tbl.getEstado());
            pst.setString(6, tbl.getTipohabitacion());
            
            int n = pst.executeUpdate();
            
            if(n!=0){
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }        
    }
    
    public boolean editar(Habitacion tbl){
        sSQL="update habitacion set numero=?,piso=?,descripcion=?,precio=?,estado=?,tipohabitacion=? "+
             "where idhabitacion = ?";
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setString(1, tbl.getNumero());
            pst.setString(2, tbl.getPiso());
            pst.setString(3, tbl.getDescripcion());
            pst.setDouble(4, tbl.getPrecio());
            pst.setString(5, tbl.getEstado());
            pst.setString(6, tbl.getTipohabitacion());
            pst.setInt(7, tbl.getIdhabitacion());

            int n=pst.executeUpdate();

            if (n!=0){
                return true;
            }
            else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }      
    }
    
    public boolean eliminar(Habitacion tbl){
        sSQL="delete from habitacion where idhabitacion=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, tbl.getIdhabitacion());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
    }
    
    public boolean desocupar(Habitacion tbl){
        sSQL = "update habitacion set estado = 'Disponible' where idhabitacion = ?";
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);

            pst.setInt(1, tbl.getIdhabitacion());

            int n=pst.executeUpdate();

            if (n!=0){
                return true;
            }
            else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }      
    }
    
    public boolean ocupar(Habitacion tbl){
        sSQL = "update habitacion set estado = 'Ocupado' where idhabitacion = ?";
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);

            pst.setInt(1, tbl.getIdhabitacion());

            int n=pst.executeUpdate();

            if (n!=0){
                return true;
            }
            else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }      
    }
    
}
