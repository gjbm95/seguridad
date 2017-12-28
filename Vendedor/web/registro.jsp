<%-- 
    Document   : index
    Created on : 17/12/2017, 06:36:33 PM
    Author     : Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Tienda UCAB</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS\principal.css" type="text/css">
        <style>
                  a{
                    color: #FFFFFF;
                  }
                  a:hover{
                    color: #FFFFFF;
                  }
                  a:active{
                    color: #FFFFFF;
                  }
        </style>
    </head>
    <body>
            <header>
                <div style=" background-color:#0445B5; overflow-x: hidden;">
                <div class="container">
                <div class="col-md-3">
                 <a href="index.jsp"><img src="recursos/logo.png" alt="tiendaUCAB"></a>
                </div>
                <div class="col-md-5">
                    <center>
                        <div>
                            <div class="col-md-3"></div>
                            <div class="col-md-6" style="background-color:#005A31; color:#FFFFFF">
                                <center>
                                   <br>
                                   <a href=""><strong>Registrate</strong></a>
                                   <br>
                                   <br>
                                </center>
                            </div>
                            <div class="col-md-3"></div>
                            <br>
                        </div>
                    </center>
                </div>
                <div class="col-md-3" style="background-color:#005A31; color: #FFFFFF">
                 
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                        <%  
                        if(request.getSession().getAttribute("usuario")==null){
                        out.print("<form method='post' action='procesamiento/iniciarsesion.jsp'>"+
                        "<div>"+
                        "    <div>"+
                        "        <center><label>Iniciar Sesión</label></center>"+
                        "    </div>"+
                        "<label >Nombre de usuario:</label>"+
                        "<input type='user' class='form-control' id='usuario' placeholder='Cedula' name='usuario' required>"+
                        "<label for='contrasena'>Contraseña:</label>"+
                        "<input type='password' class='form-control' id='exampleInputPassword1' placeholder='Contraseña' name='pwd' required>"+
                        "<br>"+
                        "<center>"+
                        "<button type='submit' class='btn btn-primary'>Ingresar</button>"+
                        "</center>"+
                        "</form>"
                        );
                        }else{
                         out.print("<div>"
                                 + "<div><center><IMG SRC='recursos/usuario.png' WIDTH=100 HEIGHT=100></center></div>"
                                 + "<div><center>"+request.getSession().getAttribute("usuario")+"</center></div>"
                                 + "<br>"
                                 + "<div><center><a href='procesamiento/cerrarsesion.jsp'><button class='btn btn-primary'>Cerrar Sesion</button></a></center></div>"
                                );
                        }
                        %>
                         <br>
                        </div>
                        <div class="col-md-1"></div>
    
                </div>
                </div>
            </header>
        <div class="container">
            <div style="width:100%; color: #FFFFFF">
            <div class="col-md-12" style="background-color:#005A31;"><strong>Registro</strong></div>
            </div>
            
            <div class="col-md-12">
                <br>
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <form action="procesamiento/registro.jsp" method="post"  onsubmit="return validar()">
                    <div class="form-group">
                      <label for="name">Nombre:</label>
                      <input type="name" class="form-control" id="name" name="name" required pattern="[A-Za-z]+">
                    </div>
                    <div class="form-group">
                      <label for="lastname">Apellido:</label>
                      <input type="lastname" class="form-control" id="lastname" name="lastname" required pattern="[A-Za-z]+">
                    </div>  
                    <div class="form-group">
                      <label for="id">Cedula:</label>
                      <input type="id" class="form-control" id="cedula" name="cedula" required pattern="[0-9]+">
                    </div>  
                    <div class="form-group">
                      <label for="email">Correo electronico:</label>
                      <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                      <label for="pwd">Ingrese una contraseña: </label>
                      <input type="password" class="form-control" id="pwd" name="pwd" minlength="8" required>
                    </div>
                    <div class="form-group">
                      <label for="pwd">Repita la contraseña: </label>
                      <input type="password" class="form-control" id="pwd2" name="pwd2" minlength="8" required>
                    </div>
                    <%
                        ReCaptcha c = ReCaptchaFactory.newSecureReCaptcha("6LeYWT4UAAAAABnQP_9RWZUJtc_w1axpT7F0wln1", "6LeYWT4UAAAAAEgGETIGFadXLo1bUY6XxEXF_Et_", false);
                        out.print(c.createRecaptchaHtml(null, null));
                    %>
                    <button type="submit" class="btn btn-default">Listo</button>
                  </form>
                </div>                
                <div class="col-md-3"></div>
            </div>
            
        </div>
        
        <script>     
            function validar(){
              if((document.getElementById("pwd").value===document.getElementById("pwd2").value)){ 
                  if(tiene_mayusculas(document.getElementById("pwd").value))
                  {
                     if (tiene_noalfanumericos(document.getElementById("pwd").value))
                     {
                       return true;  
                     }else{
                       alert("La contraseña debe tener almenos un caracter no alfanumerico");
                       return false;
                     }
                  }else{
                    alert("La contraseña al menos debe tener una letra en Mayuscula!");
                    return false;
                  }
              }else{
                  alert("Las contrasenas son distintas!");
                  return false;
              }
            } 
            var letras_mayusculas="ABCDEFGHYJKLMNÑOPQRSTUVWXYZ";
            function tiene_mayusculas(texto){
               for(i=0; i<texto.length; i++){
                  if (letras_mayusculas.indexOf(texto.charAt(i),0)!=-1){
                     return true;
                  }
               }
               return false;
            }
            var noalfanumericos="|@#~€¬=)(/&%$·!ª¨^*Ç{[]}-.,+<>";
            function tiene_noalfanumericos(texto){
               for(i=0; i<texto.length; i++){
                  if (noalfanumericos.indexOf(texto.charAt(i),0)!=-1){
                     return true;
                  }
               }
               return false;
            }
            
        </script>

    </body>
</html>

