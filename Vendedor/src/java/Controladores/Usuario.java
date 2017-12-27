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
                                       
    
    
    
}
