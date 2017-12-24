<%-- 
    Document   : index
    Created on : 17/12/2017, 06:36:33 PM
    Author     : Junior
--%>

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
                        <form method="post" action="">
                        <div>
                            <div>
                                <center><label for="">Iniciar Sesión</label></center>
                            </div>
                        <label for="">Nombre de usuario:</label>
                        <input type="user" class="form-control" id="usuario" placeholder="Usuario">
                        <label for="contrasena">Contraseña:</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Contraseña">
                        <br>
                        <center>
                        <button type="submit" class="btn btn-primary">Ingresar</button>
                        </center>
                        </form>
                         <br>
                        </div>
                        </div>
                        <div class="col-md-1"></div>
    
                </div>
                </div>
            </header>
        <div class="container">
            <div style="width:100%; color: #FFFFFF">
               <div class="col-md-12" style="background-color:#005A31;"><strong>Producto</strong></div>
            </div>
            <div style="width:100%;">
                <br>
                <br>
                <div class="col-md-3">
                    <IMG WIDTH=220 HEIGHT=220 SRC="recursos/imac.jpg">
                </div>
                <div class="col-md-9">
                    <div class="col-md-12">
                        <div style="font-size: x-large">
                        <strong>Nombre del Producto</strong>
                        </div>
                    </div>
                    <div class="col-md-12">
                       <div style="font-size: large">
                           <strong>Precio: 10000 Bs.</strong>
                       </div>
                    </div>
                    <div class="col-md-12">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,
                    </div>
                    <div class="col-md-12">
                        <hr size="4px" color="black" />
                        <a href="pagar.jsp"><button type="submit" class="btn btn-primary">Pagar</button></a>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
