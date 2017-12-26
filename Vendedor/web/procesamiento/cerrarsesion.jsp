<%-- 
    Document   : cerrarsesion
    Created on : 26/12/2017, 03:28:50 PM
    Author     : Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

session.invalidate();
response.sendRedirect("http://localhost:8080/Vendedor/");

%>
