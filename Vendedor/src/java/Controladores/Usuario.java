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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
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
    
    private List<byte[]> list;   
    
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


    }
                  
     public void generarTxtFact(int id){
        String filelocation = Sistema.rutaFactura; 
        Cliente cliente=new Cliente(); 
        while(Sistema.espera){
        
        }
        Sistema.espera = false;
        Control dao= new Control();
        cliente=dao.obtenerCliente(id);
        String nuevalinea = System.getProperty("line.separator");
            try
                {
                    //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
                    System.out.println(filelocation+cliente.getCedula()+"_"+id+".txt");
                    File archivo=new File(filelocation+cliente.getCedula()+"_"+id+".txt");
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
                    System.out.println("Se ha generado la factura con exito!");
                }catch(Exception e)
                    {
                      System.out.println(e.getMessage());
                    }
    }
     
     public void firmarArchivo(){
        try { 
            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
            
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     }
     
     
     	//Method to retrieve the Private Key from a file
	public PrivateKey getPrivate(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}
    
        //The method that signs the data using the private key that is stored in keyFile path
	public byte[] sign(String data, String keyFile) throws InvalidKeyException, Exception{
		Signature rsa = Signature.getInstance("SHA1withRSA");
		rsa.initSign(getPrivate(keyFile));
		rsa.update(data.getBytes());
		return rsa.sign();
	}
        
        //Method to write the List of byte[] to a file
	private void writeToFile(String filename) throws FileNotFoundException, IOException {
		File f = new File(filename);
		f.getParentFile().mkdirs();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
	        out.writeObject(list);
		out.close();
		System.out.println("Your file is ready.");
	}
        
        
        //The constructor of Message class builds the list that will be written to the file.
	//The list consists of the message and the signature.
	public void establecerParametros(String data, String keyFile) throws InvalidKeyException, Exception {
		list = new ArrayList<byte[]>();
		list.add(data.getBytes());
		list.add(sign(data, keyFile));
	}
    
        public void activarCuenta(String cedula){
        Control control=new Control();
        control.activarCuenta(cedula);
        }
        
        public void desactivarCuenta(String cedula){
        Control control=new Control();
        control.desactivarCuenta(cedula);
        }
    
        public int obtenerStatus(String Cedula){
        Control control=new Control();
        int resultado;
        resultado=control.obtenerStatus(Cedula);
        return resultado;
        
        }
}
