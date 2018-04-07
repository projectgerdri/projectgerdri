package models;

import db_objects.User;
import java.sql.SQLException;
import utils.DbConnection;
import utils.Encrypt;

public class Login {
    DbConnection DBC;
    Encrypt encryption;
   
    
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
        }
        
        DBC.closeConnection();
        
        return doesUserExist;
    }
    
    
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