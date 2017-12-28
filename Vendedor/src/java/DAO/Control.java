/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Dominio.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Control {
    
     String filelocation = Sistema.rutaProductos;
     String filelocation_cliente = Sistema.rutaUsuarios;
     Element root;
    
    /**
     * Agregando elemento a archivo XML
    **/
    public void agregarProducto(Producto producto){
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
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
         }  
            Element elemento = new Element("producto");
            elemento.setAttribute("id",Integer.toString(producto.getId()));
            elemento.setAttribute("nombre",producto.getNombre());
            elemento.setAttribute("descripcion", producto.getDescripcion());
            elemento.setAttribute("precio",Float.toString(producto.getPrecio()));
            elemento.setAttribute("imagen", producto.getImagen());
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
    
    public void agregarCliente(Cliente cliente){
        File xmlFile = new File(filelocation_cliente);
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
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
            Element elemento = new Element("cliente");
            elemento.setAttribute("nombre",cliente.getNombre());
            elemento.setAttribute("apellido",cliente.getApellido());
            elemento.setAttribute("cedula", cliente.getCedula());
            elemento.setAttribute("correo", cliente.getCorreo());
            elemento.setAttribute("contrasena", cliente.getContrasena());
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
      Elimina el producto
    */
    public void eliminarProducto(int id){
       
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
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
        
            Element aux = new Element("producto");
            List recursos = root.getChildren("producto");
            while (aux != null) {
                aux = obtenerProducto(recursos,id);
                if (aux != null) {
                    recursos.remove(aux);
                    updateDocumentProducto();   
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
      Elimina el producto
    */
    public void eliminarCliente(String cedula){
       
        File xmlFile = new File(filelocation_cliente);
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
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
        
            Element aux = new Element("cliente");
            List recursos = root.getChildren("cliente");
            while (aux != null) {
                aux = obtenerCliente(recursos,cedula);
                if (aux != null) {
                    recursos.remove(aux);
                    updateDocumentCliente();   
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
    
    
    
    
    
   /**
    * Permite localizar un archivo por su nombre en hash
    * @param nombre
    * @return 
    */ 
   public String buscarArchivoProducto(int nombre){
      File f = new File(filelocation);
      File[] ficheros = f.listFiles();
      for (int x=0;x<ficheros.length;x++){
          if(Math.abs(ficheros[x].getName().substring(0, ficheros[x].getName().lastIndexOf(".")).hashCode())==nombre){
           return ficheros[x].getName();
        }
      }
       return null; 
    } 
   
    
   /**
    * Permite localizar un archivo por su nombre en hash
    * @param nombre
    * @return 
    */ 
   
   public String buscarArchivoCliente(int nombre){
      File f = new File(filelocation_cliente);
      File[] ficheros = f.listFiles();
      for (int x=0;x<ficheros.length;x++){
          if(Math.abs(ficheros[x].getName().substring(0, ficheros[x].getName().lastIndexOf(".")).hashCode())==nombre){
           return ficheros[x].getName();
        }
      }
       return null; 
    } 
    
    
    /*
     Retorna el producto con dicha id
    */
    public Element obtenerProducto(List raiz,int id){
        
         Iterator i = raiz.iterator();
          while (i.hasNext()) {
            //System.out.println("i tiene algo");
            Element e = (Element) i.next();
            if ((id==Integer.parseInt(e.getAttributeValue("id")))) {
                return e;
            }
        }
      return null; 
    }
    
        /*
     Retorna el producto con dicha id
    */
    public Producto obtenerObjetoProducto(int id){
                for (Producto producto : this.obtenerListaProductos()){
                   if (producto.getId() == id)
                       return producto;
                } 
      return null; 
    }
    
    public Cliente obtenerObjetoCliente(String id){
                for (Cliente cliente : this.obtenerListaClientes()){
                   if (cliente.getCedula().equals(id))
                       return cliente;
                } 
      return null; 
    }
   
    /*
     Retorna el cliente por su cedula
    */
    
        public Element obtenerCliente(List raiz,String cedula){
        
         Iterator i = raiz.iterator();
          while (i.hasNext()) {
            Element e = (Element) i.next();
            if (cedula.equals(e.getAttributeValue("cedula"))) {
                return e;
            }
        }
      return null; 
    }
        
        
        public ArrayList<Producto> obtenerListaProductos(){
        
        ArrayList<Producto> producto = new ArrayList<Producto>();
            
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
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
        
        List recursos = root.getChildren("producto");
         Iterator i = recursos.iterator();
          while (i.hasNext()) {
            Element e = (Element) i.next();
            Producto pro = new Producto();
            pro.setId(Integer.parseInt(e.getAttributeValue("id")));
            pro.setNombre(e.getAttributeValue("nombre"));
            pro.setDescripcion(e.getAttributeValue("descripcion"));
            pro.setPrecio(Float.parseFloat(e.getAttributeValue("precio")));
            pro.setImagen(e.getAttributeValue("imagen"));
            producto.add( pro );
        }
            return producto;
        }
        
        
     public ArrayList<Cliente> obtenerListaClientes(){
        
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
            
        File xmlFile = new File(filelocation_cliente);
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
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
        
        List recursos = root.getChildren("cliente");
         Iterator i = recursos.iterator();
          while (i.hasNext()) {
            Element e = (Element) i.next();
            Cliente pro = new Cliente();
            pro.setCedula(e.getAttributeValue("cedula"));
            pro.setNombre(e.getAttributeValue("nombre"));
            pro.setApellido(e.getAttributeValue("apellido"));
            pro.setCorreo(e.getAttributeValue("correo"));
            clientes.add( pro );
        }
            return clientes;
        }
        
        
        public boolean validarInicioSesion(String cedula,String contrasena){
        File xmlFile = new File(filelocation_cliente);
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
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
        
            List recursos = root.getChildren("cliente");
           
            /*document.removeContent();
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
                }*/
        
         Iterator i = recursos.iterator();
          while (i.hasNext()) {
            Element e = (Element) i.next();
            if (cedula.equals(e.getAttributeValue("cedula"))  
                    &&(contrasena.equals(e.getAttributeValue("contrasena")))  ) {
                return true;
            }
        }
      return false; 
    }
    
    /*
     Retorna el cliente por su cedula
    */
    
        public Element obtenerClienteProducto(List raiz,int id){
        
         Iterator i = raiz.iterator();
          while (i.hasNext()) {
            //System.out.println("i tiene algo");
            Element e = (Element) i.next();
            Element cliente = e.getChild("cliente");
            Element producto=cliente.getChild("producto");
            if (id==Integer.parseInt(producto.getAttributeValue("id"))) {
                return producto;
            }
        }
      return null; 
    }
    /*
     Creando archivo XML 
    */
    public void crearXMLProducto(){
         try {
		Element vendedor = new Element("vendedor");
		Document doc = new Document(vendedor);
		doc.setRootElement(vendedor);
		// new XMLOutputter().output(doc, System.out);
		XMLOutputter xmlOutput = new XMLOutputter();
		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(doc, new FileWriter(filelocation));
	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  }
    
    }
    
    public void crearXMLCliente(){
         try {
		Element vendedor = new Element("vendedor");
		Document doc = new Document(vendedor);
		doc.setRootElement(vendedor);
		// new XMLOutputter().output(doc, System.out);
		XMLOutputter xmlOutput = new XMLOutputter();
		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(doc, new FileWriter(filelocation_cliente));
	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  }
    
    }
    
    
    
    /*
      Funcion que actualiza el documento XML 
    */
    private boolean updateDocumentProducto() {
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
    
    private boolean updateDocumentCliente() {
        try {
            XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(filelocation_cliente);
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
    public void eliminarArchivoProducto(){
      File fichero = new File(filelocation);
      fichero.delete();
    }
    
    public void eliminarArchivoCliente(){
      File fichero = new File(filelocation_cliente);
      fichero.delete();
    }
    
    public void generarFactura(String cedula,Factura fac){
        File xmlFile = new File(filelocation_cliente);
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
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
         } 
            Element aux = new Element("cliente");
            List recursos = root.getChildren("cliente");
            
                aux = obtenerCliente(recursos,cedula);
                if (aux != null) {
                    Element factura= new Element("factura");
                    factura.setAttribute("id",Integer.toString(fac.getId()));
                    Element producto = new Element("producto"); 
                    producto.setAttribute("id",Integer.toString(fac.getProducto().getId()));
                    producto.setAttribute("nombre",fac.getProducto().getNombre());
                    producto.setAttribute("descripcion",fac.getProducto().getDescripcion());
                    producto.setAttribute("precio",Float.toString(fac.getProducto().getPrecio()));
                    Date fecha=new Date();
                    producto.setAttribute("fecha",fecha.toString());
                    producto.setAttribute("imagen",fac.getProducto().getImagen());
                    aux.addContent(factura);
                    factura.addContent(producto);
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
                    updateDocumentCliente();
                }
            
        
      
    }
    
    
}
