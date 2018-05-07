package models;

import db_objects.User;
import java.sql.SQLException;
import utils.DbConnection;
import utils.Encrypt;

/** Modelo encargado de atender a las peticiones relativas al Registro del usuario
* @see utils.Encrypt
* @see utils.DbConnection
* @see controllers.Register_Control
*/
public class Register {
    DbConnection DBC;
    Encrypt encryption;

    /**
     * Función que verfica si el nombre de usuario o correo electrónico introducido en el formulario de registro existe ya en nuestra DB
     * @param userToCheck Modelo usuario que se rellena con la información que encuentra la query usando el username del formulario de registro
     * @param DBC Instancia de la clase de conexión con la base de datos mediante la cual se podrá hacer la query
     * @return Devuelve un <b>int</b> con cuatro códigos númericos posibles para determinar qué coincide de ese usuario en la DB
     */
    public int dataNewUserCheck (User userToCheck, DbConnection DBC){//comprueba que los datos sean correctos
        int dataCheck = 0;  //0=ok 1=username 2=mail 3=las dos 4=fallo desconocido "BBDD"
        boolean userExist = false;
        boolean mailExist = false;        
        boolean bdFail = false;

        String query = "select U_user_name from project_gerdri.Users where U_user_name = '"+userToCheck.getUsername()+"'";
        DBC.openConnection(query);
        try 
        {
            while (DBC.rs.next()) 
            {
                String temp_username = (DBC.rs.getString("U_user_name"));      
                if(temp_username.equals(userToCheck.getUsername())){
                    userExist = true;
                }
            }             
        } catch (SQLException e) {
            bdFail= true;
            System.out.println("Problemas al cargar los resultados de DB");
        }
        DBC.closeConnection();
        
        //compruebo si el correo existe
        query = "select U_email from project_gerdri.Users where U_email in ('"+userToCheck.getEmail()+"')";
        DBC.openConnection(query);
        try 
        {
            while (DBC.rs.next() && dataCheck != 1) 
            {
                String temp_mail = (DBC.rs.getString("U_email"));      
                if(temp_mail.equals(userToCheck.getEmail())){ 
                    mailExist = true;
                }
            }             
        } catch (SQLException e) {
            bdFail= true;
            System.out.println("Problemas al cargar los resultados de DB");
        }
        DBC.closeConnection();
        
        //defino el retorno
        if(userExist){
            dataCheck = 1;
        }
        if (mailExist){
            dataCheck = 2;
        }
        if(userExist && mailExist){
            dataCheck = 3;
        }
        if(bdFail){
            dataCheck = 4;
        }
        
        return dataCheck;//0=ok 1=username 2=mail 3=las dos 4=fallo desconocido "BBDD"
    }
    
    /**
     * Función que añade un nuevo usuario a la DB con la información del formulario de registro rellenado
     * @param userToCInsert Modelo usuario que se rellena con la información que encuentra la query usando el username del formulario de registro
     * @param DBC Instancia de la clase de conexión con la base de datos mediante la cual se podrá hacer la query
     * @return Devuelve un <b>boolean</b> respondiendo a la pregunta de si se ha podido insertar el nuevo usuario en DB o no
     */
    public boolean dataNewUserInsert(User userToCInsert, DbConnection DBC) throws SQLException {//Retorna true or false depndiendo si se ha insertado en la BD
        boolean dataRegistered = false;//por defecto entendemos que no se ha insertado
        encryption = new Encrypt(userToCInsert.getEmail(), userToCInsert.getPassword(), userToCInsert.getUsername());//encriptado
        encryption.encryptPass();
        userToCInsert.setPassword(encryption.getPassword_encrypt());
  
        String registerQuery = 
                "INSERT INTO Users (U_first_name, U_last_name, U_birth_date, U_email, U_user_name, U_nickname, U_password, U_register_date, U_confirmed )\n" +
                "VALUES (\""+userToCInsert.getFirstName()+"\", "
                + "\""+userToCInsert.getLastName()+"\", "
                + "\""+userToCInsert.getBirthDate()+"\", "//cambiar por la fecha
                + "\""+userToCInsert.getEmail()+"\", "
                + "\""+userToCInsert.getUsername()+"\", "
                + "\""+userToCInsert.getUsername()+"\", " //por ahora es el mismo, más adelante se cambia
                + "\""+userToCInsert.getPassword()+"\","
                + " sysdate(),"
                + "\""+1+"\")";//por ahora se pone 1 para dejarlo confirmado ---> con el mail se tiene que poner a 0 para que se confirme
                
        
        DBC.openConnection(registerQuery);
        try{
            DBC.set.executeUpdate(registerQuery);
            dataRegistered = true;
        } catch (Exception e){
            System.out.println("Problemas en el insert: " +e.getMessage());
        }
        DBC.closeConnection();
        
        return dataRegistered;
    }
    
}
