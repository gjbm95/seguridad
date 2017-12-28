/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoCliente;

import BancoVendedor.DaoVendedor;
import Dominio.Cuenta;
import java.util.Scanner;

/**
 *
 * @author Junior
 */
public class ControladorC {
    
    
      public static boolean debitarTarjeta(String numero, String codigo,String vence,String tipo,float cantidad,int cedula){
          Cuenta cuenta = new DaoCliente().obtenerCuenta(numero,codigo,vence);
          if(cuenta != null){
           cuenta.getTarjeta().setSaldo(cuenta.getTarjeta().getSaldo()+cantidad);
           new DaoCliente().eliminarCuenta(cuenta.getNumerocuenta());
           new DaoCliente().agregarCuenta(cuenta);
           System.out.println("Transaccion Exitosa!");
           return true;
          }else{ 
           System.out.println("Transaccion Fallida!. Hay un dato erroneo!");
           return false; 
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
      
      public void ConocerSaldo(){
      
       Scanner sc = new Scanner(System.in); 

    System.out.println("Por favor ingrese la cedula");

          String cedula = sc.nextLine(); 
        while(cedula.trim().length()==0){
         System.out.println("Por favor ingrese la cedula");
          cedula = sc.nextLine(); 
        
        }
        Cuenta cuenta = new DaoCliente().obtenerCuentaCedula(cedula);
        
        if(cuenta != null){
            System.out.println("La cuenta esta a nombre de "+cuenta.getNombre()+cuenta.getApellido());
            System.out.println("La cedula es "+cuenta.getCedula());
            System.out.println("El numero de cuenta es "+cuenta.getNumerocuenta());
            System.out.println("El tipo de cuenta es "+cuenta.getTipocuenta());
            System.out.println("El saldo en la cuenta es "+cuenta.getSaldo());
          }else{ 
           System.out.println("Consulta Fallida!. Hay uno o mas datos erroneos!");
           
          }
       
      }
      
}
