package controllers;

/**
 *
 * @author Adri
 */
import db_objects.User;
import utils.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Register;


@WebServlet(urlPatterns = {"/Register_Control"})
public class Register_Control extends HttpServlet {
    Resources db_param;
    DbConnection DBC;  
    
    
    //Conexión a la DB
    @Override
    public void init(ServletConfig cfg) throws ServletException{
        super.init(cfg);
        int db_selector;//puede que no este bien aqui
        //Seteamos la DB que estamos apuntando
        db_param = new Resources(getServletContext().getRealPath("/") + "resources/" + "web_parameters.xml");
        db_param.fillHashMap();
        db_selector = Integer.parseInt(db_param.getResourcesMap().get("db_param"));
        
        //Iniciar y testear la conexión con la DB
        DBC = new DbConnection(db_selector);
        boolean isDbConnected = DBC.testConnection();
        if (!isDbConnected) {
            //Hacer algo si la conexión con DB falla (ejemplo: recargar la página mostrando mensaje de error)
        }
        //No hay else porque es el comportamiento correcto y ya salta automáticamente a la función processRequest
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        boolean succesfullResgister = false;
        User userToCheck = new User();
        User userToInsert = new User();
        Register register = new Register();
        //Obtenemos los datos
        String form_first_name = (String) request.getParameter("first_name");
        String form_last_name = (String) request.getParameter("last_name");
        String form_mail = (String) request.getParameter("mail");
        String form_username = (String) request.getParameter("username");
        String form_password = (String) request.getParameter("password");
        String form_birth_date = (String) request.getParameter("birth_date");
        
        //Comprobar que los datos son correctos
            //se crea un objeto usuario a comprobar
        userToCheck.setFirstName(form_first_name);
        userToCheck.setLastName(form_last_name);
        userToCheck.setEmail(form_mail);
        userToCheck.setUsername(form_username);
        userToCheck.setPassword(form_password);
        userToCheck.setBirthDate(form_birth_date);
        //se pasa a comprobar
        int check = register.dataNewUserCheck(userToCheck, DBC);

        //poner los datos correctos en el objeto usuario
            //se crea un objeto usuario a insertar
        if(check == 0){//0=ok 1=username 2=mail 3=las dos 4=fallo desconocido "BBDD"
            //todo bien
            //se prepara el objeto de userToInsert
            userToInsert.setFirstName(userToCheck.getFirstName());
            userToInsert.setLastName(userToCheck.getLastName());
            userToInsert.setEmail(userToCheck.getEmail());
            userToInsert.setUsername(userToCheck.getUsername());
            userToInsert.setPassword(userToCheck.getPassword());
            userToInsert.setBirthDate(userToCheck.getBirthDate());
            register.dataNewUserInsert(userToInsert, DBC);
        }else if(check == 1){
            //falla username
            response.sendRedirect(response.encodeRedirectURL("./register.jsp?e1"));
        }else if(check == 2){
            //falla mail
            response.sendRedirect(response.encodeRedirectURL("./register.jsp?e2"));
        }else if(check == 3){
            //fallan las dos
            response.sendRedirect(response.encodeRedirectURL("./register.jsp?e3"));
        }else if(check == 4){
            //fallo con la BBDD
            response.sendRedirect(response.encodeRedirectURL("./errorPage.jsp"));
        }

        //si el registro ha sido exitoso
        if (succesfullResgister = true && check == 0){//
            response.sendRedirect(response.encodeRedirectURL("./index.jsp"));
        }else if (!succesfullResgister && check == 0){//si falla algo...
            response.sendRedirect(response.encodeRedirectURL("./errorPage.jsp"));
        } 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Register_Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Register_Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                
                    
}


