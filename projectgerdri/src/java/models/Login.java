package models;

import java.sql.SQLException;
import utils.DbConnection;
import utils.Encrypt;
import utils.Resources;

public class Login {
    User userToCheck;
    DbConnection DBC;
    private String username;
    private String password;
    Encrypt encryption;

    public Login(String username, String password, DbConnection DBC) {
        this.username = username;
        this.password = password;
        this.DBC = DBC;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   
    
    public boolean loginCheck() {
        boolean authentication = false;
        
        if (userExists()) {
            encryption = new Encrypt(userToCheck.getEmail(), password, userToCheck.getUserId(), userToCheck.getUsername());
            encryption.encryptPass();
            if (encryption.getPassword_encrypt().equals(userToCheck.getPassword())) {
                authentication = true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
        
        return authentication;
    }
    
    private boolean userExists() {
        boolean doesUserExist = false;
        String loginQuery = "SELECT U_email, U_password, U_id, U_user_name FROM project_gerdri.Users WHERE U_user_name = '" + username + "' AND U_deleted IS NULL";
        
        DBC.openConnection(loginQuery);
        
        try {
            userToCheck = new User();
            while (DBC.rs.next()) {
                userToCheck.setEmail(DBC.rs.getString("U_email"));                        
                userToCheck.setPassword(DBC.rs.getString("U_password"));
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
}
