/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author naomi
 */
public class SqlDMLStatements {

     Statement statement = null;
      

    public SqlDMLStatements (){

    }


  /*
   [////////////////////////////////////////
   * insert a message in the table
   //////////////////////////////////////7
   */

  public int insertPost(Connection conn, String sender, String receiver,
                          String subjectpost, String textpost) throws SQLException {

        ResultSet rst= null;
        int querysuccess = 0;
        try{
       
            StringBuffer strsql = new StringBuffer();
            strsql.append(" ");
            
            //query to get iduser receiver and number of post

            strsql.append("SELECT  idpost, u.iduser from  \"WEBforum\".posts as p,  ");
            strsql.append(" (Select Distinct u.iduser   From \"WEBforum\".users u  where u.username = 'martika') u order by p.idpost DESC");
           



            statement=conn.createStatement();
            rst = statement.executeQuery(strsql.toString());
            if(rst.next()) {
            //get count and user
            long idpost = rst.getInt("idpost") + 1;
            int iduser = rst.getInt("iduser");
            long numpost = idpost;
            
            //get time
            String replaydate = getDateTime();

            //clear the Stringbuffer content
	   strsql.delete(0, strsql.length());

           //escape the special characters
           //replace the actual char by escape format
           subjectpost = subjectpost.replaceAll("'", "''");
           textpost = textpost.replaceAll("'", "''");

            strsql.append(" ");
            strsql.append("Insert into  \"WEBforum\".posts ( ");
            strsql.append(" idpost, sender, receiver, replaydate, numpost, subjectpost, textpost, iduser)");
            strsql.append(" VALUES ( "+idpost +", \'"+ sender +"\' , \'" + receiver +"\' , \'"+ replaydate + "\', " + numpost +
                    " , \'" + subjectpost + "\' , \'"+  textpost + "\' , " + iduser + ");" );
            //reset the statement
            statement = null;
            statement=conn.createStatement();
            querysuccess=statement.executeUpdate(strsql.toString());
            }
       }catch(Exception ex){
           System.out.println("Error in inserpostmethod, SqlDMLStatement");
           ex.printStackTrace();

       }

        finally{
            if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore){}
        }
        return querysuccess;
     // fin encontrar registro actual

}


   public int deletePost(Connection conn, int idpost) throws SQLException {

       
        int querysuccess = 0;
        try{

            StringBuffer strsql = new StringBuffer();
            strsql.append(" ");

            strsql.append("Delete from  \"WEBforum\".posts  ");
            strsql.append(" where idpost = "+ idpost+" ;");

            //reset the statement
            statement = null;
            statement=conn.createStatement();
            querysuccess=statement.executeUpdate(strsql.toString());
            
       }catch(Exception ex){
           System.out.println("Error in inserpostmethod, SqlDMLStatement");
           ex.printStackTrace();

       }
        finally{
            if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore){}
        }
        return querysuccess;
     // fin encontrar registro actual

}

  private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }



  public int insertPostFromSPpostgres(Connection conn, String sender, String receiver,
                          String subjectpost, String textpost) throws SQLException {

       int querysuccess =0;
            Statement stmt = null;
        try{

            //get time
            String replaydate = getDateTime();

            // PostgreSQL needs a transaction to do this...
            //conn.setAutoCommit(false);

            //escape the special characters
           //replace the actual char by escape format
           subjectpost = subjectpost.replaceAll("'", "''");
           textpost = textpost.replaceAll("'", "''");

            /*The JDBC driver is expecting a single scalar value returned from your
            function.  It retrieves the first column in the first row and detects that
            it is a varchar, not the Types.OTHER that was registered.
             CallableStatement interface is not good  for this operation.

             */

            //callable statemnet are used just with cursors

             statement = conn.createStatement();
           if ( statement.execute("SELECT  \"WEBforum\".insert_post('"+sender+ "' , '" + receiver + "' , '" +  replaydate+ "' , '" +subjectpost +"' , '"+textpost+ "');"))
            querysuccess=1;
        

       }catch(Exception ex){
           System.out.println("Error in inserpost  method ===> [insertPostFromSPpostgres]");
           ex.printStackTrace();

       }finally{
         //if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore){}
       }
        return querysuccess;
     // fin encontrar registro actual

}



   /*intialize function*/
    public static String buildProcedureCallRanges(String procedureName, int paramCount) {
        StringBuffer sb = new StringBuffer("{call \"WEBforum\""+"."+procedureName+"(");
        for (int n = 1; n <= paramCount; n++) {
            sb.append("?");
            if (n < paramCount) sb.append(",");
        }
        return sb.append(")}").toString();
    }




    /*initialize functions*/
    public static String buildProcedureCall(String procedureName, int paramCount) {
         StringBuffer sb = new StringBuffer("{call \"WEBforum\""+"."+procedureName+"(");
        for (int n = 1; n <= paramCount; n++) {
            sb.append("?");
            if (n < paramCount) sb.append(",");
        }
        return sb.append(")}").toString();
    }
}
