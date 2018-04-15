$(document).ready(function() 
    {   
        //Lanzamos el evento de calcular la seguridad de contraseña al abrir la página y cada vez que se escriba algo en el input padre
	$("#register-container-password-input").keyup(checkPassLength);
	
	function checkPassLength() {
            //Capturamos el valor del input padre (desde fuera de esta función no reconoce los cambios)
            var currentPass = $("#register-container-password-input").val();

            //Decoloreamos las barras de seguridad para recalcular de cero cada vez que se cambie algo
            $("#level-1").removeClass("terrible-sec");
            $("#level-2").removeClass("low-sec");
            $("#level-3").removeClass("medium-sec");
            $("#level-4").removeClass("high-sec");
            $("#level-5").removeClass("excellent-sec");

            //Borramos también los textos para recalcular de cero cada vez que se cambie algo
            $("#password-security-hint > b").removeClass("terrible-sec-text");
            $("#password-security-hint > b").removeClass("low-sec-text");
            $("#password-security-hint > b").removeClass("medium-sec-text");
            $("#password-security-hint > b").removeClass("high-sec-text");
            $("#password-security-hint > b").removeClass("excellent-sec-text");

            //Primero comprobamos que la longitud de la contraseña sea o no la mínima para decidir qué mensaje mostrar
            //Sólo pasamos a calcular seguridad de contraseña si la longitud de ésta es igual o mayor a 6
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
                //Preparamos variables para calcular la puntuación de seguridad de la contraseña
		var currentPass = $("#register-container-password-input").val();
		var securityPoints = 0.0;
		var varietyMultiplier = 1;
		
		var hasNumbers = false;
		var hasCapitalLetters = false;
		var hasLetters = false;
		var hasSpecialChars = false;
		
		//Vamos mirando caracter a caracter para puntuar
		for (var i = 0; i < currentPass.length; i++) {
                    //Si es un número...
                    if (currentPass.charCodeAt(i) >= 48 && currentPass.charCodeAt(i) <= 57) {
                        securityPoints += 0.75;
                        if (!hasNumbers) {
                            varietyMultiplier += 0.1;
                            hasNumbers = true;
                        }
                    }
                    //Si es una mayúscula...
                    else if (currentPass.charCodeAt(i) >= 65 && currentPass.charCodeAt(i) <= 90) {
                        securityPoints += 1.5;
                        if (!hasCapitalLetters) {
                            varietyMultiplier += 0.25;
                            hasCapitalLetters = true;
                        }
                    }
                    //Si es una minúscula...
                    else if (currentPass.charCodeAt(i) >= 97 && currentPass.charCodeAt(i) <= 122) {
                        securityPoints += 1.5;
                        if (!hasLetters) {
                            varietyMultiplier += 0.25;
                            hasLetters = true;
                        }
                    }
                    //Si es un caracter especial...
                    else {
                        securityPoints += 3;
                        if (!hasSpecialChars) {
                            varietyMultiplier += 0.5;
                            hasSpecialChars = true;
                        }
                    }
                    //console.log("Caracter '" + currentPass.charAt(i) + "', puntuación temporal: " + securityPoints);			
		}
		
		//console.log("Puntuación premultiplicador: " + securityPoints);
		securityPoints *= varietyMultiplier; //Multiplicador de puntos cada vez que hay un tipo de caracter nuevo
		//console.log("Puntuación postmultiplicador: " + securityPoints);
		
                //Monstramos el mensaje y barras de colores correspondientes al nivel de seguridad obtenido
		$("#level-1").addClass("terrible-sec");
                $("#password-security-hint > b").addClass("terrible-sec-text").text($("#pass-sec-terrible").val());
		if (securityPoints > 10) {
			$("#level-2").addClass("low-sec");
                        $("#password-security-hint > b").addClass("low-sec-text").text($("#pass-sec-weak").val());
		}
		if (securityPoints > 20) {
			$("#level-3").addClass("medium-sec");
                        $("#password-security-hint > b").addClass("medium-sec-text").text($("#pass-sec-average").val());
		}
		if (securityPoints > 30) {
			$("#level-4").addClass("high-sec");
                        $("#password-security-hint > b").addClass("high-sec-text").text($("#pass-sec-strong").val());
		}
		if (securityPoints > 40) {
			$("#level-5").addClass("excellent-sec");
                        $("#password-security-hint > b").addClass("excellent-sec-text").text($("#pass-sec-excellent").val());
		}
	}
    }
);


