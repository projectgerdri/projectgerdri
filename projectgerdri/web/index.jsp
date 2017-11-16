<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page import="utils.*"%>
<html>
    <!-- Operational zone-->
    <%
        String language = request.getLocale().getLanguage();
        
        Resources prueba = new Resources(language, "global_es_ES.xml");
        prueba.fillHashMap();
        
String a = prueba.getGetResources().get("title");
        
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project geRdRi</title>
        <!--<link rel="icon" href="http://ewaiter.netau.net/fotos/logo/logo%20definitivo%2045x45.png">-->
        <link rel="stylesheet" href="css/css.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body class="fondo">
        
        <form action="login" id="data" method="post">
        
                    <div class="contenedora">
                        <div class="contenedora_interior">
                            <h2><%out.println(prueba.getGetResources().get("title"));%></h2>
                        <input class="" type="text" name="username" placeholder="Username"><br>
                        <input class="contenedora_password" type="password" name="password" placeholder="Password"><br>
                        
                        <table align="center">
                            <tr>
                                <td></td>
                                <td><button type="submit" class="contenedora_login">Sign in</button></td>
                                <td></td>
                            </tr>
                                <td><button type="submit" class="contenedora_login">Faceb</button></td>
                                <td><button type="submit" class="contenedora_login">Twitter</button></td>
                                <td><button type="submit" class="contenedora_login">Google</button></td>
                            <tr>
                                
                            </tr>
                        </table>
                        </div>
                    </div>
                    
                    </div>
                    <!--fin div contenedor de campos y botones-->
    </form>

            
        
            <!--<div>
                <input class="btnGuardar" type="submit" value="Guardar">
                <!-- Botón de login de Facebook 
                <div class="fb-login-button" data-max-rows="1" data-size="large" data-button-type="login_with" data-show-faces="false" data-auto-logout-link="false" data-use-continue-as="false">
                    
            </div>-->
            </div>
        </form>
    </body>
</html>
