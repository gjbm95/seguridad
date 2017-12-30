/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;
import Dominio.Sistema;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

/**
 *
 * @author Junior
 */
public class ReciboArchivo {

    DataOutputStream output;
    BufferedInputStream bis;
    BufferedOutputStream bos;
    byte[] receivedData;
    int in;
    String file;
    private  String CLIENT_KEY_STORE = Sistema.llaveClientCliente;
    private  String CLIENT_KEY_STORE_PASSWORD = "456456";

    public void descargarArchivo(String ip, int puerto, int nombre) {
        String[] d = null;
        DataInputStream dis = null;
        try {
            System.out.println("Iniciando proceso de recepcion de factura");
            // Se abre una conexion con Servidor Socket
  
            Socket cliente = clientWithCert(Sistema.ipvendedor,Sistema.puertovendedor+1);
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            //Solicito el archivo:
            salidaObjeto.writeObject("1:" +Sistema.cedula+"_"+Sistema.idTransaccion+".txt");
            bis = new BufferedInputStream(cliente.getInputStream());
            dis = new DataInputStream(cliente.getInputStream());
            //Recibimos el nombre del fichero
            file = dis.readUTF();
            d = file.split(":");
            d[0] = d[0].substring(d[0].indexOf('\\') + 1, d[0].length());
            //La data recibida, vendran en paquetes de 1024 bytes. 
            receivedData = new byte[1024];
            //Para guardar fichero recibido
            bos = new BufferedOutputStream(new FileOutputStream("Descargas\\" + d[0]));
       
            int l = 0;
            //Se manejan los datos acerca del libro recibido
            while ((in = bis.read(receivedData)) != -1) {
                    bos.write(receivedData, 0, in);
                l += in;
            }
            //Se cierra la conexion con el servidor de descarga. 
            bos.close();
            dis.close();
            System.out.println("Finalizando proceso de recepcion de factura");
        } catch (Exception e) {
                Logger.getLogger(ReciboArchivo.class.getName()).log(Level.SEVERE, null, e);
       
        }

    }
    
        private Socket clientWithCert(String ip, int puerto) throws Exception {
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
