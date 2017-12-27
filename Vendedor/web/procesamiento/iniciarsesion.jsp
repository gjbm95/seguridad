<%-- 
    Document   : iniciarsesion
    Created on : 26/12/2017, 02:07:00 PM
    Author     : Junior
--%>


<%@page import="Controladores.Productos"%>
<%@page import="Controladores.Usuario"%>
<%@page import="Dominio.Sistema"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Red.*" %>
<%
            if (Sistema.suiche){ 
            new Control().start();
            Sistema.suiche = false;
            }
            String usuario = (String) request.getParameter("usuario");
            String pass = (String) request.getParameter("pwd");
            
            //Inicio de Sesion  
            Productos pro=new Productos();
            Usuario usu=new Usuario();
            pro.ObtenerListaProductos();
            //pro.AgregarProductos();
            //usu.GenerarFactura("2");
            //usu.AgregarCliente();
            if(usu.IniciarSesion(usuario, Integer.toString(pass.hashCode()))==true){
            session.setAttribute("usuario",usuario);
            response.sendRedirect("https://garryjunior.com.ve:8443/Vendedor/");
            }else{
            //response.sendRedirect("http://localhost:8080/Vendedor/");
            //Thread.sleep(3000);
            out.print("<script> alert('Usuario o Contrasena Incorrecto');</script>");
            out.print("<div>mensaje</div> <a href='https://garryjunior.com.ve:8443/Vendedor/'> volver </a>");
           
            
            }

%>
