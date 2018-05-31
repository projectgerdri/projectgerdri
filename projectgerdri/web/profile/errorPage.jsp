<%-- 
    Document   : errorPage
    Created on : 07-abr-2018, 0:28:30
    Author     : adria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/modules/footer.css">
        <link rel="stylesheet" href="../css/errorPage.css">
        <title>JSP Page</title>
    </head>
    <body>

        
        <div class="container">
            <div class="header">
                <div class="menu"><!--zona donde esta el menu pero como es la pantalla de seleccion de profile solo sale el mail del usuario y tiene la opcion de salir -->
                    <a>
                    <ul>
                        <li><a>Logout</a></li>
                        <li><a>PK9BarrisBCN</a></li>
                    </ul>
                </div>
            </div>
            
            
            
            
            <div class="cuerpo">
                
                
                <div class="selector_profile1">
                    <table>
                        <tr>
                            <td>a</td>
                            <td>b</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>2</td>
                        </tr>
                    </table>  
                </div>
                <div class="selector_profile2">
                    <table>
                        <tr>
                            <td>a</td>
                            <td>b</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>2</td>
                        </tr>
                    </table>  
                </div><div class="selector_profile3">
                    <table>
                        <tr>
                            <td>a</td>
                            <td>b</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>2</td>
                        </tr>
                    </table>  
                </div>
                
            </div>
            
            
            
            <!--footer-->
            <jsp:include page="../modules/footer.jsp" />
        </div>
        
        
        
        
       <!-- -->

    </body>
</html>
