package Red;
import Dominio.Sistema;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Universidad Catolica Andres Bello
 * Facultad de Ingenieria
 * Escuela de Ingenieria Informatica 
 * Seguridad Computacional
 * ----------------------------------
 * Integrantes: 
 * --------------
 * Garry Bruno 
 * Aquiles Pulido
 */
public class EnvioArchivo extends Thread {
    
    DataInputStream input;
    BufferedInputStream bis;
    BufferedOutputStream bos;
    ServerSocket server; 
    Socket connection;
    int in;
    byte[] byteArray;
     byte[] mitad;
    //Fichero a transferir
    String solicitud= null;
    String ip; 
    String libro;
    
    public EnvioArchivo() 
    {
    
    
    }
    public EnvioArchivo(ServerSocket server, Socket connection)
    {
        this.connection = connection;
        this.server = server;
    }
    
    /**
     * Este hilo se encarga del envio de archivos al nodo que lo solicite
     */
    public void run (){
                String [] dt=null;
           
               try{
                     int id=0;
                     ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
                     solicitud = (String)ois.readObject();
                     dt = solicitud.split(":");
                     System.out.println("Iniciando proceso de envio de factura... ");
                     File localFile = new File(Sistema.rutaFactura+dt[1]);
                     bis = new BufferedInputStream(new FileInputStream(localFile));
                     bos = new BufferedOutputStream(connection.getOutputStream());       
                     //Enviamos el nombre del fichero
                     DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
                     dos.writeUTF(localFile.getName()+":"+Integer.toString((int)localFile.length()));
                     int tamano = (int)localFile.length();
                     //Enviamos el fichero
                     byteArray = new byte[(int)localFile.length()];
                     //Mando:
                     int k=0;
                      while ((in = bis.read(byteArray)) != -1){        
                      bos.write(byteArray,0,in); 
                      k+=in; 
                     }
                      // Se cierra la conexion
                      bis.close();
                      bos.close();
                       System.out.println("Envio de Archivo finalizado!");
                    }catch ( Exception e ) {
                          System.out.println("Error de Envio del archivo "+dt[1]+ ". Usted o el cliente ha perdido la conexion");           
                          Logger.getLogger(EnvioArchivo.class.getName()).log(Level.SEVERE, null, e);
                          
                    }
    }
        

    
}
