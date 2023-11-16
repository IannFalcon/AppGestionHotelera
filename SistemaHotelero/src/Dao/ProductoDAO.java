/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Iann
 */
public class ProductoDAO {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","Producto","Descripcion","Precio"};
        String [] registro = new String[4];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from producto where nombre like '%" + buscar + "%' order by idproducto";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
           
            while(rs.next()){
                registro [0]=rs.getString("idproducto");
                registro [1]=rs.getString("nombre");
                registro [2]=rs.getString("descripcion");
                registro [3]=rs.getString("precio");
                totalregistros=totalregistros+1;
                modelo.addRow(registro);

            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
    public boolean insertar(Productos tbl){
        sSQL = "insert into producto (nombre, descripcion, precio) " +
               "values (?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, tbl.getNombre());
            pst.setString(2, tbl.getDescripcion());
            pst.setDouble(3, tbl.getPrecio());
            
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
    
    public boolean editar(Productos tbl){
        sSQL="update producto set nombre=?,descripcion=?,precio=? "+
             "where idproducto = ?";
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setString(1, tbl.getNombre());
            pst.setString(2, tbl.getDescripcion());
            pst.setDouble(3, tbl.getPrecio());
            
            pst.setInt(4, tbl.getIdproducto());

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
    
    public boolean eliminar(Productos tbl){
        sSQL="delete from producto where idproducto=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, tbl.getIdproducto());
           
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
