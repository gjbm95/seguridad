<%-- 
    Document   : confirmacion
    Created on : 29/12/2017, 01:24:55 PM
    Author     : Junior
--%>

<%@page import="net.tanesha.recaptcha.ReCaptchaResponse"%>
<%@page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<%@page import="Red.Envio"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
                  <%
                //Validacion del Captcha
                String remoteAddr = request.getRemoteAddr();
                ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
                reCaptcha.setPrivateKey("6LeYWT4UAAAAAEgGETIGFadXLo1bUY6XxEXF_Et_");
                String challenge = request.getParameter("recaptcha_challenge_field");
                String uresponse = request.getParameter("recaptcha_response_field");
                ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
                if (reCaptchaResponse.isValid()) { 
                //Datos de la tarjeta 
                String numerotarjeta =(String) request.getParameter("numerotarjeta");
                String codigoseguridad =(String) request.getParameter("codigoseguridad");
                String tipotarjeta =(String) request.getParameter("tipo");
                String vencimiento = (String) request.getParameter("vencimiento");
                //Datos del usuario
                String nombre =(String) request.getParameter("name");
                String apellido =(String) request.getParameter("lastname");
                String cedula =(String) request.getParameter("cedula");
                String correo =(String) request.getParameter("email");
                //Datos de la Compra
                String idproducto = (String) request.getParameter("idproducto");  
                String monto = (String) request.getParameter("monto");   
                request.getSession().setAttribute("numerotarjeta",numerotarjeta);
                request.getSession().setAttribute("codigoseguridad",codigoseguridad);
                request.getSession().setAttribute("tipo",tipotarjeta);
                request.getSession().setAttribute("vencimiento",vencimiento);
                request.getSession().setAttribute("name",nombre);
                request.getSession().setAttribute("lastname",apellido);
                request.getSession().setAttribute("cedula",cedula);
                request.getSession().setAttribute("email",correo);
                request.getSession().setAttribute("idproducto",idproducto);
                request.getSession().setAttribute("monto",monto);
                
                int numero=10;
                Random rand = new Random(); 
                numero = rand.nextInt(500000);
              
                request.getSession().setAttribute("confirmacion",Integer.toString(numero));
                if(!(boolean)Envio.enviodato("2:"+numero+":"+Integer.toString((numerotarjeta).hashCode())+":"+codigoseguridad+":"+vencimiento,"bancocliente")){
                  out.print("<script> alert('La transaccion ha fallado en el banco del cliente'); window.history.back();</script>");
                }else 
                  {
                    out.print("");
                  }
                }else 
                {
                   out.print("<script> alert('Error en el captcha');  window.history.back();</script>");
                }
        %>  
          <script type="text/javascript">
                $(window).on('load',function(){
                    $('#myModal').modal('show');
                });
          </script>  
          <!-- Modal -->
          <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                    <form action="pagar.jsp" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title"><strong>Confirmación de la Transacción</strong></h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                          <label for="text">Código de Seguridad del Banco:</label>
                          <input type="password" class="form-control" name="confirmacion" id="confirmacion" required>          
                        </div> 
                    </div>
                    <div class="modal-footer">
                      <button type="submit" class="btn btn-default">Continuar</button>
                    </div>
                    </form>
                  </div>

                </div>
          </div> 

        

    </body>
</html>
