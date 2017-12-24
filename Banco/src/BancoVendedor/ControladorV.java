/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoVendedor;

import BancoCliente.DaoCliente;
import Dominio.Cuenta;

/**
 *
 * @author Junior
 */
public class ControladorV {
    
    
     public static boolean debitarTarjeta(String numero, String codigo,int clave,float cantidad){
          Cuenta cuenta = new DaoCliente().obtenerCuenta(Integer.parseInt(numero),codigo,clave);
          if(cuenta != null){
           cuenta.getTarjeta().setSaldo(cuenta.getTarjeta().getSaldo()+cantidad);
           new DaoCliente().eliminarCuenta(Integer.parseInt(cuenta.getNumerocuenta()));
           new DaoCliente().agregarCuenta(cuenta);
           System.out.println("Transaccion Exitosa!");
           return true;
          }else{ 
           System.out.println("Transaccion Fallida!. Hay un dato erroneo!");
           return false; 
          }
      }
      
      public static boolean depositarCuenta(String numero,float cantidad){
          Cuenta cuenta = new DaoCliente().obtenerCuenta(Integer.parseInt(numero));
          if(cuenta != null){
           cuenta.setSaldo(cuenta.getSaldo()+cantidad);
           new DaoVendedor().eliminarCuenta(Integer.parseInt(cuenta.getNumerocuenta()));
           new DaoVendedor().agregarCuenta(cuenta);
           System.out.println("La transferencia ha sido Exitosa!");
           return true;
          }else{ 
           System.out.println("Transferencia Fallida!. Hay un dato erroneo!");
           return false; 
          }
      }
}
