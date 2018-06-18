package utils;

import db_objects.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import org.apache.commons.io.IOUtils;

/**
 * Clase de utilidad que permite el envío de correos electrónicos a los usuarios de PTS, escogiendo idioma y rellenando dinámicamente parte del contenido
 * @author gurug
 */
public class Email {
    //Datos de configuración almacenados en el fichero properties para la gestión de envío de mail
    private Properties mailProps;
    private String specificHeader;
    
    //Tipo de mail que podemos enviar
    public enum TypeOfMessage { VALIDATE_ACCOUNT, WELCOME, DELETE_ACCOUNT }
    
    //Atributos relativos al envío del mail
    private User destinationUser;
    private String template;
    private String language;
    private String mailSubject;
    private String mailBody;   
    
    /**
     * Constructor que crea un mail con la información necesaria para su gestión y envío
     * @param destinationUser Modelo User con la información del jugador que recibirá el mail (así se puede acceder a cualquier información suya que necesitemos)
     * @param context Ruta genérica desde la cual se pueden acceder luego a recursos como las plantillas de mail o el fichero de propiedades
     * @param whichMail Tipo de correo electrónico que queremos enviar, a escoger entre las plantillas disponibles
     * @param language Idioma usado por el jugador en su navegador y con el que se elaborará el mail
     */
    public Email(User destinationUser, String context, TypeOfMessage whichMail, String language) {
        this.mailProps = FileParser.getDataFromProperties(context + "properties/email.properties");
        this.destinationUser = destinationUser;
        this.language = language;
        this.template = getMailTemplate(context, whichMail, language);
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
        props.put("mail.smtp.host", mailProps.getProperty("config.SMTP-SERVER"));
        props.put("mail.smtp.port", "587");

        //Creamos el objeto sesión necesario para el envío del mail, que se obtiene autenticándonos al SMTP con nuestras credenciales
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProps.getProperty("config.FROM-ADDRESS"), mailProps.getProperty("config.FROM-PASSWORD"));
            }
        });
        
        //Enviamos el mail con la información previamente establecida en el constructor y capturamos posibles excepciones. Gestionar reintentos de envío
        //más adelante en caso de fallar
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailProps.getProperty("config.FROM-ADDRESS")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinationUser.getEmail()));
            message.setSubject(mailSubject);
            message.setContent(mailBody, "text/html");
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
        mailSubject = mailProps.getProperty(specificHeader + "SUBJECT");
        StringWriter htmlContent = new StringWriter();
        
        //Gracias a la librería Apache Commons IO, parseamos el contenido de la plantilla HTML a un String que se puede enviar por JavaMail
        try {            
            IOUtils.copy(new FileInputStream(new File(template)), htmlContent, "utf-8");            
        } catch (IOException ioe) {
            System.err.println("No se ha podido convertir la plantilla HTML a String");
        }
        
        //De momento reemplazamos el contenido dinámico a pelo
        String parsedContent = htmlContent.toString();        
        parsedContent = parsedContent.replace("{NAME}", destinationUser.getFirstName()).replace("{YEAR}", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
        
        this.setMailBody(parsedContent);
    }
    
    /**
     * Método con el cual se obtiene la plantilla de mail correcta teniendo en cuenta los siguientes parámetros que recibimos del constructor:
     * @param context Ruta genérica para acceder a los recursos
     * @param whichMail Tipo de mail que se quiere enviar, a escoger entre las plantillas disponibles
     * @param lang Idioma que usa el usuario en su navegador y con el que se enviará el mail
     * @return Devuelve la ruta completa de la plantilla de mail para que sea seteada en el atributo correspondiente
     */
    private String getMailTemplate(String context, TypeOfMessage whichMail, String lang) {
        String template = context + "mail-templates/";
        int commonPath = template.length();
        switch (whichMail) {
            case VALIDATE_ACCOUNT: template += "validate-user_lang.html"; break;
            case DELETE_ACCOUNT: template += "delete-user_lang.html"; break;
            case WELCOME: template += "welcome-user_lang.html"; break;
            default: template += "welcome-user_lang.html"; break;
        }
        template = template.replace("_lang", "_" + lang);
        specificHeader = template.substring(commonPath, template.length());
        specificHeader = specificHeader.replace("_", ".").substring(0, specificHeader.indexOf(".html") + 1);
        return template;
    }
    
    /*GETTERS AND SETTERS*/
    public Properties getMailProps() {
        return mailProps;
    }

    public void setMailProps(Properties mailProps) {
        this.mailProps = mailProps;
    }
    
    public String getSpecificHeader() {
        return specificHeader;
    }

    public void setSpecificHeader(String specificHeader) {
        this.specificHeader = specificHeader;
    }
    
    public User getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(User destinationUser) {
        this.destinationUser = destinationUser;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
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
