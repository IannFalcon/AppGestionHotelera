/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Iann
 */
public class Persona {
    
    private int idpersona;
    private String nombre;
    private String apellido;
    private String tipodocumento;
    private String numdocumento;
    private String telefono;
    private String email;

    public Persona() {
    }

    public Persona(int idpersona, String nombre, String apellido, String tipodocumento, String numdocumento, String telefono, String email) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipodocumento = tipodocumento;
        this.numdocumento = numdocumento;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
