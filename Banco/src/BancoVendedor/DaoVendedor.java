/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoVendedor;

import Dominio.Cuenta;
import Dominio.Sistema;
import Dominio.Tarjeta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Junior
 */
public class DaoVendedor {
        String filelocation = "bancovendedor.xml";
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
            elemento.setAttribute("numero",cuenta.getNumerocuenta());
            elemento.setAttribute("tipo",cuenta.getTipocuenta());
            elemento.setAttribute("saldo",Float.toString(cuenta.getSaldo()));
            Element tarjeta = new Element("tarjeta"); 
            tarjeta.setAttribute("numero",cuenta.getTarjeta().getNumero());
            tarjeta.setAttribute("marca",cuenta.getTarjeta().getMarca());
            tarjeta.setAttribute("codigo",cuenta.getTarjeta().getCodseguridad());
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
      Elimina un recurso del cliente 
    */
    public void eliminarRecurso(int numero){
       
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
                aux = obtenerIdRecurso(recursos,numero);
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
     Devuelve un recurso del cliente
    */
    public Cuenta obtenerRecurso(int id){
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
                    aux = obtenerIdRecurso(nodos,id);
                    if(aux != null) {
                        resultado =  new Cuenta(aux.getAttributeValue("nombre")
                                 ,aux.getAttributeValue("apellido"),aux.getAttributeValue("numero")
                                 ,aux.getAttributeValue("tipo"),
                                 Float.parseFloat(aux.getAttributeValue("saldo")));
                        Element tar = aux.getChild("tarjeta");
                        resultado.setTarjeta(new Tarjeta(tar.getAttributeValue("numero"),tar.getAttributeValue("marca"),tar.getAttributeValue("codigo")));
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
     Retorna la ip del nodo en HASH almacenada
    */
    public Element obtenerIdRecurso(List raiz,int id){
        
         Iterator i = raiz.iterator();
          while (i.hasNext()) {
            //System.out.println("i tiene algo");
            Element e = (Element) i.next();
            if (id==Integer.parseInt(e.getAttributeValue("numero"))) {
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
