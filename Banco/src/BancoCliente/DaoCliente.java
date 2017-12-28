/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoCliente;

import BancoVendedor.DaoVendedor;
import Dominio.Cuenta;
import Dominio.Tarjeta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Junior
 */
public class DaoCliente {
    String filelocation = "bancocliente.xml";
    Element root;
    
    /**
     * Agregando elemento a archivo XML
    **/
    public void agregarCuenta(Cuenta cuenta){
        File xmlFile = new File(filelocation);
        Document document = null;
        
        if(xmlFile.exists()) {
            try {
                // try to load document from xml file if it exist
                // create a file input stream
                FileInputStream fis = new FileInputStream(xmlFile);
                // create a sax builder to parse the document
                SAXBuilder sb = new SAXBuilder();
                // parse the xml content provided by the file input stream and create a Document object
                document = sb.build(fis);
                // get the root element of the document
                root = document.getRootElement();
                fis.close();
            } catch (JDOMException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
            Element elemento = new Element("cuenta");
            elemento.setAttribute("nombre",cuenta.getNombre());
            elemento.setAttribute("apellido",cuenta.getApellido());
            elemento.setAttribute("cedula", cuenta.getCedula());
            elemento.setAttribute("numero",cuenta.getNumerocuenta());
            elemento.setAttribute("tipo",cuenta.getTipocuenta());
            elemento.setAttribute("saldo",Float.toString(cuenta.getSaldo()));
            Element tarjeta = new Element("tarjeta"); 
            tarjeta.setAttribute("numero",cuenta.getTarjeta().getNumero());
            tarjeta.setAttribute("marca",cuenta.getTarjeta().getMarca());
            tarjeta.setAttribute("codigo",cuenta.getTarjeta().getCodseguridad());
            tarjeta.setAttribute("clave",Integer.toString(cuenta.getTarjeta().getClave()));
            tarjeta.setAttribute("saldo",Float.toString(cuenta.getTarjeta().getSaldo()));
            tarjeta.setAttribute("fecha_vencimiento",cuenta.getTarjeta().getFechavenc());
            elemento.addContent(tarjeta);
            root.addContent(elemento);
            document.removeContent();
            document.addContent(root);
                try {
                    FileWriter writer = new FileWriter(xmlFile);
                    XMLOutputter outputter = new XMLOutputter();
                    outputter.setFormat(Format.getPrettyFormat());
                    outputter.output(document, writer);
                    //outputter.output(document, System.out);
                    writer.close(); // close writer
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    /*
      Elimina una cuenta de un cliente 
    */
    public void eliminarCuenta(String numero){
       
        File xmlFile = new File(filelocation);
        Document document = null;
        
        if(xmlFile.exists()) {
            try {
                // try to load document from xml file if it exist
                // create a file input stream
                FileInputStream fis = new FileInputStream(xmlFile);
                // create a sax builder to parse the document
                SAXBuilder sb = new SAXBuilder();
                // parse the xml content provided by the file input stream and create a Document object
                document = sb.build(fis);
                // get the root element of the document
                root = document.getRootElement();
                fis.close();
            } catch (JDOMException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
        
            Element aux = new Element("cuenta");
            List recursos = root.getChildren("cuenta");
            while (aux != null) {
                aux = obtenerCuenta(recursos,numero);
                if (aux != null) {
                    recursos.remove(aux);
                    updateDocument();   
                }
            }
            document.removeContent();
            document.addContent(root);
            
                try {
                    FileWriter writer = new FileWriter(xmlFile);
                    XMLOutputter outputter = new XMLOutputter();
                    outputter.setFormat(Format.getPrettyFormat());
                    outputter.output(document, writer);
                    //outputter.output(document, System.out);
                    writer.close(); // close writer
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    
    /*
     Devuelve una cuenta en base a la Tarjeta de un cliente
    */
    public Cuenta obtenerCuenta(String id,String codigo,String vence){
       File xmlFile = new File(filelocation);
        Document document = null;
        if(xmlFile.exists()) {
            try {
                // try to load document from xml file if it exist
                // create a file input stream
                FileInputStream fis = new FileInputStream(xmlFile);
                // create a sax builder to parse the document
                SAXBuilder sb = new SAXBuilder();
                // parse the xml content provided by the file input stream and create a Document object
                document = sb.build(fis);
                // get the root element of the document
                root = document.getRootElement();
                Cuenta resultado = null;
                    Element aux = new Element("cuenta");
                    List nodos = root.getChildren("cuenta");
                    aux = obtenerTarjeta(nodos,id,codigo);
                    if(aux != null) {
                        resultado =  new Cuenta(aux.getAttributeValue("nombre")
                                 ,aux.getAttributeValue("apellido")
                                 ,aux.getAttributeValue("cedula")
                                 ,aux.getAttributeValue("numero")
                                 ,aux.getAttributeValue("tipo"),
                                 Float.parseFloat(aux.getAttributeValue("saldo")));
                        Element tar = aux.getChild("tarjeta");
                        resultado.setTarjeta(new Tarjeta(tar.getAttributeValue("numero"),tar.getAttributeValue("marca"),tar.getAttributeValue("codigo"),Integer.parseInt(tar.getAttributeValue("clave")),Float.parseFloat(tar.getAttributeValue("saldo")),tar.getAttributeValue("fecha_vencimiento")));
                    }
                fis.close();
                return resultado; 
            } catch (JDOMException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 

        return null; 
    }
    
    /*
     Devuelve una cuenta en base a la Tarjeta de un cliente
    */
    public Cuenta obtenerCuenta(String id){
       File xmlFile = new File(filelocation);
        Document document = null;
        if(xmlFile.exists()) {
            try {
                // try to load document from xml file if it exist
                // create a file input stream
                FileInputStream fis = new FileInputStream(xmlFile);
                // create a sax builder to parse the document
                SAXBuilder sb = new SAXBuilder();
                // parse the xml content provided by the file input stream and create a Document object
                document = sb.build(fis);
                // get the root element of the document
                root = document.getRootElement();
                Cuenta resultado = null;
                    Element aux = new Element("cuenta");
                    List nodos = root.getChildren("cuenta");
                    aux = obtenerCuenta(nodos,id);
                    if(aux != null) {
                        resultado =  new Cuenta(aux.getAttributeValue("nombre")
                                 ,aux.getAttributeValue("apellido")
                                 ,aux.getAttributeValue("cedula")
                                 ,aux.getAttributeValue("numero")
                                 ,aux.getAttributeValue("tipo"),
                                 Float.parseFloat(aux.getAttributeValue("saldo")));
                        Element tar = aux.getChild("tarjeta");
                        resultado.setTarjeta(new Tarjeta(tar.getAttributeValue("numero"),tar.getAttributeValue("marca"),tar.getAttributeValue("codigo"),Integer.parseInt(tar.getAttributeValue("clave")),Float.parseFloat(tar.getAttributeValue("saldo")),tar.getAttributeValue("fecha_vencimiento")));
                    }
                fis.close();
                return resultado; 
            } catch (JDOMException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 

        return null; 
    }
    
    
    /*
     Devuelve una cuenta en base a la Tarjeta de un cliente
    */
    public Cuenta obtenerCuentaCedula(String cedula){
       File xmlFile = new File(filelocation);
        Document document = null;
        if(xmlFile.exists()) {
            try {
                // try to load document from xml file if it exist
                // create a file input stream
                FileInputStream fis = new FileInputStream(xmlFile);
                // create a sax builder to parse the document
                SAXBuilder sb = new SAXBuilder();
                // parse the xml content provided by the file input stream and create a Document object
                document = sb.build(fis);
                // get the root element of the document
                root = document.getRootElement();
                Cuenta resultado = null;
                    Element aux = new Element("cuenta");
                    List nodos = root.getChildren("cuenta");
                    aux = obtenerCuentacedula(nodos,cedula);
                    if(aux != null) {
                        resultado =  new Cuenta(aux.getAttributeValue("nombre")
                                 ,aux.getAttributeValue("apellido")
                                 ,aux.getAttributeValue("cedula")
                                 ,aux.getAttributeValue("numero")
                                 ,aux.getAttributeValue("tipo"),
                                 Float.parseFloat(aux.getAttributeValue("saldo")));
                        Element tar = aux.getChild("tarjeta");
                        resultado.setTarjeta(new Tarjeta(tar.getAttributeValue("numero"),tar.getAttributeValue("marca"),tar.getAttributeValue("codigo"),Integer.parseInt(tar.getAttributeValue("clave")),Float.parseFloat(tar.getAttributeValue("saldo")),tar.getAttributeValue("fecha_vencimiento")));
                    }
                fis.close();
                return resultado; 
            } catch (JDOMException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DaoVendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 

        return null; 
    }
   /**
    * Permite localizar un archivo por su nombre en hash
    * @param nombre
    * @return 
    */ 
   public String buscarArchivo(int nombre){
      File f = new File(filelocation);
      File[] ficheros = f.listFiles();
      for (int x=0;x<ficheros.length;x++){
          if(Math.abs(ficheros[x].getName().substring(0, ficheros[x].getName().lastIndexOf(".")).hashCode())==nombre){
           return ficheros[x].getName();
        }
      }
       return null; 
    } 
    
      
    /*
     Retorna la cuenta de un usuario 
    */
    public Element obtenerTarjeta(List raiz,String id,String codigo){
         Iterator i = raiz.iterator();
          while (i.hasNext()) {
            Element e = (Element) i.next();
            Element tarjeta = e.getChild("tarjeta"); 
            if ((id.equals(tarjeta.getAttributeValue("numero")))&&(codigo.equals(tarjeta.getAttributeValue("codigo")))) {
                return e;
            }
        }
      return null; 
    }
    
    /*
     Retorna la cuenta de un usuario 
    */
    public Element obtenerCuenta(List raiz,String id){
        
         Iterator i = raiz.iterator();
          while (i.hasNext()) {
            //System.out.println("i tiene algo");
            Element e = (Element) i.next();
            if (id.equals(e.getAttributeValue("numero"))) {
                return e;
            }
        }
      return null; 
    }
    
    public Element obtenerCuentacedula(List raiz,String cedula){
        
         Iterator i = raiz.iterator();
          while (i.hasNext()) {
            //System.out.println("i tiene algo");
            Element e = (Element) i.next();
            if (cedula.equals(e.getAttributeValue("cedula"))) {
                return e;
            }
        }
      return null; 
    }
    
    /*
     Creando archivo XML 
    */
    public void crearXML(){
         try {
		Element banco = new Element("banco");
		Document doc = new Document(banco);
		doc.setRootElement(banco);
		// new XMLOutputter().output(doc, System.out);
		XMLOutputter xmlOutput = new XMLOutputter();
		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(doc, new FileWriter(filelocation));
	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  }
    
    }
    
    /*
      Funcion que actualiza el documento XML 
    */
    private boolean updateDocument() {
        try {
            XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(filelocation);
            out.output(root, file);
            file.flush();
            file.close();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }
    /**
     * Elimina el archivo XML del disco
     */
    public void eliminarArchivo(){
      File fichero = new File(filelocation);
      fichero.delete();
    }
    
}
