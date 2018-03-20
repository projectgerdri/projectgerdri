package utils;

import java.security.MessageDigest;

/**
 *
 * @author Adri
 */
public class Encrypt {

    private String mail;                //Received variable
    private int id;                     //Received variable
    private String password;            //Received varaible
    private String username;            //Received variable
    private String password_encrypt;
    //here set variable from caller
    public Encrypt (String w_mail, String w_password, int w_id, String w_username){ //
        mail = w_mail;                          //               /////////////////////|||\\\\\\\\\\\\\\\\\\\\\\\
        id = w_id;                              //              //that only works to user password (login page)\\
        password = w_password;                  //              \\\\\\\\\\\\\\\\\\\\\\|||////////////////////////
        username = w_username;                  //                                     //
    }
     public void encryptPass (){
        //zone of encrypt 
        String password_to_encrypt = mail+password+id+username;                 //join variables
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

    public int getId() {
        return id;
    }

    public void setId(int date) {
        this.id = date;
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