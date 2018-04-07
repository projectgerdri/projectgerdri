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


