/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.*;
import DAO.*;
import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class Productos {
    
    public void AgregarProductos() {
      //Producto producto= new Producto(1,"audifonos","Con un audio magnifico",405,"recursos/audifonos");
      //Producto producto= new Producto(2,"camisa","De muy buena calidad, muy comoda",700,"recursos/camisa");
      //Producto producto= new Producto(3,"celula","Uno de los mas novedosos equipos del momento, deber tenerlo",405,"recursos/celular");
      //Producto producto= new Producto(4,"computadora","Potente maquina que cumple todas tus necesidades",300,"recursos/computadora");
      //Producto producto= new Producto(5,"pantalon","Muy comodos y se adaptan a tu figura",200,"recursos/pantalon");
      Producto producto= new Producto(6,"zapatos","De lo mas a la moda que puedes encontrar",900,"recursos/zapatos");
      Control dao= new Control();
      dao.agregarProducto(producto);
    
    }
    public void EliminarProductos(int id)
    {
      Control dao= new Control();
      dao.eliminarProducto(id);
    
    }
    
    public void ObtenerListaProductos(){
      ArrayList<Producto> producto = new ArrayList<Producto>();
      Control dao= new Control();
      producto=dao.obtenerListaProductos();
      
        for (Producto prod : producto) {
            System.out.println("esta es la id "+prod.getId());
            System.out.println("este es el nombre "+prod.getNombre());
            System.out.println("esta es la descripcion "+prod.getDescripcion());
            System.out.println("este es el precio "+prod.getPrecio());
            System.out.println("esta es la imagen "+prod.getImagen());
            
        }
    
    
    }
    
    
    
    
}
