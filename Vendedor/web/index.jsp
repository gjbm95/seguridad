<%-- 
    Document   : index
    Created on : 17/12/2017, 06:36:33 PM
    Author     : Junior
--%>

<%@page import="Controladores.Usuario"%>
<%@page import="Dominio.Producto"%>
<%@page import="DAO.Control"%>
<%@page import="Dominio.Sistema"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            <%
                   Sistema.llaveClientVendedor = getServletConfig().getServletContext().getRealPath("seguridad/Vendedor/clientVendedor_ks");
                   Sistema.llaveServerVendedor = getServletConfig().getServletContext().getRealPath("seguridad/Vendedor/serverVendedor_ks");
                   Sistema.rutaProductos = getServletConfig().getServletContext().getRealPath("basedatos/productos.xml");
                   Sistema.rutaUsuarios = getServletConfig().getServletContext().getRealPath("basedatos/usuarios.xml");
                   Sistema.rutaFactura = getServletConfig().getServletContext().getRealPath("basedatos/factura.txt");
                  
            %>
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
                                   <a href="registro.jsp"><strong>Registrate</strong></a>
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
                        out.print("<form method='post' action='iniciarsesion.jsp'>"+
                        "<div>"+
                        "    <div>"+
                        "        <center><label>Iniciar Sesión</label></center>"+
                        "    </div>"+
                        "<label >Nombre de usuario:</label>"+
                        "<input type='user' class='form-control' placeholder='Cedula' name='usuario' required>"+
                        "<label for='contrasena'>Contraseña:</label>"+
                        "<input type='password' class='form-control' id='exampleInputPassword1' placeholder='Contraseña' name='pwd' required>"+
                        "<br>"+
                        "<center>"+
                        "<button type='submit' class='btn btn-primary'>Ingresar</button>"+
                        "</center>"+
                        "</div>"
                        + "</form>"
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
                        </div>
                        <div class="col-md-1"></div>
    
                </div>
                </div>
            </header>
        <div class="container">
            <div style="width:100%; color: #FFFFFF">
                <div class="col-md-12" style="background-color:#005A31;"><strong>Productos</strong></div>
                <div class="col-md-12">
                    
                    <%  
                     Control almacen = new Control();
                       out.print("<br>"); 
                      for(Producto producto : almacen.obtenerListaProductos())
                      {
                         
                        out.print(""
                            + ""+
                            "<div class='col-md-4' style='background-color:#005A31;'>"+
                            "<br>"+
                            "    <div name='imagen' class='col-md-12'>"+
                            "        <center>"+
                            "         <IMG SRC='"+producto.getImagen()+"' WIDTH=220 HEIGHT=220>"+
                            "        </center>"+
                            "    </div>"+                      
                            "    <div class='col-md-12' style='color:#ffffff;'>"+
                            "        <div class='col-md-12'><label>"+producto.getNombre()+"</label></div>"+
                            "        <div class='col-md-12'><label>Precio: "+producto.getPrecio()+" Bs.</label></div>"+

                            "        <div class='col-md-12'><center><a href='producto.jsp?id="+producto.getId()+"'><button type='submit' class='btn btn-primary'>Comprar</button></a></center>"+

                            "        <br>"+
                            "    </div>"+  
                            "    </div>"+
                            "</div>"
                            );
                      }                      
                    %>
                </div>
            </div>   
        </div>
             <div style="width:100%; color: #FFFFFF">
                 <br>
                 <div class="col-md-12" style="background-color:#0445B5;">
                     <br>
                     <div class="col-md-2">
                       <img src="recursos/ucablogo.gif" WIDTH=150 HEIGHT=140 alt="UCAB">
                     </div>
                     <div class="col-md-10">
                         <strong>
                         Integrantes: 
                         <br>
                         Garry Jr Bruno / Aquiles Pulido 
                         <br>
                         Escuela de Ingenieria Informatica 
                         <br>
                         Seguridad Computacional 
                         </strong>
                     </div>
                     
                 </div>       
            </div>  
    </body>
</html>
