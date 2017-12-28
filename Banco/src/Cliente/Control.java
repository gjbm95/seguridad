package Cliente;

import BancoVendedor.*;
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
import javax.net.ssl.SSLServerSocketFactory;

/**
 *
 * @author Junior
 */
public class Control extends Thread{
    
    private static String SERVER_KEY_STORE = Sistema.llaveServerCliente;
    private static String SERVER_KEY_STORE_PASSWORD = "123123";
    
    public Control(){
    
    
    }
    
    @Override
    public void run(){
    try { 
            System.setProperty("javax.net.ssl.trustStore", SERVER_KEY_STORE);
            SSLContext context = SSLContext.getInstance("TLS");

            KeyStore ks = KeyStore.getInstance("jceks");
            ks.load(new FileInputStream(SERVER_KEY_STORE), null);
            KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
            kf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());

            context.init(kf.getKeyManagers(), null, null);

            ServerSocketFactory factory = context.getServerSocketFactory();
            ServerSocket _socket = factory.createServerSocket(Sistema.puertocliente);
            ((SSLServerSocket) _socket).setNeedClientAuth(false);
            //Contador de procesos; 
             int i=1;
            //Solicitudes concurrentes:
            while (true)
            {        
                       System.out.println("En espera...");
                       Socket recibo = _socket.accept();
                       new Recepcion(_socket,recibo,i).start();
                       i++;
            } 
        
        } catch (IOException ex) {
            ex.printStackTrace();   
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
}
