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
     public static String ipbancov = "192.168.1.100"; 
     public static String ipbancoc = "192.168.1.100";
     public static String ipvendedor = "192.168.1.105";
     public static String ipcliente = "192.168.1.105";
     public static int    puertov = 8888; 
     public static int    puertoc = 1111;
     public static int    puertovendedor = 7777;
     public static int    puertocliente = 5555;
     public static String recepcion=""; 
   //Datos de Seguridad
     public static String llaveServerBancoVendedor = "seguridad/BancoVendedor/serverBancoVendedor_ks"; 
     public static String llaveServerBancoCliente = "seguridad/BancoCliente/serverBancoCliente_ks"; 
     public static String llaveClientBancoVendedor = "seguridad/BancoVendedor/clientBancoVendedor_ks"; 
     public static String llaveClientBancoCliente = "seguridad/BancoCliente/clientBancoCliente_ks";
     public static String llaveServerVendedor = "";
     public static String llaveClientVendedor = "";
     public static boolean suiche = true; 
     public static boolean espera = true; 
     
   //Almaceno los datos
     
     public static String rutaProductos="";
     public static String rutaUsuarios="";
     public static String rutaFactura="";
     public static String rutaSeguridad="";
     public static int intentos=1;

     
   //Datos Bancarios del Vendedor
     public static String numerocuenta="463234823423323423333"; 
   
     
     
}
