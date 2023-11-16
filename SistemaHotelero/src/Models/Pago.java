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
public class Pago {
    
    private int idpago;
    private int idreserva;
    private String tipocomprobante;
    private String numcomprobante;
    private double igv;
    private double pagototal;
    private Date fechaemision;
    private Date fechapago;

    public Pago() {
    }

    public Pago(int idpago, int idreserva, String tipocomprobante, String numcomprobante, double igv, double pagototal, Date fechaemision, Date fechapago) {
        this.idpago = idpago;
        this.idreserva = idreserva;
        this.tipocomprobante = tipocomprobante;
        this.numcomprobante = numcomprobante;
        this.igv = igv;
        this.pagototal = pagototal;
        this.fechaemision = fechaemision;
        this.fechapago = fechapago;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getTipocomprobante() {
        return tipocomprobante;
    }

    public void setTipocomprobante(String tipocomprobante) {
        this.tipocomprobante = tipocomprobante;
    }

    public String getNumcomprobante() {
        return numcomprobante;
    }

    public void setNumcomprobante(String numcomprobante) {
        this.numcomprobante = numcomprobante;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getPagototal() {
        return pagototal;
    }

    public void setPagototal(double pagototal) {
        this.pagototal = pagototal;
    }

    public Date getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }
    
}
