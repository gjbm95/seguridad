/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import BancoVendedor.ControladorV;
import BancoVendedor.*;
import Dominio.Sistema;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Junior
 */
public class Recepcion extends Thread {
    
    private ServerSocket reves;
    private Socket recibo;
    private int proceso =0; 
    private String mensaje; 
    private int conexiones=0; 
    
    public Recepcion(ServerSocket reves,Socket recibo,int proceso){
        this.reves = reves; 
        this.recibo = recibo;
        this.proceso = proceso;
    }
    
    @Override
    public void run(){
  
        try {
               //El servidor recibe:
               ObjectInputStream ois = new ObjectInputStream(recibo.getInputStream());
               ObjectOutputStream salidaObjeto = new ObjectOutputStream(recibo.getOutputStream()); 
               //Mensaje que llega:
                mensaje = (String)ois.readObject();
               //Preparo respuesta:
                Object respuesta = null;
               //RESPUESTAS DEL SERVIDOR:
               //----------------------------------------------------------------------
               switch(mensaje.split(":")[0]){
                    case"0":
                        Sistema.idTransaccion = mensaje.split(":")[1];
                        Sistema.cedula = mensaje.split(":")[2];
                    break;
                    case"1":
                        System.out.println("La compra ha sido exitosa, en breve se emitira su factura!");
                    break;
                    case"2":
                        System.out.println("Una transaccion no se ha efectuado por saldo insuficiente");
                    break;
                    case"3":
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Se ha detectado una transaccion en su banco!");
                        System.out.println("Confirme usando el siguiente codigo: " +mensaje.split(":")[1]);
                        System.out.println("-----------------------------------------------------------------");
                    break;
                    case"4":
                        new ReciboArchivo().descargarArchivo(Sistema.ipvendedor,Sistema.puertovendedor+1,1);
                        System.out.println("Se ha recibido la factura con exito!");
                    break;
               }
               //Con este codigo es que responde el servidor:
               salidaObjeto.writeObject(respuesta);
               
               //----------------------------------------------------------------------
               
               recibo.close();//Se finaliza la conexión con el cliente
           } catch (IOException | ClassNotFoundException ex) {
               System.out.println("Hay un error de Conexion! o Ocurrio un error en la solicitud recibida: " + mensaje);
               //Logger.getLogger(GestionSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
}
