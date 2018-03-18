package controllers;

/**
 *
 * @author Adri
 */
import utils.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/Login_Control"})
public class Login_Control extends HttpServlet {
    Resources param;
    DbConnection DBC;
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    
    //Conexion to database
    public void init(ServletConfig cfg) throws ServletException{
        super.init(cfg);
//getServletContext().getRealPath("/") +
       
        try {
            Resources param = new Resources(getServletContext().getRealPath("/") +"resources/web_parameters.xml");
            param.fillHashMap();
            String a = param.getResourcesMap().get("db_param");
            System.out.println(a);
            DbConnection DBC = new DbConnection(Integer.parseInt(a));
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(DBC.getDbUrl(), DBC.getUserName(), DBC.getPassword());
            System.out.println("Se ha conectado");
        }catch(Exception e){
            System.out.println("err: "+ e.getMessage());
        }
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
        String query_user_name;
        String query_user_password;
        String query_user_id;
        String query_user_email;
        //variable to confirm the correct Login_Control
        boolean loginOk = false;
        
        
        //Extract data from user of the JSP index.jsp
        String w_user_name = (String) request.getParameter("username");
        String w_user_password = (String) request.getParameter("password");
        
        HttpSession U_session = request.getSession(true);       //obtain the user sesion
        U_session.setAttribute("user_name", w_user_name);       //save the sesion data
       
        //PREAPARAR FICHERO DE CONSULTAS
        String query = "SELECT U_nickname, U_password, U_id, U_email FROM users";
        try{
            set = con.createStatement();
            rs = set.executeQuery(query);
            //proces of the extraction query data
            while (rs.next()){
                query_user_name = rs.getString("U_nickname");
                query_user_password = rs.getString("U_password");
                query_user_id = rs.getString("U_id");
                query_user_email = rs.getString("U_email");
                 
                //information management
                if(query_user_name.equals(w_user_name)){
                    //name is ok, next step, check password
                    //but fist, we must encrypt the password
                    Encrypt encrypt = new Encrypt (query_user_email, query_user_id, w_user_password, query_user_name);
                    encrypt.Encrypt();
                    //and now check the pasword
                    if(encrypt.getPassword_encrypt().equals(query_user_password)){
                         loginOk = true;
                    }
                }
            }
            rs.close();
            set.close();
        }catch (Exception e){
            System.out.println("No se ha conectado");
        }
        if(loginOk){
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


