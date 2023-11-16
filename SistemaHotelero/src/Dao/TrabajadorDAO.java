/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.Trabajador;
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
public class TrabajadorDAO {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","Nombres","Apellidos","Documento","Nro.Documento","Telefono","Email","Acceso","Usuario","Clave","Estado"};
        String [] registro = new String[11];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona, p.nombre, p.apellido, p.tipodocumento, p.numdocumento, p.telefono, p.email, t.acceso, "
                + "t.usuario, t.clave, t.estado from persona p inner join trabajador t on p.idpersona = t.idpersona "
                + "where numdocumento like '%" + buscar + "%' order by idpersona desc";
        
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
                registro [7]=rs.getString("acceso");
                registro [8]=rs.getString("usuario");
                registro [9]=rs.getString("clave");     
                registro [10]=rs.getString("estado");           
                totalregistros=totalregistros+1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
    public boolean insertar(Trabajador tbl){
        sSQL = "insert into persona (nombre, apellido, tipodocumento, numdocumento, telefono, email) " +
               "values (?,?,?,?,?,?)";
        sSQL2 = "insert into trabajador (idpersona, acceso, usuario, clave, estado) " +
               "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, tbl.getNombre());
            pst.setString(2, tbl.getApellido());
            pst.setString(3, tbl.getTipodocumento());
            pst.setString(4, tbl.getNumdocumento());
            pst.setString(5, tbl.getTelefono());
            pst.setString(6, tbl.getEmail());
            
            pst2.setString(1, tbl.getAcceso());
            pst2.setString(2, tbl.getUsuario());
            pst2.setString(3, tbl.getClave());
            pst2.setString(4, tbl.getEstado());
            
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
    
    public boolean editar(Trabajador tbl){
        sSQL="update persona set nombre=?,apellido=?,tipodocumento=?, numdocumento=?, " +
             "telefono=?, email=? where idpersona = ?";
        sSQL2="update trabajador set acceso=?,usuario=?,clave=?,estado=? "+
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
            
            pst2.setString(1, tbl.getAcceso());
            pst2.setString(2, tbl.getUsuario());
            pst2.setString(3, tbl.getClave());
            pst2.setString(4, tbl.getEstado());
            pst2.setInt(5, tbl.getIdpersona());
            
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
    
    public boolean eliminar(Trabajador tbl){
        sSQL="delete from trabajador where idpersona=?";
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
    
    public DefaultTableModel login(String usuario, String clave) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","Nombres","Apellidos","Acceso","Usuario","Clave","Estado"};
        String [] registro = new String[7];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona, p.nombre, p.apellido, t.acceso, t.usuario, t.clave, t.estado "
                + "from persona p inner join trabajador t on p.idpersona = t.idpersona "
                + "where t.usuario = '" + usuario + "' and t.clave = '" + clave + "' and estado = 'A'";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
           
            while(rs.next()){
                registro [0]=rs.getString("idpersona");
                registro [1]=rs.getString("nombre");
                registro [2]=rs.getString("apellido");
                registro [3]=rs.getString("acceso");
                registro [4]=rs.getString("usuario");
                registro [5]=rs.getString("clave");     
                registro [6]=rs.getString("estado");           
                totalregistros=totalregistros+1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
}
