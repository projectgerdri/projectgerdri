<%@page import="java.util.HashMap"%>
<%@page import="utils.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <!-- Operational zone-->
    <%
        String language = request.getLocale().getLanguage();
        HashMap<String, String> text = FileParser.getResourcesFromXml(getServletContext().getRealPath("/") + "resources/global_lang.xml", language);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%out.println(text.get("title_register"));%></title>
        <!--<link rel="icon" href="http://ewaiter.netau.net/fotos/logo/logo%20definitivo%2045x45.png">-->
        <!-- Línea de abajo comentada porque se está importando el CSS global con el HTML parcial de más abajo. Recordar descomentar esto cuando
        tengamos los CSS bien montados y separados y hacer que el HTML parcial importe sólo el CSS de su utilidad -->
        <!--<link rel="stylesheet" href="css/css.css">-->
        <jsp:include page="modules/password_security_head.jsp" />
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
                <span class="login-title"><%out.println(text.get("title_app"));%></span>
            </div>
            <div class="register-container-fields">
                <input class="common-register-fields" type="text" name="first_name" placeholder="<%out.println(text.get("first_name"));%>">
            </div>
            <div class="register-container-fields">
                <input class="common-register-fields" type="text" name="last_name" placeholder="<%out.println(text.get("last_name"));%>">
            </div>
            <div class="register-container-fields">
                <input class="common-register-fields" type="mail" name="mail" pattern="[^ @]*@[^ @]*" placeholder="<%out.println(text.get("mail"));%>">
            </div>
            <div class="register-container-fields">
                <input class="common-register-fields" type="text" name="username" placeholder="<%out.println(text.get("username"));%>">
            </div>
            <div class="register-container-fields">
                <input id="register-container-password-input" class="common-register-fields" type="password" name="password" placeholder="<%out.println(text.get("password"));%>">
                <jsp:include page="modules/password_security.jsp" />
            </div>
            <div class="register-container-fields">
                <a><%out.println(text.get("birth_date"));%></a>
                <input class ="common-register-fields" name="birth_date" type="date">
            </div>
            <div class="register-container-fields">
                <button type="submit" class=""><%out.println(text.get("btn_form_register"));%></button>
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