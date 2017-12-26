<%-- 
    Document   : registro
    Created on : 26/12/2017, 10:05:34 AM
    Author     : Junior
--%>

<%@page import="Dominio.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controladores.*" %>
<!DOCTYPE html>
<% 
 
    String nombre= (String) request.getParameter("name");
    String apellido= (String) request.getParameter("lastname");
    String cedula = (String) request.getParameter("cedula");
    String contrasena = (String) request.getParameter("pwd"); 
    String email = (String) request.getParameter("email"); 
    
    new Cliente(nombre,apellido, cedula,email,Integer.toString(contrasena.hashCode())); 
    response.sendRedirect("http://localhost:8080/Vendedor/");
%>
