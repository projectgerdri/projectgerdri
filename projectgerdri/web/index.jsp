<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page import="utils.*"%>
<html>
    <!-- Operational zone
        NO MORE CHANGE PLZ
   
    -->
    <%
        String language = request.getLocale().getLanguage();
        Resources text = new Resources(language, "global_es_ES.xml");
        text.fillHashMap();        
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project geRdRi</title>
        <!--<link rel="icon" href="http://ewaiter.netau.net/fotos/logo/logo%20definitivo%2045x45.png">-->
        <link rel="stylesheet" href="css/css.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body class="background">
        
    <form action="login" id="data" method="post">
        
    <!--box login container -->
        <div class="login_container">
            <div>
                <h1><%out.println(text.getGetResources().get("title"));%></h1>
            </div>
            <div class="login_container_username">
                <input class="login_container_username_input" type="text" name="username" placeholder="Username">
            </div>
            <div class="login_container_password">
                <input class="login_container_password_input" type="password" name="password" placeholder="Password">
            </div>
            <div class="login_container_singin">
                <button type="submit" class="contenedora_login">Sign in</button>
            </div>
            <div class="login_container_social">
                <div class="login_container_social_facebook">
                    <button type="submit" class="login_container_social_facebook_button">Facebook</button>
                </div>
                <div class="login_container_social_twitter">
                    <button type="submit" class="login_container_social_twitter_button">Twitter</button>
                </div>
                <div class="login_container_social_google">
                <button type="submit" class="login_container_social_google_button">Google+</button>
                </div>
            </div>
        </div>
        <!-- End of box login container -->
        
    </form>

    </body>
</html>
