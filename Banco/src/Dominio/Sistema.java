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
     public static String ipcliente = "localhost";
     public static int    puertov = 8888; 
     public static int    puertoc = 1111;
     public static int    puertovendedor = 7777;
     public static int    puertocliente = 5555;
   //Datos de Seguridad
     public static String llaveServerBancoVendedor = "seguridad/BancoVendedor/serverBancoVendedor_ks"; 
     public static String llaveServerBancoCliente = "seguridad/BancoCliente/serverBancoCliente_ks"; 
     public static String llaveClientBancoVendedor = "seguridad/BancoVendedor/clientBancoVendedor_ks"; 
     public static String llaveClientBancoCliente = "seguridad/BancoCliente/clientBancoCliente_ks";
     public static String llaveServerVendedor = "seguridad/Vendedor/serverVendedor_ks";
     public static String llaveClientVendedor = "seguridad/Vendedor/clientVendedor_ks";
     public static String llaveServerCliente = "seguridad/Cliente/serverCliente_ks";
     public static String llaveClientCliente = "seguridad/Cliente/clientCliente_ks";
     
     
   //Carga Inicial
    public static void cargaInicialBancoCliente(){
      new DaoCliente().crearXML();
      new DaoCliente().agregarCuenta(new Cuenta("Garry","Bruno","24939724","463728495837206462743","Corriente",500000,new Tarjeta(Integer.toString(("98723643327893").hashCode()),"Visa","4563",1234,0,"02/21")));
      new DaoCliente().agregarCuenta(new Cuenta("Aquiles","Pulido","24782343","283649687512457857320","Ahorro",500000,new Tarjeta(Integer.toString(("12922892832908").hashCode()),"Master Card","8675",5678,0,"05/22")));
    } 
    public static void cargaInicialBancoVendedor(){
      new DaoVendedor().crearXML();
      new DaoVendedor().agregarCuenta(new Cuenta("Wilmer","Pereira","16754232","463234823423323423333","Corriente",500000,new Tarjeta(Integer.toString(("987334544434433").hashCode()),"Visa","7644",2356,0,"07/23")));
    } 
}
