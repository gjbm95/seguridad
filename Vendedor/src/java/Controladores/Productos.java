/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.*;
import DAO.*;

/**
 *
 * @author Junior
 */
public class Productos {
    
    public void AgregarProductos() {
      Producto producto= new Producto(1,"fruta","es buena",405);
      Control dao= new Control();
      dao.crearXMLProducto();
      dao.agregarProducto(producto);
    
    }
    public void EliminarProductos(int id)
    {
      Control dao= new Control();
      dao.eliminarProducto(id);
    
    }
    
    
    
    
}
