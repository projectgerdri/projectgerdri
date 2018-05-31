<%@page import="java.util.HashMap"%>
<%@page import="utils.*"%>
<html>
    <!-- Operational zone-->
    <%
        String language = request.getLocale().getLanguage();
        HashMap<String, String> text = FileParser.getResourcesFromXml(getServletContext().getRealPath("/") + "resources/global_lang.xml", language);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%out.println(text.get("title_login"));%></title>
        <!--<link rel="icon" href="http://ewaiter.netau.net/fotos/logo/logo%20definitivo%2045x45.png">-->
        <link rel="stylesheet" href="css/css.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body class="background">
        
    <form action="Login_Control" id="data" method="post">
        
    <!--box login container -->
        <div class="login-container">
            <div>
                <span class="login-title"><%out.println(text.get("title_app"));%></span>
            </div>
            <div class="login-container-username">
                <input class="login-container-username-input" type="text" name="username" placeholder="<%out.println(text.get("username"));%>">
            </div>
            <div class="login-container-password">
                <input class="login-container-password-input" type="password" name="password" placeholder="<%out.println(text.get("password"));%>">
            </div>
            <div class="login-container-signin">
                <button type="submit" class=""><%out.println(text.get("login"));%></button>
            </div>
            <div class="login-container-register">              
                <input type="button" value="<%out.println(text.get("register"));%>" onclick="window.location.href='register.jsp'" />
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
