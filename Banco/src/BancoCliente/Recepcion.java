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
import java.util.logging.Level;
import java.util.logging.Logger;


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
                     String resultado = ControladorC.debitarTarjeta(mensaje.split(":")[1],mensaje.split(":")[2],
                     mensaje.split(":")[3],mensaje.split(":")[4],Float.parseFloat(mensaje.split(":")[5])
                             ,mensaje.split(":")[6]);
                     if(resultado.equals("Exitoso")){
                          if((boolean)(Envio.enviodato("3:"+mensaje.split(":")[7]+":"+mensaje.split(":")[5]+":"+mensaje.split(":")[8],"bancovendedor"))){
                          System.out.println("Se ha debitado la cantidad en la cuenta del vendedor exitosamente");
                          respuesta = true;
                          Envio.enviodato("1:","cliente");
                          Envio.enviodato("0:"+mensaje.split(":")[8]+":"+mensaje.split(":")[6],"cliente");
                          }else 
                          respuesta = false;
                     }else if (resultado.equals("Fallido")){
                      respuesta = false; 
                     }else if (resultado.equals("Saldo insuficiente"))
                         {
                          respuesta = "Saldo insuficiente";
                          Envio.enviodato("2:","bancovendedor");
                         }
                     
                    break;
                    case"2":
                        if (ControladorC.existeCuenta(mensaje.split(":")[2],mensaje.split(":")[3],mensaje.split(":")[4])){
                         System.out.println("Se ha detectado una solicitud de transaccion!");
                         System.out.println("Enviando Codigo de Confirmacion...");
                         Envio.enviodato("3:"+mensaje.split(":")[1],"cliente");
                         respuesta = true;
                        }else 
                          respuesta = false;
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
               Logger.getLogger(Recepcion.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
}
