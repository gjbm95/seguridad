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
public class Cuenta {
      
      private String nombre; 
      private String apellido; 
      private String numerocuenta; 
      private String tipocuenta;
      private float saldo; 
      private Tarjeta tarjeta;

      public Cuenta (){

      }

    public Cuenta(String nombre, String apellido, String numerocuenta, String tipocuenta,float saldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numerocuenta = numerocuenta;
        this.tipocuenta = tipocuenta;
        this.saldo = saldo;
        
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

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
      
      
      
      
      
}
