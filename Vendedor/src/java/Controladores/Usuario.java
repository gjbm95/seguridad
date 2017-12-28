/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.Control;
import Dominio.Cliente;
import Dominio.Factura;
import Dominio.Producto;
import Dominio.Sistema;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Junior
 */
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class Usuario extends HttpServlet{
    
    public boolean IniciarSesion(String Cedula,String contra){
    
    boolean encontrado;
    Control dao= new Control();
    encontrado=dao.validarInicioSesion(Cedula, contra);
    
        return encontrado;
    }
    
    
    public void AgregarCliente(Cliente cli)
            
    {
        
       // Cliente cliente= new Cliente("Aquiles","Alberto","2","a@w","123");
        Control dao= new Control();
        dao.agregarCliente(cli);
        
    
    }
    
    public void EliminarCliente(String cedula){
        Control dao= new Control();
        dao.eliminarCliente(cedula);
    }
    
    public void GenerarFactura(String cedula){
        /*Producto producto= new Producto(55,"frutasaa","es malaa",123);
        Factura fact= new Factura(1,producto);
        Control dao= new Control();
        dao.generarFactura(cedula,fact);*/
    
    
    }
                  
     public void generarTxtFact(int id){
        String filelocation = Sistema.rutaFactura; 
        Cliente cliente=new Cliente(); 
        Control dao= new Control();
        cliente=dao.obtenerCliente(id);
        String nuevalinea = System.getProperty("line.separator");
    try
        {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo=new File(filelocation);

            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            FileWriter escribir=new FileWriter(archivo,true);

            //Escribimos en el archivo con el metodo write 
            escribir.write("EL NOMBRE DEL CLIENTE ES : "+cliente.getNombre()+nuevalinea);
            escribir.write("EL APELLIDO DEL CLIENTE ES : "+cliente.getApellido()+nuevalinea);
            escribir.write("LA CEDULA DEL CLIENTE ES : "+cliente.getCedula()+nuevalinea);
            escribir.write("EL CORREO DEL CLIENTE ES : "+cliente.getCorreo()+nuevalinea);
            
            for ( Factura factura : cliente.getFactura()) {
                escribir.write("LA ID DE LA FACTURA ES : "+factura.getId()+nuevalinea);
                escribir.write("EL PRODUCTO ES : "+factura.getProducto().getNombre()+nuevalinea);
                escribir.write("LA DESCRIPCION DEL PRODUCTOS ES : "+factura.getProducto().getDescripcion()+nuevalinea);
            }

            //Cerramos la conexion
            escribir.close();
        }

//Si existe un problema al escribir cae aqui
catch(Exception e)
{
System.out.println("Error al escribir");
}
        
        
    }
    
    
    
    
}
