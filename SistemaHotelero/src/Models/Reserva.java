/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Iann
 */
public class Reserva {
    
    private int idreserva;
    private int idhabitacion;
    private int idcliente;
    private int idtrabajador;
    private String tiporeserva;
    private Date fechareserva;
    private Date fechaingreso;
    private Date fechasalida;
    private double costoalojamiento;
    private String estado;

    public Reserva() {
    }

    public Reserva(int idreserva, int idhabitacion, int idcliente, int idtrabajador, String tiporeserva, Date fechareserva, Date fechaingreso, Date fechasalida, double costoalojamiento, String estado) {
        this.idreserva = idreserva;
        this.idhabitacion = idhabitacion;
        this.idcliente = idcliente;
        this.idtrabajador = idtrabajador;
        this.tiporeserva = tiporeserva;
        this.fechareserva = fechareserva;
        this.fechaingreso = fechaingreso;
        this.fechasalida = fechasalida;
        this.costoalojamiento = costoalojamiento;
        this.estado = estado;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public String getTiporeserva() {
        return tiporeserva;
    }

    public void setTiporeserva(String tiporeserva) {
        this.tiporeserva = tiporeserva;
    }

    public Date getFechareserva() {
        return fechareserva;
    }

    public void setFechareserva(Date fechareserva) {
        this.fechareserva = fechareserva;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public double getCostoalojamiento() {
        return costoalojamiento;
    }

    public void setCostoalojamiento(double costoalojamiento) {
        this.costoalojamiento = costoalojamiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
