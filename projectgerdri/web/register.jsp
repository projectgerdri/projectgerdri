<%@page import="utils.*"%>
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
        
        <script>
            $(document).ready(function() 
    {
	$("#register-container-password-input").keyup(checkPassLength);
	
	function checkPassLength() {		
		var currentPass = $("#register-container-password-input").val();
		
		//Decoloreamos las barras de seguridad para recalcular de cero cada vez que se cambie algo
		$("#level-1").removeClass("terrible-sec");
		$("#level-2").removeClass("low-sec");
		$("#level-3").removeClass("medium-sec");
		$("#level-4").removeClass("high-sec");
		$("#level-5").removeClass("excellent-sec");
		
		//Primero comprobamos que la longitud de la contraseña sea o no la mínima para mostrar mensajes
		if (currentPass.length < 6) {
			$("#password-too-short").show();
			$("#password-security-hint").hide();
		} else {
			$("#password-too-short").hide();
			$("#password-security-hint").show();
			checkPassSecurity();
		}		
	}

	function checkPassSecurity() {
		var currentPass = $("#register-container-password-input").val();
		var securityPoints = 0.0;
		var varietyMultiplier = 1;
		
		var hasNumbers = false;
		var hasCapitalLetters = false;
		var hasLetters = false;
		var hasSpecialChars = false;
		
		//Vamos mirando caracter a caracter para puntuar
		for (var i = 0; i < currentPass.length; i++) {
			if (currentPass.charCodeAt(i) >= 48 && currentPass.charCodeAt(i) <= 57) { //Es un número
				securityPoints += 0.75;
				if (!hasNumbers) {
					varietyMultiplier += 0.1;
					hasNumbers = true;
				}
			}
			else if (currentPass.charCodeAt(i) >= 65 && currentPass.charCodeAt(i) <= 90) { //Es una mayúscula
				securityPoints += 1.5;
				if (!hasCapitalLetters) {
					varietyMultiplier += 0.25;
					hasCapitalLetters = true;
				}
			}
			else if (currentPass.charCodeAt(i) >= 97 && currentPass.charCodeAt(i) <= 122) { //Es una minúscula
				securityPoints += 1.5;
				if (!hasLetters) {
					varietyMultiplier += 0.25;
					hasLetters = true;
				}
			}
			else { //Es un caracter especial
				securityPoints += 3;
				if (!hasSpecialChars) {
					varietyMultiplier += 0.5;
					hasSpecialChars = true;
				}
			}
			console.log("Caracter '" + currentPass.charAt(i) + "', puntuación temporal: " + securityPoints);			
		}
		
		console.log("Puntuación premultiplicador: " + securityPoints);
		securityPoints *= varietyMultiplier;
		console.log("Puntuación postmultiplicador: " + securityPoints);
		
		$("#level-1").addClass("terrible-sec");
		if (securityPoints > 10) {
			$("#level-2").addClass("low-sec");
		}
		if (securityPoints > 20) {
			$("#level-3").addClass("medium-sec");
		}
		if (securityPoints > 30) {
			$("#level-4").addClass("high-sec");
		}
		if (securityPoints > 40) {
			$("#level-5").addClass("excellent-sec");
		}
	}
    }
);
        </script>
        
        
        
        
        
        
    </head>
    <body class="background">
        
    <form action="Register_Control" id="data" method="post">
        
    <!--box login container -->
        <div class="register-container">
            <div>
                <span class="login-title"><%out.println(text.getResourcesMap().get("title_app"));%></span>
            </div>
            <div class="register-container-fields">
                <input class="register-container-password-input" type="text" name="first_name" placeholder="<%out.println(text.getResourcesMap().get("first_name"));%>">
            </div>
            <div class="register-container-fields">
                <input class="register-container-password-input" type="text" name="last_name" placeholder="<%out.println(text.getResourcesMap().get("last_name"));%>">
            </div>
            <div class="register-container-fields">
                <input class="register-container-password-input" type="mail" name="mail" pattern="[^ @]*@[^ @]*" placeholder="<%out.println(text.getResourcesMap().get("mail"));%>">
            </div>
            <div class="register-container-fields">
                <input class="register-container-password-input" type="text" name="username" placeholder="<%out.println(text.getResourcesMap().get("username"));%>">
            </div>
            <div class="register-container-fields">
                <input class="register-container-password-input" type="password" name="password" placeholder="<%out.println(text.getResourcesMap().get("password"));%>">
                <div class="password-security">
                    <table class="password-security-level">
                        <tr>
                            <td id="level-1" class="level-1"></td>
                            <td id="level-2" class="level-2"></td>
                            <td id="level-3" class="level-3"></td>
                            <td id="level-4" class="level-4"></td>
                            <td id="level-5" class="level-5"></td>
                        </tr>
                    </table>
                    <span id="password-security-hint" class="password-security-hint">Fortaleza de la contraseña: <b></b></span>
                    <span id="password-too-short" class="password-too-short">La contraseña es demasiado corta</span>
                </div>
            </div>
            <div class="register-container-fields">
                <a><%out.println(text.getResourcesMap().get("birth_date"));%></a>
                <input class ="register-container-fields-calendar" name="birth_date" type="date">
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