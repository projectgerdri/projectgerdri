package controllers;

/**
 *
 * @author Adri
 */
import db_objects.User;
import utils.*;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Login;


@WebServlet(urlPatterns = {"/Login_Control"})
public class Login_Control extends HttpServlet {
    Resources db_param;
    DbConnection DBC;
    
    
    //Conexión a la DB
    public void init(ServletConfig cfg) throws ServletException{
        super.init(cfg);
        
        int db_selector = 1; //por defecto desarrollo (tiene que estar inicializada)
        
        //Comprobar a qué DB estamos apuntando
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Login login = new Login();
        boolean successfulLogin = false;
        User userToCheck = new User();
        User userToLogin = new User();
        HttpSession U_session; //Iniciamos el objeto de sesión
        boolean m_userExist = false;
        
        //Obtener las credenciales que ha introducido el usuario en el login
        String form_username = (String) request.getParameter("username");
        String form_password = (String) request.getParameter("password");
        //se crea un objeto usuario a comprobar
        userToCheck.setUsername(form_username); //en este caso solo este, porque solo miramos el usuario si existe
        //lo enviamos al modelo
        m_userExist = login.userExists(userToCheck, DBC);
        
        if(m_userExist){
            //si existe pasamos a la siguiente fase, la comprobación
            //seeteamos el objeto de usuario para el login
            userToLogin = userToCheck;
            userToLogin.setPassword(form_password);
            successfulLogin = login.loginCheck(userToLogin, DBC);
        }else{
            String err;
        }

        if (successfulLogin){
            U_session = request.getSession(true); //Obtener sesión del usuario
            U_session.setAttribute("user_name", userToLogin.getUsername()); //Guardar la información de sesión
            response.sendRedirect(response.encodeRedirectURL("./profile/profile.jsp"));
        }else{
            response.sendRedirect(response.encodeRedirectURL("./index.jsp?login_fail"));
        } 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
    processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
    processRequest(request, response);
    }                
                    
}


