package Red;

import Dominio.Sistema;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

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
public class RecepcionArchivo extends Thread {
    
    private static String SERVER_KEY_STORE = Sistema.llaveServerVendedor;
    private static String SERVER_KEY_STORE_PASSWORD = "123123";
    
    public RecepcionArchivo()
    {

    }
    
    /**
     * Inicializa el hilo encargado del envio de archivos 
     */
    public void run () 
    {
       //Solicitudes concurrentes:       
           try {
            System.setProperty("javax.net.ssl.trustStore", SERVER_KEY_STORE);
            SSLContext context = SSLContext.getInstance("TLS");

            KeyStore ks = KeyStore.getInstance("jceks");
            ks.load(new FileInputStream(SERVER_KEY_STORE), null);
            KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
            kf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());

            context.init(kf.getKeyManagers(), null, null);

            ServerSocketFactory factory = context.getServerSocketFactory();
            ServerSocket _socket = factory.createServerSocket(Sistema.puertovendedor+1);
            ((SSLServerSocket) _socket).setNeedClientAuth(false); 
               
                while (true)
                   {   
                      Socket recibo = _socket.accept();              
                      new EnvioArchivo(_socket,recibo).start();
                   }
        } catch (IOException ex) {
                   //Logger.getLogger(Recepcion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {  
            Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }  

        
             
    }
}
