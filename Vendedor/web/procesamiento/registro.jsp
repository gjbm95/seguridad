<%-- 
    Document   : registro
    Created on : 26/12/2017, 10:05:34 AM
    Author     : Junior
--%>

<%@ page import="Dominio.*"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controladores.*" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaResponse" %>
<!DOCTYPE html>
<% 
    /*Productos prod=new Productos();
    prod.EliminarProductos(1);*/
    
    String nombre= (String) request.getParameter("name");
    String apellido= (String) request.getParameter("lastname");
    String cedula = (String) request.getParameter("cedula");
    String contrasena = (String) request.getParameter("pwd"); 
    String email = (String) request.getParameter("email");
    
    //Validacion del Captcha
    String remoteAddr = request.getRemoteAddr();
    ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
    reCaptcha.setPrivateKey("6LeYWT4UAAAAAEgGETIGFadXLo1bUY6XxEXF_Et_");
    String challenge = request.getParameter("recaptcha_challenge_field");
    String uresponse = request.getParameter("recaptcha_response_field");
    ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
    if (reCaptchaResponse.isValid()) {
      
      Cliente cli=new Cliente(nombre,apellido, cedula,email,Integer.toString(contrasena.hashCode())); 
      //LLamada a DAO
      Usuario usu=new Usuario();
      usu.AgregarCliente(cli);
      //-------------------------------------------
       out.print("<script> alert('Registro Exitoso'); </script>");
       response.sendRedirect("https://garryjunior.com.ve:8443/Vendedor/");
    } else {
      out.print("<script> alert('Error en el captcha');  window.history.back();</script>");
    }
    

%>
