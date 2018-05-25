package utils;

import db_objects.User;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

/**
 * Clase de utilidad que permite el envío de correos electrónicos a los usuarios de PTS. Actualmente puede:
 * - Añadir contenido dinámico según el usuario final
 * En un futuro próximo podrá:
 * - Escoger plantillas de mails diferentes según idioma y temática del mensaje
 * - Enviar al usuario un link de validación para activar su cuenta PTS
 * @author gurug
 */
public class Email {
    //Constantes, posiblemente deban ser movidas a un fichero properties
    private final String DEFAULT_FROM_ADDRESS = "project.gerdri@gmail.com";
    private final String DEFAULT_FROM_PASSWORD = "aguaes@gua9333";
    private final String DEFAULT_SMTP_SERVER = "smtp.gmail.com";    
    
    //Los diferentes tipos de plantillas de mail de los que disponemos
    public enum TypeOfMessage { VALIDATE_NEW_ACCOUNT, FORGOT_PASSWORD, WELCOME_MESSAGE }
    
    //Atributos relativos al envío del mail
    private User destinationUser;
    private String fromAddress;
    private String fromPass;
    private String smtpServer;
    private TypeOfMessage template;
    private String language;
    private String mailSubject;
    private String mailBody;   
    
    /**
     * Constructor que crea un mail con la información necesaria para su gestión y envío
     * @param destinationUser Modelo User con la información del jugador que recibirá el mail (así se puede acceder a cualquier información suya que necesitemos)
     * @param template Tipo de mensaje que se quiere enviar, necesario para rellenar el cuerpo y título del correo electrónico
     * @param language Idioma usado por el jugador en su navegador y con el que se elaborará el mail
     */
    public Email(User destinationUser, TypeOfMessage template, String language) {
        this.destinationUser = destinationUser;
        this.fromAddress = DEFAULT_FROM_ADDRESS;
        this.fromPass = DEFAULT_FROM_PASSWORD;        
        this.smtpServer = DEFAULT_SMTP_SERVER;
        this.template = template;
        this.language = language;
        fillWithDynamicContent();
    }
    
    /**
     * Tras tener el mail preparado con toda la información, este método lo envía siguiendo los pasos establecidos por la librería JavaMail
     */
    public void sendEmail() {
        
        //Rellenamos las propiedades que establecen el modo de envío del mail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "587");

        //Creamos el objeto sesión necesario para el envío del mail, que se obtiene autenticándonos al SMTP con nuestras credenciales
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromAddress, fromPass);
            }
        });
        
        //Enviamos el mail con la información previamente establecida en el constructor y capturamos posibles excepciones. Gestionar reintentos de envío
        //más adelante en caso de fallar
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinationUser.getEmail()));
            message.setSubject(mailSubject);
            message.setText(mailBody);
            Transport.send(message);
        } 
        catch (MessagingException msge) {
            System.err.println("Couldn't send " + template.toString() + " message to " + destinationUser.getEmail() + ". Should retry before throwing an exception");
            msge.printStackTrace();
        }        

    }    
    
    /**
     * Método que rellena el título y cuerpo del mensaje según el idioma, plantilla escogida y usuario final
     */
    private void fillWithDynamicContent() {
        this.mailSubject = "¡Bienvenido!";
        this.mailBody = "Bienvenido a PTS " + destinationUser.getFirstName() + " " + destinationUser.getLastName();        
    }
    
    /*GETTERS AND SETTERS*/
    public User getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(User destinationUser) {
        this.destinationUser = destinationUser;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromPass() {
        return fromPass;
    }

    public void setFromPass(String fromPass) {
        this.fromPass = fromPass;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public TypeOfMessage getTemplate() {
        return template;
    }

    public void setTemplate(TypeOfMessage template) {
        this.template = template;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }    
    
    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }
}
