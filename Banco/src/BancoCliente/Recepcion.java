/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoCliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


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
                    case "0":

                    break;
                    case"1":
                     if(ControladorC.debitarTarjeta(mensaje.split(":")[1],mensaje.split(":")[2],
                     mensaje.split(":")[3],mensaje.split(":")[4],Float.parseFloat(mensaje.split(":")[5])
                             ,Integer.parseInt(mensaje.split(":")[6]))){
                      if((boolean)(Envio.enviodato("3:"+mensaje.split(":")[7]+":"+mensaje.split(":")[5],"bancovendedor"))){
                      System.out.println("Se ha debitado la cantidad en la cuenta del vendedor exitosamente");
                      respuesta = true;
                      }else 
                      respuesta = false;
                     }
                     
                    break;
                    case"2":

                    break;
                    case"3":

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
