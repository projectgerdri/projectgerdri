/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Adri
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    
    //PROCESO DE CONEXIÓN A LA BBDD
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
    
    //el trabajo de java
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //variables que utilizaremos
        String QUname = "";
        String QUpassword = "";
        String QUid = "";
        boolean loginOk = false;
        
        //obtenemos la sesión del usuario
        HttpSession U_session = request.getSession(true);
        //recojemos los datos del usuario
        String Uname = (String) request.getParameter("username");
        String Upassword = (String) request.getParameter("password");
        
        //guardamos datos de sesión
        U_session.setAttribute("user_name", Uname);
        
        //cifrado del password
        //
        //
        //////////////////////
        String query = "SELECT U_nickname, U_password, U_id FROM users";
        //consulta
        try{
            set = con.createStatement();
            rs = set.executeQuery(query);
            //proceso de extracción de datos de la query
            while (rs.next()){
                 QUname = rs.getString("U_nickname");
                 QUpassword = rs.getString("U_password");
                 QUid = rs.getString("U_id");
                 //hacer aqui dentro toda al gestión de la información
                 if(QUname.equals(Uname)){
                     loginOk = true;
                 }
            }
            
            //terminamos la interacción con la BBDD
            rs.close();
            set.close();
        }catch (Exception e){
            System.out.println("No se ha conectado");//here a web page error "DATABASE"
        }
        
        //una vez terminado todo realizamos acción
        
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


