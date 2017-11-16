<%-- 
    Document   : mailtest
    Created on : 11-nov-2017, 15:09:22
    Author     : Adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*, java.util.*, javax.mail.*"%>
<%@page import="javax.mail.internet.*, javax.activation.*" %>
<%@page import="javax.servlet.http.*, javax.servlet.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
        String to = "adriasamo12@gmail.com";
        String from = "noreply@gerdri.com";
        String host = "localhost";
        String result = "";
        //Properties
        Properties properties = System.getProperties();
        
        properties.setProperty("mail.smtp.host", host);
        Session mailSession = Session.getDefaultInstance(properties);
        
        try{
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType. TO, new InternetAddress(to));
            message.setSubject("Este es el asunto");
            message.setText("Este es el Cuerpo");
            Transport.send(message);
            result = "SI enviado";
        }catch (MessagingException mex){
            System.out.println("error: ");
            System.out.println(mex);
            result = "NO enviado";
        }
            
            
        out.println("resutl = "+result);
        %>
        
        
        
        
    </body>
</html>
