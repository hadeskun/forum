/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebActions;

import controller.ViewController;
import java.sql.Connection;
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
 * @author @Marta, julian , Armando
 */

/*
 * deletePost.java class implements communication between model and controller
 * it receives the request from controller, the classs connect with the model
 * exucute the SQL operation, in this case the class is in charge
 * call  the method in the model to delete the messages.
 */

public class deletePost extends Action {



     public deletePost() {
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
        int idpost= Integer.valueOf(req.getParameter("id_post"));
        int querysuccess =  sqlInsertPost.deletePost(con, idpost);

            if (querysuccess  == 1){
         //get the message received of the actual user
            List<Row> posts = msg.getListPost(con, sender);
           HttpSession messages = req.getSession();
           messages.setAttribute("messages",posts);


          ViewController.nextView(req, resp,"view/forumdisplay.jsp");
        }


       }catch(Exception ex){
            System.out.print(ex);
       }finally{
          
            conn.cerrarConexion();
       }

    
    }
}
