/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.Reserva;
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
public class ReservaDAO {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String [] titulos = {"ID","IdHabitacion","Numero","IdCliente","Cliente","IdTrabajador","Trabajador",
                            "Tipo reserva","FecReserva","FecIngreso","FecSalida","CostoAlojamiento","Estado"};
        String [] registro = new String[13];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select r.idreserva, r.idhabitacion, h.numero, r.idcliente," 
                + "(select nombre from persona where idpersona = r.idcliente) as clienten,"
                + "(select apellido from persona where idpersona = r.idcliente) as clienteap, "
                + "r.idtrabajador, (select nombre from persona where idpersona = r.idtrabajador)as trabajadorn, "
                + "(select apellido from persona where idpersona = r.idtrabajador) as trabajadorap, "
                + "r.tiporeserva, r.fechareserva, r.fechaingreso, r.fechasalida, r.costoalojamiento, r.estado "
                + "from reserva r inner join habitacion h on r.idhabitacion = h.idhabitacion "
                + "where r.fechareserva like '%" + buscar + "%' order by idreserva desc";
        
        try {
            Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
           
            while(rs.next()){
                registro [0]=rs.getString("idreserva");
                registro [1]=rs.getString("idhabitacion");
                registro [2]=rs.getString("numero");
                registro [3]=rs.getString("idcliente");
                registro [4]=rs.getString("clienten") + " " + rs.getString("clienteap");
                registro [5]=rs.getString("idtrabajador");
                registro [6]=rs.getString("trabajadorn") + " " + rs.getString("trabajadorap");
                registro [7]=rs.getString("tiporeserva");
                registro [8]=rs.getString("fechareserva");
                registro [9]=rs.getString("fechaingreso");
                registro [10]=rs.getString("fechasalida");
                registro [11]=rs.getString("costoalojamiento");
                registro [12]=rs.getString("estado");                
                totalregistros=totalregistros+1;
                modelo.addRow(registro);

            }
            return modelo;
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
        }
    }
    
    public boolean insertar(Reserva tbl){
        sSQL = "insert into reserva (idhabitacion,idcliente,idtrabajador,tiporeserva,fechareserva,fechaingreso,fechasalida,"
                + "costoalojamiento,estado) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, tbl.getIdhabitacion());
            pst.setInt(2, tbl.getIdcliente());
            pst.setInt(3, tbl.getIdtrabajador());
            pst.setString(4, tbl.getTiporeserva());
            pst.setDate(5, tbl.getFechareserva());
            pst.setDate(6, tbl.getFechaingreso());
            pst.setDate(7, tbl.getFechasalida());
            pst.setDouble(8, tbl.getCostoalojamiento());
            pst.setString(9, tbl.getEstado());
            
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
    
    public boolean editar(Reserva tbl){
        sSQL="update reserva set idhabitacion=?,idcliente=?,idtrabajador=?,tiporeserva=?,"
                + "fechareserva=?,fechaingreso=?,fechasalida=?,costoalojamiento=?,estado=?"+
             "where idreserva = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, tbl.getIdhabitacion());
            pst.setInt(2, tbl.getIdcliente());
            pst.setInt(3, tbl.getIdtrabajador());
            pst.setString(4, tbl.getTiporeserva());
            pst.setDate(5, tbl.getFechareserva());
            pst.setDate(6, tbl.getFechaingreso());
            pst.setDate(7, tbl.getFechasalida());
            pst.setDouble(8, tbl.getCostoalojamiento());
            pst.setString(9, tbl.getEstado());
            
            pst.setInt(10, tbl.getIdreserva());

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
    
    public boolean eliminar(Reserva tbl){
        sSQL="delete from reserva where idreserva=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, tbl.getIdreserva());
           
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
    
    public boolean pagar(Reserva tbl){
        sSQL="update reserva set estado='Pagada'"+
             "where idreserva = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setInt(1, tbl.getIdreserva());

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
