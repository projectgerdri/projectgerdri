/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author adria
 */
public class DbConnection {
    
    private int connectionMode; //0 = local, 1 = desarrollo, 2 = preproducción, 3 = producción
    private String dbUrl;
    private String userName;
    private String password;
    private String dbSelectorError;
    public Connection con;
    public Statement set;
    public ResultSet rs;
    
    public DbConnection(int connectionMode){
        this.connectionMode = connectionMode;
        
        switch (connectionMode) {
            case 0: //local
                dbUrl = "jdbc:mysql://127.0.0.1:3306/project_gerdri";
                userName = "root";
                password = "test"; break;
            case 1: //desarrollo
                dbUrl = "jdbc:mysql://88.1.49.217/project_gerdri";
                userName = "root";
                password = "test"; break;
            case 2: //preproducción
                dbUrl = "jdbc:mysql://88.1.49.217/project_gerdri_prepro";
                userName = "root";
                password = "test"; break;
            case 3: //producción
                dbUrl = "";
                userName = "";
                password = ""; break;
            default: 
                dbSelectorError = "No se ha pasado un párametro selector de BD correcto";
        }
    }
    
    public int getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(int connectionMode) {
        this.connectionMode = connectionMode;
    }
    
    public String getDbUrl() {
        return dbUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDbSelectorError() {
        return dbSelectorError;
    }
    
    public boolean testConnection() {
        boolean isConnected = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(dbUrl, userName, password);
            System.out.println("Conectado a DB correctamente");
            isConnected = true;
        } catch (Exception e) {
            System.out.println("Error al conectar con la DB");
        }
        return isConnected;
    }
    
    public void openConnection(String query) {
        try {
            set = con.createStatement();
            rs = set.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Algo ha ido mal al intentar hacer la query");
        }   
    }
    
    public void closeConnection() {
        try {
            set.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Algo ha ido mal al intentar cerrar la conexión con DB");
        }   
    }
    
}