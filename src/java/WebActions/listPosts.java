/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebActions;

import controller.ViewController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Messages;
import model.Row;
import model.SqlDMLStatements;
import model.dbConnection.DBConnection;

/**
 *
 * @author @Marta, Julian, Armando
 *
 */


/*
 * listPosts.java class implements communication between model and controller
 * it receives the request from controller, the classs connect with the model
 * exucute the SQL operation, in this case the class is in charge
 * call  the method in the model to display the messages on the main page
 * e.g. inbox mail
 */


public class listPosts extends Action {


    ResultSet rst =null;

    String userSession ="";



    public listPosts(){

    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //save user
        String user ="";
         //creat objects to establich connection to the DB
        DBConnection conn =  new DBConnection ();
        Connection con = null;

        try{


        /**
         [*****************************************************]
         * ****************************************************
         * verify if the request is perform from Identity Provider
         * checking the state of the session variable.
         * 
         * @if the session is null get the parameters from
         * identity server and create the session to track each event
         * from user on the application.
         * ****************************************************
         [*****************************************************]
         */
          userSession=(String)req.getSession().getAttribute("usernameSession");

          if (userSession==null) {

              req.getSession().setAttribute("usernameSession",user);
            }



         // get establish connection with the database
        con = conn.conecta();
         //create object from Messages to get the updated list after some insert
        Messages msg = new Messages();
        List<Row> posts  =null;


        //get parameters
        String sender  =(String)req.getSession().getAttribute("usernameSession");

        //catch the param to get posts sent or posts received
        String  sentORreceived  = req.getParameter("sentreceivedpost");
        if (sentORreceived== null) {
             posts = msg.getListPost(con, sender);

        } else if (sentORreceived.trim().equals("1")) {
        //get the message received of the actual user
         posts = msg.getListPost(con, sender);

         }else{

            posts = msg.getListPostSent(con, sender);
         }




           HttpSession messages = req.getSession();
           messages.setAttribute("messages",posts);


          ViewController.nextView(req, resp,"view/forumdisplay.jsp");

       }catch(Exception ex){
            System.out.print(ex);
       }finally{
           if (rst != null) try { rst.close(); } catch (SQLException logOrIgnore) {}
            conn.cerrarConexion();
       }

    }

}
