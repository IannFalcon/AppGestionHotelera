/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.Consumo;
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
public class ConsumoDAO {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    public Double totalconsumo;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","idreserva","idproducto","Producto","Cantidad","Precio Venta","Estado"};
        String [] registro = new String[7];

        totalregistros = 0;
        totalconsumo = 0.0;
        
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select c.idconsumo,c.idreserva,c.idproducto,p.nombre,c.cantidad,c.precioventa, +"
                + "c.estado from consumo c inner join producto p on c.idproducto = p.idproducto "
                + "where c.idreserva = " + buscar + " order by c.idconsumo desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
           
            while(rs.next()){
                registro [0]=rs.getString("idconsumo");
                registro [1]=rs.getString("idreserva");
                registro [2]=rs.getString("idproducto");
                registro [3]=rs.getString("nombre");
                registro [4]=rs.getString("cantidad");
                registro [5]=rs.getString("precioventa");
                registro [6]=rs.getString("estado");
                
                totalregistros = totalregistros+1;
                totalconsumo = totalconsumo + (rs.getDouble("cantidad") * rs.getDouble("precioventa"));
                modelo.addRow(registro);

            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
    public boolean insertar(Consumo tbl){
        sSQL = "insert into consumo (idreserva,idproducto,cantidad,precioventa,estado) " +
               "values (?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, tbl.getIdreserva());
            pst.setInt(2, tbl.getIdproducto());
            pst.setDouble(3, tbl.getCantidad());
            pst.setDouble(4, tbl.getPrecioventa());
            pst.setString(5, tbl.getEstado());
            
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
    
    public boolean editar(Consumo tbl){
        sSQL="update producto set idreserva=?,idproducto=?,cantidad=?,precioventa=?,estado=? "+
             "where idconsumo = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, tbl.getIdreserva());
            pst.setInt(2, tbl.getIdproducto());
            pst.setDouble(3, tbl.getCantidad());
            pst.setDouble(4, tbl.getPrecioventa());
            pst.setString(5, tbl.getEstado());
            
            pst.setInt(6, tbl.getIdconsumo());

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
    
    public boolean eliminar(Consumo tbl){
        sSQL="delete from consumo where idconsumo=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, tbl.getIdconsumo());
           
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
