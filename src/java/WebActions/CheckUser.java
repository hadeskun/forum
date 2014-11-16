/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebActions;

import java.sql.Connection;
import controller.ViewController;

import forumMychum.registration.openid.OpenIdRegistrationPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Messages;
import model.Row;
import model.Users;
import model.dbConnection.DBConnection;
import org.openid4java.message.AuthRequest;

/**
 *
 * @author Marta, Julian, Armando
 */

/*
 * CheckUser.java class implements comunication between model and controller
 * receive the request from controller, the classs connect with the model
 * exucute the SQL operation, in this case the class is in charge to verify
 * each authentication, compared the users credentials agains database data
 */

public class CheckUser extends Action {
    
    String p_username="";
    String p_password="";
    String userSession ="";
  

    

    public CheckUser(  String param_user ,  String param_password){
       this.p_password=param_password;
       this.p_username =param_user;

    }

    public CheckUser(){

      

    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //save username from resultset
        String user ="";
        ResultSet rst =null;

         //creat objects to establich connection to the DB
        DBConnection conn =  new DBConnection ();
        Connection con = null;

  try{



      /**
       [***************************************************************]
       * Get the parameters and verify which @authentication method
       * will be used......
       * @OpenID
       * @Traditional
       [***************************************************************]
       */

       //Obtain the userSuppliedIdentifier provide from user form
       String userSuppliedIdentifie = req.getParameter("OpenID");

      if (userSuppliedIdentifie != null && userSuppliedIdentifie.trim().length()>0){
          
          //start the authentication
          OpenIdRegistrationPage mount = new OpenIdRegistrationPage();
          //get the URL to communicate with OP
          AuthRequest authRequest = mount.onSubmit(userSuppliedIdentifie, req, resp);
          //resp.setBufferSize(0);
          //resp.sendRedirect(authRequest.getDestinationUrl(true));
          //req.getRequestDispatcher(authRequest.getDestinationUrl(true)).include(req, resp);
          resp.flushBuffer();
          ViewController.nextView(req, resp,authRequest.getDestinationUrl(true));
          //resp.sendRedirect(authRequest.getDestinationUrl(true));
          return;
           

       }
      else{
        //get parameters
        p_username = req.getParameter("username");
        p_password = req.getParameter("password");

         //create object from Queries class to verufy the user that he/she claims  is
        //Queries  queries= new Queries();

         Users auth = new Users();

        Messages  msg= new Messages();
        // get establish connection with the database
        con = conn.conecta();

        
        rst=auth.authentication(con, p_username, p_password);

        
        if (rst.next()) {


           //retrive the username
             user = rst.getString("username");

          userSession=(String)req.getSession().getAttribute("usernameSession");

          if (userSession==null) {
              req.getSession().setAttribute("usernameSession",user);
            }
          

           List<Row> posts = msg.getListPost(con, user);

          
           HttpSession messages = req.getSession();
           messages.setAttribute("messages",posts);


          ViewController.nextView(req, resp,"login redirect.jsp");
        
        }else{
          ViewController.nextView(req, resp,"invalidlogin.jsp");
        }

      }



   }catch(Exception ex){
        System.out.print(ex);
   }finally{
       if (rst != null) try { rst.close(); } catch (SQLException logOrIgnore) {}
        conn.cerrarConexion();
   }

    }



}
