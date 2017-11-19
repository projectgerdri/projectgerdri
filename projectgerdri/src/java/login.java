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
import java.util.Date;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    
    //Conexion to database
    public void init(ServletConfig cfg) throws ServletException{
        String sURL="jdbc:mysql://localhost:3306/project_gerdri";
        super.init(cfg);
        String userNamebd = "root";
        String passwordbd = "";
        //conection
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(sURL, userNamebd, passwordbd);
            System.out.println("Se ha conectado");
            }catch(Exception e){
                System.out.println("No se ha conectado");//here a web page error "DATABASE"
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
    
    //Java works
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //variables that we use
        //that variables will be extract from database
        String query_user_name;
        String query_user_password;
        String query_user_id;
        String query_user_email;
        //variable to confirm the correct login
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
                if(query_user_name.equals(w_user_name)){ //check if user name exist
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
            //end of the use of database
            rs.close();
            set.close();
        }catch (Exception e){
            System.out.println("No se ha conectado");//here a web page error "DATABASE"
        }
        //now we can send out or in to the user, depending on the boolean variable 
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


