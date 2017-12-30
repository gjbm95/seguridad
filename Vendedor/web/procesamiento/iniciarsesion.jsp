<%-- 
    Document   : iniciarsesion
    Created on : 26/12/2017, 02:07:00 PM
    Author     : Junior
--%>


<%@page import="net.tanesha.recaptcha.ReCaptchaResponse"%>
<%@page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<%@page import="Controladores.Productos"%>
<%@page import="Controladores.Usuario"%>
<%@page import="Dominio.Sistema"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            if (Sistema.suiche){ 
            new Red.Control().start();
            new Red.RecepcionArchivo().start();
            Sistema.suiche = false;
            }
            String usuario = (String) request.getParameter("usuario");
            String pass = (String) request.getParameter("contra");
            
            //Inicio de Sesion  
            Productos pro=new Productos();
            Usuario usu=new Usuario();
            //Validacion del Captcha
            String remoteAddr = request.getRemoteAddr();
            ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
            reCaptcha.setPrivateKey("6LeYWT4UAAAAAEgGETIGFadXLo1bUY6XxEXF_Et_");
            String challenge = request.getParameter("recaptcha_challenge_field");
            String uresponse = request.getParameter("recaptcha_response_field");
            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
            int status=usu.obtenerStatus(usuario);
            System.out.println("el status es "+status);
            
            if((usu.IniciarSesion(usuario,pass))&&(reCaptchaResponse.isValid())&&(status==1)){
          
            session.setAttribute("usuario",new DAO.Control().obtenerObjetoCliente(usuario).getNombre() + " " + new DAO.Control().obtenerObjetoCliente(usuario).getApellido());
            response.sendRedirect("https://garryjunior.com.ve:8443/Vendedor/");
            }else{
                
                if(Sistema.intentos<=3){
                out.print("<script> alert('Usuario o Contrasena Incorrecto');"
                    + " window.location='https://garryjunior.com.ve:8443/Vendedor/';</script>");
                }
            
            if(Sistema.intentos>3){
            usu.desactivarCuenta(usuario);
            out.print("<script> alert('A alcanzado el numero de intentos maximos, su cuenta ha sido desactivada');"
                    + " window.location='https://garryjunior.com.ve:8443/Vendedor/';</script>");
            Sistema.intentos= 1;
            }
            Sistema.intentos= Sistema.intentos + 1;
               
            }

%>
