/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

/**
 *
 * @author Junior
 */
public class Facturacion extends Thread {
    int idfactura; 
    
    
    public Facturacion (int idfactura){
      this.idfactura = idfactura; 
    } 
    
    
    @Override
    public void run(){
      Usuario usu=new Usuario();
      usu.generarTxtFact(idfactura); 
    }
}
