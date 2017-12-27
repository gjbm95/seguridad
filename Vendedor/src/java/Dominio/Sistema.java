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
     public static String llaveServerVendedor = "";
     public static String llaveClientVendedor = "";
     public static boolean suiche = true; 
     
   //Almaceno los datos
     
     public static String rutaProductos="";
     public static String rutaUsuarios="";
     
}
