<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="fb-root"></div>
        <!-- Autenticación del SDK y relleno dinámico del botón login de Facebook -->
        <script>
            window.fbAsyncInit = function() {
              FB.init({
                appId            : '182102869006134',
                autoLogAppEvents : true,
                xfbml            : true,
                version          : 'v2.10'
              });
              FB.AppEvents.logPageView();
            };

            (function(d, s, id){
               var js, fjs = d.getElementsByTagName(s)[0];
               if (d.getElementById(id)) {return;}
               js = d.createElement(s); js.id = id;
               js.src = "//connect.facebook.net/es_LA/sdk.js#xfbml=1&version=v2.10&appId=182102869006134";
               fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>
        <form action="login" id="data" method="post">
            <div>
                <span>lOGIN</span>
                    <span>
                        <input type="text" name="user">
                    </span>
                    <span>
                        <input type="text" name="pasword">
                    </span>
            </div>
            <div>
                <input class="btnGuardar" type="submit" value="Guardar">
                <!-- Botón de login de Facebook -->
                <div class="fb-login-button" data-max-rows="1" data-size="large" data-button-type="login_with" data-show-faces="false" data-auto-logout-link="false" data-use-continue-as="false">
                    
                </div>
            </div>
        </form>
    </body>
</html>
