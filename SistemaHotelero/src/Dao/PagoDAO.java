/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.Pago;
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
public class PagoDAO {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","idreserva","Tipo Comprobante","Nro.Comprobante","IGV","Pago Total","Fec.Emisión","Fec.Pago"};
        String [] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from pago where idreserva = " + buscar + " order by idpago desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
           
            while(rs.next()){
                registro [0]=rs.getString("idpago");
                registro [1]=rs.getString("idreserva");
                registro [2]=rs.getString("tipocomprobante");
                registro [3]=rs.getString("numcomprobante");
                registro [4]=rs.getString("igv");
                registro [5]=rs.getString("pagototal");
                registro [6]=rs.getString("fechaemision");
                registro [7]=rs.getString("fechapago");
                totalregistros=totalregistros+1;
                modelo.addRow(registro);

            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
    public boolean insertar(Pago tbl){
        sSQL = "insert into pago (idreserva, tipocomprobante, numcomprobante, igv, pagototal, fechaemision, fechapago) " +
               "values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, tbl.getIdreserva());
            pst.setString(2, tbl.getTipocomprobante());
            pst.setString(3, tbl.getNumcomprobante());
            pst.setDouble(4, tbl.getIgv());
            pst.setDouble(5, tbl.getPagototal());
            pst.setDate(6, tbl.getFechaemision());
            pst.setDate(7, tbl.getFechapago());
            
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
    
    public boolean editar(Pago tbl){
        sSQL="update pago set idreserva=?,tipocomprobante=?,numcomprobante=?,igv=?,pagototal=?,fechaemision=?,fechapago=? "+
             "where idpago = ?";
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setInt(1, tbl.getIdreserva());
            pst.setString(2, tbl.getTipocomprobante());
            pst.setString(3, tbl.getNumcomprobante());
            pst.setDouble(4, tbl.getIgv());
            pst.setDouble(5, tbl.getPagototal());
            pst.setDate(6, tbl.getFechaemision());
            pst.setDate(7, tbl.getFechapago());
            
            pst.setInt(8, tbl.getIdpago());

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
    
    public boolean eliminar(Pago tbl){
        sSQL="delete from pago where idpago=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, tbl.getIdpago());
           
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
