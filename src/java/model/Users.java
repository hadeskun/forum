/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;



/**
 *
 * @author naomi
 */
public class Users {


   ResultSet rst = null;
   Statement statement = null;
   
public Users (){

}

   public ResultSet authentication(Connection conn, String username, String password) {

       //int totCertificados = 0;
       rst= null;
        try{
            StringBuffer strsql = new StringBuffer();
            strsql.append(" ");

            strsql.append("select * ");
            strsql.append(" from \"WEBforum\".users where username = '"+ username + "' and password = '" + password + "';" );

            statement=conn.createStatement();
            rst = statement.executeQuery(strsql.toString());

       }catch(java.sql.SQLException ex){
           System.out.println("Error in authetication method");
           ex.printStackTrace();
           return rst;
       }
        return rst;
    } // fin encontrar registro actual


}
