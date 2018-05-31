<%@page import="utils.FileParser"%>
<%@page import="java.util.HashMap"%>
<div class="password-security">
    <!--Importamos el XML de Resources global_es.xml aquí también porque al parecer no lo puede coger desde register.jsp--> 
    <%
        String language = request.getLocale().getLanguage();
        HashMap<String, String> text = FileParser.getResourcesFromXml(getServletContext().getRealPath("/") + "resources/global_lang.xml", language);     
    %>
    <!--Barritas que indican la seguridad de la contraseña-->
    <table class="password-security-level">
        <tr>
            <td id="level-1" class="row-beginning"></td>
            <td id="level-2"></td>
            <td id="level-3"></td>
            <td id="level-4"></td>
            <td id="level-5" class="row-ending"></td>
        </tr>
    </table>
    <!--Inputs hidden para que el JS pueda coger los valores del XML de Resources global_es.xml-->
    <input type="hidden" id="pass-sec-terrible" value="<%out.println(text.get("pass_sec_terrible"));%>"/>
    <input type="hidden" id="pass-sec-weak" value="<%out.println(text.get("pass_sec_weak"));%>"/>
    <input type="hidden" id="pass-sec-average" value="<%out.println(text.get("pass_sec_average"));%>"/>
    <input type="hidden" id="pass-sec-strong" value="<%out.println(text.get("pass_sec_strong"));%>"/>
    <input type="hidden" id="pass-sec-excellent" value="<%out.println(text.get("pass_sec_excellent"));%>"/>
    <!--Mensajes orientativos para el usuario relativos a la seguridad de la contraseña o si ésta es demasiado corta-->
    <div class="password-security-text">
        <span id="password-security-hint"><%out.println(text.get("pass_sec_strength"));%> <b></b></span>
        <span id="password-too-short"><%out.println(text.get("pass_sec_too_short"));%></span>
    </div>                    
</div>