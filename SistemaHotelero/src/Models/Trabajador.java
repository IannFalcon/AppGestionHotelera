/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Iann
 */
public class Trabajador extends Persona{
    
    private double sueldo;
    private String acceso;
    private String usuario;
    private String clave;
    private String estado;

    public Trabajador() {
    }

    public Trabajador(double sueldo, String acceso, String usuario, String clave, String estado) {
        this.sueldo = sueldo;
        this.acceso = acceso;
        this.usuario = usuario;
        this.clave = clave;
        this.estado = estado;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
