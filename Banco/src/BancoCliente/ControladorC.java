/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoCliente;

import BancoVendedor.DaoVendedor;
import Dominio.Cuenta;

/**
 *
 * @author Junior
 */
public class ControladorC {
    
    
      public static String debitarTarjeta(String numero, String codigo,String vence,String tipo,float cantidad,int cedula){
          Cuenta cuenta = new DaoCliente().obtenerCuenta(Integer.parseInt(numero),codigo,vence);
          if(cuenta != null){
            if (cuenta.getSaldo()>cantidad){   
               cuenta.getTarjeta().setSaldo(cuenta.getTarjeta().getSaldo()+cantidad);
               new DaoCliente().eliminarCuenta(cuenta.getNumerocuenta());
               new DaoCliente().agregarCuenta(cuenta);
               System.out.println("Transaccion Exitosa!");
               return "Exitoso";
            }else
               return "Saldo insuficiente";
          }else{ 
           System.out.println("Transaccion Fallida!. Hay un dato erroneo!");
           return "Fallido"; 
          }
      }
      
      public static boolean depositarCuenta(String numero,float cantidad){
          Cuenta cuenta = new DaoCliente().obtenerCuenta(numero);
          if(cuenta != null){
           cuenta.setSaldo(cuenta.getSaldo()+cantidad);
           new DaoVendedor().eliminarCuenta(cuenta.getNumerocuenta());
           new DaoVendedor().agregarCuenta(cuenta);
           System.out.println("La transferencia ha sido Exitosa!");
           return true;
          }else{ 
           System.out.println("Transferencia Fallida!. Hay un dato erroneo!");
           return false; 
          }
      }
      
}
