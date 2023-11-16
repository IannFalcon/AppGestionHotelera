/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Iann
 */
public class Habitacion {
    
    private int idhabitacion;
    private String numero;
    private String piso;
    private String descripcion;
    private double precio;
    private String estado;
    private String tipohabitacion;

    public Habitacion() {
    }

    public Habitacion(int idhabitacion, String numero, String piso, String descripcion, double precio, String estado, String tipohabitacion) {
        this.idhabitacion = idhabitacion;
        this.numero = numero;
        this.piso = piso;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.tipohabitacion = tipohabitacion;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipohabitacion() {
        return tipohabitacion;
    }

    public void setTipohabitacion(String tipohabitacion) {
        this.tipohabitacion = tipohabitacion;
    }
    
}
