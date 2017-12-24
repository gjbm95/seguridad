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
    
    public Tarjeta (){
    
    
    }

    public Tarjeta(String numero, String marca, String codseguridad) {
        this.numero = numero;
        this.marca = marca;
        this.codseguridad = codseguridad;
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
    
    
}
