/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.Cliente;
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
public class ClienteDAO {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","Nombres","Apellidos","Documento","Nro.Documento","Telefono","Email","Código"};
        String [] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona, p.nombre, p.apellido, p.tipodocumento, p.numdocumento, p.telefono, p.email, c.codcliente "+ 
               "from persona p inner join cliente c on p.idpersona = c.idpersona where numdocumento like '%" + buscar + 
               "%' order by idpersona desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
           
            while(rs.next()){
                registro [0]=rs.getString("idpersona");
                registro [1]=rs.getString("nombre");
                registro [2]=rs.getString("apellido");
                registro [3]=rs.getString("tipodocumento");
                registro [4]=rs.getString("numdocumento");
                registro [5]=rs.getString("telefono");                
                registro [6]=rs.getString("email");
                registro [7]=rs.getString("codcliente");
                totalregistros=totalregistros+1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
    public boolean insertar(Cliente tbl){
        sSQL = "insert into persona (nombre, apellido, tipodocumento, numdocumento, telefono, email) " +
               "values (?,?,?,?,?,?)";
        sSQL2 = "insert into cliente (idpersona, codcliente) " +
               "values ((select idpersona from persona order by idpersona desc limit 1),?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, tbl.getNombre());
            pst.setString(2, tbl.getApellido());
            pst.setString(3, tbl.getTipodocumento());
            pst.setString(4, tbl.getNumdocumento());
            pst.setString(5, tbl.getTelefono());
            pst.setString(6, tbl.getEmail());
            
            pst2.setString(1, tbl.getCodcliente());
            
            int n = pst.executeUpdate();
            
            if(n!=0){
                int n2 = pst2.executeUpdate();
                if(n2!=0){
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }        
    }
    
    public boolean editar(Cliente tbl){
        sSQL="update persona set nombre=?,apellido=?,tipodocumento=?, numdocumento=?, " +
             "telefono=?, email=?  where idpersona = ?";
        sSQL2="update cliente set codcliente=? "+
             "where idpersona= ?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, tbl.getNombre());
            pst.setString(2, tbl.getApellido());
            pst.setString(3, tbl.getTipodocumento());
            pst.setString(4, tbl.getNumdocumento());
            pst.setString(5, tbl.getTelefono());
            pst.setString(6, tbl.getEmail());
            pst.setInt(7,tbl.getIdpersona());
            
            pst2.setString(1, tbl.getCodcliente());
            pst2.setInt(2,tbl.getIdpersona());
            
            int n = pst.executeUpdate();
            
            if(n!=0){
                int n2 = pst2.executeUpdate();
                if(n2!=0){
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }        
    }
    
    public boolean eliminar(Cliente tbl){
        sSQL="delete from cliente where idpersona=?";
        sSQL2="delete from persona where idpersona=?";
       
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setInt(1,tbl.getIdpersona());
            
            pst2.setInt(1,tbl.getIdpersona());
            
            int n = pst.executeUpdate();
            
            if(n!=0){
                int n2 = pst2.executeUpdate();
                if(n2!=0){
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }    
    }
    
}
