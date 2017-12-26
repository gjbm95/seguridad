<%-- 
    Document   : iniciarsesion
    Created on : 26/12/2017, 02:07:00 PM
    Author     : Junior
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Red.*" %>
<%
            new Control().start();
            String usuario = (String) request.getParameter("usuario");
            String pass = (String) request.getParameter("pwd");
            
            //Inicio de Sesion  
            session.setAttribute("usuario",usuario);
            response.sendRedirect("http://localhost:8080/Vendedor/");


%>
