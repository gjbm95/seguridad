/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Junior
 */
public class Tarjeta {
    
    
    private String numero; 
    private String marca; 
    private String codseguridad; 
    private float saldo; 
    private int clave; 
    private String fechavenc;
    
    public Tarjeta (){
    
    
    }

    public Tarjeta(String numero, String marca, String codseguridad,int clave,float saldo,String fechavenc) {
        this.numero = numero;
        this.marca = marca;
        this.codseguridad = codseguridad;
        this.clave = clave;
        this.saldo = saldo;
        this.fechavenc=fechavenc;
    }

    
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodseguridad() {
        return codseguridad;
    }

    public void setCodseguridad(String codseguridad) {
        this.codseguridad = codseguridad;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getFechavenc() {
        return fechavenc;
    }

    public void setFechavenc(String fechavenc) {
        this.fechavenc = fechavenc;
    }
    
    
    
}
