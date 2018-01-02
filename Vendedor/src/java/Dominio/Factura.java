/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Junior
 */
public class Factura {
    
    private int id;
    private Producto producto;
    

    public Factura() {
    }

    public Factura(int id, Producto producto) {
        this.id = id;
        this.producto = producto;
    }
    
    
    
    public Factura(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}
