/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import BancoCliente.Envio;
import BancoCliente.VistaC;
import BancoVendedor.VistaV;
import Cliente.RealizarDescarga;
import Cliente.ReciboArchivo;
import Dominio.Sistema;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class Banco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int estado =1; 
        while (estado !=0){
            Scanner sc = new Scanner(System.in);
            System.out.println("Bienvenido: Seleccione una modalidad");
            System.out.println("------------------------------------------------");
            System.out.println("1 - Banco Cliente");
            System.out.println("2 - Banco Vendedor");
            System.out.println("3 - Cliente");
            System.out.println("4 - Salir");
            System.out.println("------------------------------------------------");
            System.out.println("Ingresa tu opcion: ");
            String i = sc.nextLine();
            switch(i){
            case "1":{
                    Sistema.cargaInicialBancoCliente();
                    new BancoCliente.Control().start();
                    VistaC.menu();
            break;
            }
            case "2":{
                    Sistema.cargaInicialBancoVendedor();
                    new BancoVendedor.Control().start();
                    VistaV.menu();
            break;
            }
            case "3":{
                    new Cliente.Control().start();
            break;
            }
            case "4": {
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
