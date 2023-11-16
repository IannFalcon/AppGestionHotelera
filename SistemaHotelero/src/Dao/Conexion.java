/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Iann
 */
public class Conexion {
    
    public String bd = "bd_hotel";
    public String url = "jdbc:mysql://127.0.0.1/" + bd;
    public String user = "root";
    public String pass = "";
    
    public Conexion(){
    }
    
    public Connection conectar(){
        Connection link = null;
        
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return link;
    }
    
}
