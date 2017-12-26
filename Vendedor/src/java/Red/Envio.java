/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Red;

import Dominio.Sistema;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.KeyStore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Junior
 */
public class Envio {
    private static String CLIENT_KEY_STORE = Sistema.llaveClientVendedor;
    private static String CLIENT_KEY_STORE_PASSWORD = "456456";
    
    public static Object enviodato(Object dato, String tipo){
        try {
            Object respuesta =null;
            
              // Set the key store to use for validating the server cert.
            System.setProperty("javax.net.ssl.trustStore", CLIENT_KEY_STORE);
            //System.setProperty("javax.net.debug", "ssl,handshake");
            
            Socket reves = null;
            if(tipo.equals("bancocliente")){
            CLIENT_KEY_STORE = Sistema.llaveClientBancoCliente;
            reves = clientWithCert(Sistema.ipbancoc,Sistema.puertoc);
            }
            if(tipo.equals("bancovendedor")){ 
            CLIENT_KEY_STORE = Sistema.llaveClientBancoVendedor;
            reves = clientWithCert(Sistema.ipbancov,Sistema.puertov);
            }
            if(tipo.equals("vendedor")){
            CLIENT_KEY_STORE = Sistema.llaveClientVendedor;
            reves = clientWithCert(Sistema.ipvendedor,Sistema.puertovendedor);
            }
            ObjectOutputStream salidaObjeto;      
            //Se colocan los datos del nodo (Direccion IP y Puerto).
            salidaObjeto = new ObjectOutputStream(reves.getOutputStream());
            //El cliente manda: 
            salidaObjeto.writeObject(dato);
            //El cliente recibe: 
            ObjectInputStream ois = new ObjectInputStream(reves.getInputStream());
            respuesta = ois.readObject();
            //Se cierra la conexion. 
            reves.close();
            
            return respuesta;
        } catch (IOException ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
       private static Socket clientWithoutCert() throws Exception {
        SocketFactory sf = SSLSocketFactory.getDefault();
        Socket s = sf.createSocket("localhost", 8443);
        return s;
       }

       private static Socket clientWithCert(String ip, int puerto) throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        KeyStore ks = KeyStore.getInstance("jceks");

        ks.load(new FileInputStream(CLIENT_KEY_STORE), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());
        context.init(kf.getKeyManagers(), null, null);

        SocketFactory factory = context.getSocketFactory();
        Socket s = factory.createSocket(ip,puerto);
        return s;
       }
}
