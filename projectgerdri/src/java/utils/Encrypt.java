package utils;

import java.security.MessageDigest;

/**Clase de utilidad encargada de encriptar contraseñas u otras cadenas de texto. Se usa cifrado SHA-256 por defecto*/
public class Encrypt {
    
    //TODO: Estos campos no deberían ser atributos ya que para otro cifrado se pueden usar otros. Mejor pasarlos sólo como
    //parámetros en el método de encriptado
    private String mail;
    private String password;
    private String username;
    private String password_encrypt;

    /**
     * Constructor que define los 3 parámetros sobre los que se realizará el encriptado
     * @param w_mail Email del usuario
     * @param w_password Contraseña del usuario
     * @param w_username Username del usuario
     */
    public Encrypt (String w_mail, String w_password, String w_username){
        mail = w_mail;  //that only works to user password (login page)\\
        password = w_password;
        username = w_username;
    }
    
    /**
     * Método encargado de generar la cadena cifrada a partir de los parámetros de usuario establecidos anteriormente
     */
    public void encryptPass (){
        //zone of encrypt 
        String password_to_encrypt = mail+password+username;  //join variables
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password_to_encrypt.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            password_encrypt = hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
     }
    
    //getters and setters 
    public String getPassword_encrypt() {
        return password_encrypt;
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}