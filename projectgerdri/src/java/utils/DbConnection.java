/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author adria
 */
public class DbConnection {
    
    private int connectionMode; //1 = desarrollo 2 = preproducción 3 = producción
    private String dbUrl;
    private String userName;
    private String password;
    private String dbSelectorError;
    
    public DbConnection(int connectionMode){
        this.connectionMode = connectionMode;
        if(connectionMode == 1){
            //desarrollo
            dbUrl = "";
            userName = "";
            password = "";            
        } else if(connectionMode == 2){
            //PreProducción
            dbUrl = "jdbc:mysql://88.1.49.217/project_gerdri";
            userName = "root";
            password = "test";
        }else if (connectionMode == 3){
            //Producción
            dbUrl = "";
            userName = "";
            password = "";
        }else{
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
    
}