/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;
import java.sql.Connection;
import java.sql.ResultSet;
 import java.util.List;


/**
 *
 * @author naomi
 */
public class Queries {
    ResultSet rst = null;




   public ResultSet connectAuth(Connection con,String username, String password) {

      
        Users auth = new Users();       
        String rst_user = "";

//       rst = auth.authentication(con, username, password ) ;
      
        return rst ;
    } // end connectAuth


    public List<Row> queryMessages(Connection conn, int iduser) {

       //creat objects to establich connection to the DB
        
         Messages msg = new Messages();
         List<Row> rows = null;

        String rst_user = "";

        try{
           
           // rows = msg.getListPost(conn, iduser) ;
            
       }catch(Exception ex){
            System.out.print(ex);
       }finally{
         
       }
        return  rows ;
    } // end connectAuth



  

}
