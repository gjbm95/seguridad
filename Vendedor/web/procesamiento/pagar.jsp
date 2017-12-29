<%-- 
    Document   : pagar
    Created on : 27/12/2017, 08:11:00 PM
    Author     : Junior
--%>

<%@page import="Dominio.Factura"%>
<%@page import="Dominio.Sistema"%>
<%@page import="Red.Envio"%>
<%@page import="net.tanesha.recaptcha.ReCaptchaResponse"%>
<%@page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
        //Datos de la tarjeta 
        String numerotarjeta =(String) request.getParameter("numerotarjeta");
        String codigoseguridad =(String) request.getParameter("codigoseguridad");
        String tipotarjeta =(String) request.getParameter("numerotarjeta");
        String vencimiento = (String) request.getParameter("vencimiento");
        //Datos del usuario
        String nombre =(String) request.getParameter("name");
        String apellido =(String) request.getParameter("lastname");
        String cedula =(String) request.getParameter("cedula");
        String correo =(String) request.getParameter("email");
        //Datos de la Compra
        String idproducto = (String) request.getParameter("idproducto");  
        String monto = (String) request.getParameter("monto");   
        //Validacion del Captcha
        String remoteAddr = request.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LeYWT4UAAAAAEgGETIGFadXLo1bUY6XxEXF_Et_");
        String challenge = request.getParameter("recaptcha_challenge_field");
        String uresponse = request.getParameter("recaptcha_response_field");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
        int idfactura = new DAO.Control().obtenerNFactura();
        if (reCaptchaResponse.isValid()) {    
        //-------------------------------------------
        Object respcliente = Envio.enviodato("1:"+Integer.toString(numerotarjeta.hashCode())+":"+codigoseguridad
        +":"+vencimiento+":"+tipotarjeta+":"+monto+":"+cedula+":"+Sistema.numerocuenta+":"+Integer.toString(idfactura),"bancocliente");
        if (respcliente instanceof Boolean){
        if((boolean)respcliente){
          new DAO.Control().generarFactura(cedula,new Factura(idfactura,new DAO.Control().obtenerObjetoProducto(Integer.parseInt(idproducto))));
          Sistema.espera = false; 
          response.sendRedirect("https://garryjunior.com.ve:8443/Vendedor/producto_pago.jsp?id="+idproducto);
        }else 
          out.print("<script> alert('La transaccion ha fallado en el banco del cliente'); window.history.back();</script>");
        }else 
        {
          if (((String)respcliente).equals("Saldo insuficiente"))
          out.print("<script> alert('Su banco ha rechazado la solicitud por saldo insuficiente'); window.history.back();</script>");
        }
        //-------------------------------------------
        //out.print("<script> alert('Registro Exitoso'); </script>");
        } else {
           out.print("<script> alert('Error en el captcha');  window.history.back();</script>");
        }


%>
