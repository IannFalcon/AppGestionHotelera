/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Iann
 */
public class Cliente extends Persona{
    
    private String codcliente;

    public Cliente() {
    }

    public Cliente(String codcliente) {
        this.codcliente = codcliente;
    }

    public String getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(String codcliente) {
        this.codcliente = codcliente;
    }
    
}
