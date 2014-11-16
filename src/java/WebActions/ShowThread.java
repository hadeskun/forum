/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebActions;

import java.sql.Connection;
import controller.ViewController;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Row;
import model.SqlDMLStatements;
import model.dbConnection.DBConnection;
import model.Messages;

/**
 *
 *author @Marta, Julian, Armando
 */

/*
 * ShowThread.java class implements comunication between model and controller
 * receive the request from controller, the classs connect with the model
 * exucute the SQL operation, in this case the class is in charge 
 * call  the method in the model to display the messages.
 */

public class ShowThread extends Action {


    public ShowThread(){

    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //save user
        String user ="";
         //creat objects to establich connection to the DB
        DBConnection conn =  new DBConnection ();
        Connection con = null;

        try{

             //create object from Queries class to verufy the user that he/she claims  is
            //SqlDMLStatements  sqlInsertPost= new SqlDMLStatements();
            // get establish connection with the database
            con = conn.conecta();
             //create object from Messages to get the updated list after some insert
            Messages msg = new Messages();
            List<Row> posts  =null;


            //get parameters
            HttpSession session = req.getSession();
            String sender  =(String)session.getAttribute("usernameSession");

            //catch the param to get posts sent or posts received
            String  idpost = req.getParameter("id_post");

            if (idpost== null) {
                 posts = msg.getListPost(con, sender);

           
             }else{
                int int_idpost= Integer.valueOf(idpost);
                posts = msg.getListPostbyIdpost(con,int_idpost);
             }

               HttpSession messages = req.getSession();
               messages.setAttribute("messages",posts);


               ViewController.nextView(req, resp,"view/showthread.jsp");

           }catch(Exception ex){
                System.out.print(ex);
           }finally{
           
                conn.cerrarConexion();
           }
     }

}
