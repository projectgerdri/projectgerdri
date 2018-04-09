<%@page import="utils.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <!-- Operational zone-->
    <%
        String language = request.getLocale().getLanguage();
        Resources text = new Resources(language, getServletContext().getRealPath("/") + "resources/global_es.xml");
        text.fillHashMap();        
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%out.println(text.getResourcesMap().get("title_register"));%></title>
        <!--<link rel="icon" href="http://ewaiter.netau.net/fotos/logo/logo%20definitivo%2045x45.png">-->
        <link rel="stylesheet" href="css/css.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="javascript/PasswordQuality.js"></script>       
    </head>
    <body class="background">
        
    <form action="Register_Control" id="data" method="post">
        
    <!--box login container -->
        <div class="register-container">
            <div>
                <span class="login-title"><%out.println(text.getResourcesMap().get("title_app"));%></span>
            </div>
            <div class="register-container-fields">
                <input class="common-register-fields" type="text" name="first_name" placeholder="<%out.println(text.getResourcesMap().get("first_name"));%>">
            </div>
            <div class="register-container-fields">
                <input class="common-register-fields" type="text" name="last_name" placeholder="<%out.println(text.getResourcesMap().get("last_name"));%>">
            </div>
            <div class="register-container-fields">
                <input class="common-register-fields" type="mail" name="mail" pattern="[^ @]*@[^ @]*" placeholder="<%out.println(text.getResourcesMap().get("mail"));%>">
            </div>
            <div class="register-container-fields">
                <input class="common-register-fields" type="text" name="username" placeholder="<%out.println(text.getResourcesMap().get("username"));%>">
            </div>
            <div class="register-container-fields">
                <input id="register-container-password-input" class="common-register-fields" type="password" name="password" placeholder="<%out.println(text.getResourcesMap().get("password"));%>">
                <div class="password-security">
                    <table class="password-security-level">
                        <tr>
                            <td id="level-1" class="row-beginning"></td>
                            <td id="level-2"></td>
                            <td id="level-3"></td>
                            <td id="level-4"></td>
                            <td id="level-5" class="row-ending"></td>
                        </tr>
                    </table>
                    <div class="password-security-text">
                        <span id="password-security-hint">Fortaleza de la contraseña: <b></b></span>
                        <span id="password-too-short">La contraseña es demasiado corta</span>
                    </div>                    
                </div>
            </div>
            <div class="register-container-fields">
                <a><%out.println(text.getResourcesMap().get("birth_date"));%></a>
                <input class ="common-register-fields" name="birth_date" type="date">
            </div>
            <div class="register-container-fields">
                <button type="submit" class=""><%out.println(text.getResourcesMap().get("btn_form_register"));%></button>
            </div>
            <div class="login-container-social">
                <button type="submit" id="login-facebook" class="login-facebook">Facebook</button>
                <button type="submit" id="login-twitter" class="login-twitter">Twitter</button>
                <button type="submit" id="login-google" class="login-google">Google+</button>
            </div>
        </div>
        <!-- End of box login container -->      
    </form>
    </body>
</html>