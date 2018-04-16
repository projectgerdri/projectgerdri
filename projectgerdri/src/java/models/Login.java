package models;

import db_objects.User;
import java.sql.SQLException;
import utils.DbConnection;
import utils.Encrypt;

/** Modelo encargado de atender a las peticiones relativas al Login del usuario */
public class Login {
    DbConnection DBC;
    Encrypt encryption;
   
    /** Método que verifica si el usuario introducido en el campo username del formulario de entrada existe o no en el sistema
     @param userToCheck Modelo usuario que se rellena con la información que encuentra la query usando el username del formulario de entrada
     @param DBC Instancia de la clase de conexión con la base de datos mediante la cual se podrá hacer la query
     @return Devuelve un <b>boolean</b> que responde a la pregunta de si el usuario existe o no tras hacer las pertinentes comprobaciones
     @see controllers.Login_Control */
    public boolean userExists(User userToCheck, DbConnection DBC) {
        boolean doesUserExist = false;
        
        String loginQuery = "SELECT U_email, U_id, U_user_name "
                + "FROM project_gerdri.Users "
                + "WHERE U_user_name = '" + userToCheck.getUsername() + "' "
                    + "AND U_deleted IS NULL";
        DBC.openConnection(loginQuery);
        
        try {
            while (DBC.rs.next()) {
                userToCheck.setEmail(DBC.rs.getString("U_email"));                        
                userToCheck.setUserId(DBC.rs.getInt("U_id"));
                userToCheck.setUsername(DBC.rs.getString("U_user_name"));
                doesUserExist = true;
            }             
        } catch (SQLException e) {
            System.out.println("Problemas al cargar los resultados de DB");
        } finally {
            DBC.closeConnection();
        }        
        
        return doesUserExist;
    }
    
    /** Método que verifica si la contraseña en el campo password del formulario de entrada se corresponde con el usuario introducido para permitir el Login
     @param userToLogin Modelo usuario que se rellena con la información que encuentra la query usando el username del formulario de entrada
     @param DBC Instancia de la clase de conexión con la base de datos mediante la cual se podrá hacer la query
     @return Devuelve un <b>boolean</b> que responde a la pregunta de si el usuario y la contraseña introducidos son correctos
     @see controllers.Login_Control */
    public boolean loginCheck(User userToLogin, DbConnection DBC) {
        boolean authentication = false;
        //encriptamos el password
        encryption = new Encrypt(userToLogin.getEmail(), userToLogin.getPassword(), userToLogin.getUsername());
        encryption.encryptPass();
        userToLogin.setPassword(encryption.getPassword_encrypt());

            String loginQuery = "SELECT U_password, U_id, U_user_name"
                + " FROM project_gerdri.Users "
                + "WHERE U_password = '" + userToLogin.getPassword()+ "' "
                    + "AND U_deleted IS NULL";
        DBC.openConnection(loginQuery);
        
        try {//si entra en la cosnulta quiere decir que es igual la contraseña y que la escritobien, guardaremos los datos en el objeto usuario y daremos el ok
            while (DBC.rs.next()) {  
                userToLogin.setUserId((DBC.rs.getInt("U_id")));
                userToLogin.setUsername((DBC.rs.getString("U_user_name")));
                userToLogin.setPassword("");//la dejamos vacia por seguridad 
                authentication = true;
            }             
        } catch (SQLException e) {
            System.out.println("Problemas al cargar los resultados de DB");
        }
        
        DBC.closeConnection();
        
        return authentication;
    }
    

}