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
 * author @MartaLusarreta, Julian, Armando
 */

/*
 * inserPost.jav class implements comunication between model and controller
 * receive the request from controller, the classs connect with the model
 * exucute the SQL operation, in this insert messages and response back to
 * the controller
 */
public class InsertPost extends Action {

   /*
    * Result set to retrived the set of records from database
    *
    */
    ResultSet rst =null;

    public InsertPost(){

    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {

       
         //creat objects to establich connection to the DB
        DBConnection conn =  new DBConnection ();
        Connection con = null;

        try{
        

         //create object from Queries class to verufy the user that he/she claims  is
        SqlDMLStatements  sqlInsertPost= new SqlDMLStatements();
        // get establish connection with the database
        con = conn.conecta();
         //create object from Messages to get the updated list after some insert
        Messages msg = new Messages();



        //get parameters
        HttpSession session = req.getSession();
        String sender  =(String)session.getAttribute("usernameSession");
        String  receiver  = req.getParameter("user_target");
        String subjectpost= req.getParameter("subject");
        String textpost= req.getParameter("message");

        //call the methind in Queries class
        int querysuccess =  sqlInsertPost.insertPostFromSPpostgres(con, sender,receiver,subjectpost,textpost);

            if (querysuccess  == 1){
         //get the message received of the actual user
          List<Row> posts = msg.getListPost(con, sender);


           HttpSession messages = req.getSession();
           messages.setAttribute("messages",posts);


          ViewController.nextView(req, resp,"view/forumdisplay.jsp");
        }else{
             ViewController.nextView(req, resp,"error.jsp") ;
        }


       }catch(Exception ex){
            System.out.print(ex);
       }finally{
           if (rst != null) try { rst.close(); } catch (SQLException logOrIgnore) {}
            conn.cerrarConexion();
       }

    }

}
