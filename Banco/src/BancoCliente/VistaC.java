/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoCliente;
import java.util.Scanner;
/**
 *
 * @author Junior
 */
public class VistaC {
    
    
    
    public static void menu(){
       int estado=1; 
       while(estado!=0){
            Scanner sc = new Scanner(System.in);
            System.out.println("Bienvenido al Banco del Cliente");
            System.out.println("------------------------------------------------");
            System.out.println("1 - Consultar Cuenta");
            System.out.println("2 - Salir");
            System.out.println("------------------------------------------------");
            System.out.println("Ingresa tu opcion: ");
            String i = sc.nextLine();
            switch(i){
            case "1":{
               ControladorC cont= new ControladorC();
               cont.ConocerSaldo();
            break;
            }
            case "2": {
            // SALIR
              estado =0;
              System.exit(0);
            break;
            }
            default:
                System.out.println("Opcion erronea");
            }
       }
    
    
    }
    
    
}
