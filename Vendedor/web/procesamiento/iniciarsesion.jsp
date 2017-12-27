<%-- 
    Document   : iniciarsesion
    Created on : 26/12/2017, 02:07:00 PM
    Author     : Junior
--%>


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
            
            Usuario usu=new Usuario();
            //usu.GenerarFactura("2");
            //usu.AgregarCliente();
            if(usu.IniciarSesion(usuario, pass)==true){
            session.setAttribute("usuario",usuario);
            response.sendRedirect("http://localhost:8080/Vendedor/");
            }else{
            //response.sendRedirect("http://localhost:8080/Vendedor/");
            //Thread.sleep(3000);
            out.print("<script> alert('Usuario o Contrasena Incorrecto');</script>");
            out.print("<div>mensaje</div> <a href='http://localhost:8080/Vendedor/'> volver </a>");
           
            
            }

%>
