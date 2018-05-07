package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**Clase de utilidad encargada de la conexión con la DB. Se gestiona a qué entorno nos conectamos y la apertura y cierre de conexiones para las queries */
public class DbConnection {
    
    private int connectionMode; //0 = local, 1 = desarrollo, 2 = preproducción, 3 = producción
    private String dbUrl;
    private String userName;
    private String password;
    private String dbSelectorError;
    public Connection con;
    public Statement set;
    public ResultSet rs;
    
    /**
     * Constructor que, dependiendo del entorno al que nos queramos conectar, establece una URL de DB y credenciales adecuadas
     * @param connectionMode Entero con hasta 4 valores posibles que define a qué entorno nos vamos a conectar
     */
    public DbConnection(int connectionMode){
        this.connectionMode = connectionMode;
        
        switch (connectionMode) {
            case 0: //local
                dbUrl = "jdbc:mysql://192.168.1.25:3306/project_gerdri";
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
    
    /**
     * Método que prueba la conexión de base de datos con el entorno previamente definido antes de empezar cualquier query
     * @return Devuelve un <b>boolean</b> que responde a la pregunta de si se ha podido establecer correctamente la conexión con Db
     */
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
    
    /**
     * Método que abre la conexión con Db y ejecuta la query que le pasemos por parámetro. Lanza una excepción si no se ha podido completar la query
     * @param query String con todo el contenido de la query para que sea ejecutada
     */
    public void openConnection(String query) {
        try {
            set = con.createStatement();
            rs = set.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Algo ha ido mal al intentar hacer la query");
        }
    }
    
    /**
     * Método que simplemente cierra la conexión con Db
     */
    public void closeConnection() {
        try {
            set.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Algo ha ido mal al intentar cerrar la conexión con DB");
        }   
    }
    
}