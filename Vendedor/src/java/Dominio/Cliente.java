/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class Cliente {
    private int status;
    private String nombre; 
    private String apellido; 
    private String cedula; 
    private String correo; 
    private String contrasena;
    private ArrayList<Factura> facturas;
    private Factura facturaactual; 

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String cedula, String correo, String contrasena,int status) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasena = contrasena;
        this.status=status;
    }

    public Cliente(String nombre, String apellido, String cedula, String correo, String contrasena,int status,ArrayList<Factura> facturas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasena = contrasena;
        this.status=status;
        this.facturas = facturas;
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

    public String getCedula() {
        return cedula;
    }
    
    

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Factura getFacturaactual() {
        return facturaactual;
    }

    public void setFacturaactual(Factura facturaactual) {
        this.facturaactual = facturaactual;
    }
    
    
    
    
    
    
    
     
     
}
