/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import BancoCliente.DaoCliente;
import BancoVendedor.DaoVendedor;

/**
 *
 * @author Junior
 */
public class Sistema {
    //Datos de Conexion 
     public static String ipbancov = "localhost"; 
     public static String ipbancoc = "localhost";
     public static String ipvendedor = "localhost";
     public static int    puertov = 8888; 
     public static int    puertoc = 9999;
     public static int    puertovendedor = 7777;
   //Datos de Seguridad
     public static String llaveServerBancoVendedor = "seguridad/BancoVendedor/serverBancoVendedor_ks"; 
     public static String llaveServerBancoCliente = "seguridad/BancoCliente/serverBancoCliente_ks"; 
     public static String llaveClientBancoVendedor = "seguridad/BancoVendedor/clientBancoVendedor_ks"; 
     public static String llaveClientBancoCliente = "seguridad/BancoCliente/clientBancoCliente_ks";
     public static String llaveServerVendedor = "seguridad/Vendedor/serverVendedor_ks";
     public static String llaveClientVendedor = "seguridad/Vendedor/clientVendedor_ks";
     
     
   //Carga Inicial
    public static void cargaInicialBancoCliente(){
      new DaoCliente().crearXML();
      new DaoCliente().agregarCuenta(new Cuenta("Garry","Bruno","24939724","463728495837206462743","Corriente",500000,new Tarjeta("98723643327893","Visa","4563",1234,0)));
      new DaoCliente().agregarCuenta(new Cuenta("Aquiles","Pulido","24782343","283649687512457857320","Ahorro",500000,new Tarjeta("12922892832908","Master Card","8675",5678,0)));
    } 
    public static void cargaInicialBancoVendedor(){
      new DaoVendedor().crearXML();
      new DaoVendedor().agregarCuenta(new Cuenta("Wilmer","Pereira","16754232","463234823423323423333","Corriente",500000,new Tarjeta("987334544434433","Visa","7644",2356,0)));
    } 
}
