/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *author @Marta, Julian, Armando
 */

/**
 * Messages.java class is implemented from side @model it establish communication
 * with the intermediary between controller (web Actions)
 * receive the request from intermediary, in this case the class is in charge
 * establish connection with the database and a and executed Sql operation in order
 * to retrive the messages
 */
public class Messages {

    ResultSet rst = null;
   Statement statement = null;

public Messages(){

}

   public  List<Row>  getListPost(Connection conn, int iduser) {

       //int totCertificados = 0;
       rst= null;
       List<Row> rows = new ArrayList<Row>();

        try{
            StringBuffer strsql = new StringBuffer();
            strsql.append(" ");

            strsql.append("select * ");
            strsql.append(" from \"WEBforum\".posts where iduser = "+ iduser +" order by idpost DESC;" );

            statement=conn.createStatement();
            rst = statement.executeQuery(strsql.toString());

            while (rst.next()) {
             Row row = new Row( );
             row.setIdPost(rst.getInt("idpost"));
             row.setIdUser(rst.getInt("iduser"));
             row.setNumpost(rst.getInt("numpost"));
             row.setReceiver(rst.getString("receiver"));
             row.setReplydate(rst.getString("replaydate"));
             row.setSender(rst.getString("sender"));
             row.setSubjectpost(rst.getString("subjectpost"));
             row.setTextPost(rst.getString("textpost"));

            // ...
             rows.add(row);
        }


       }catch(java.sql.SQLException ex){
           System.out.println("Error in authetication method");
           ex.printStackTrace();
           
       }
        return rows;
    } // fin encontrar registro actual



   public  List<Row>  getListPost(Connection conn, String receiver) {

       //int totCertificados = 0;
       rst= null;
       List<Row> rows = new ArrayList<Row>();

        try{
            StringBuffer strsql = new StringBuffer();
            strsql.append(" ");

            strsql.append("select * ");
            strsql.append(" from \"WEBforum\".posts where receiver = '"+ receiver +"' order by idpost DESC;" );

            statement=conn.createStatement();
            rst = statement.executeQuery(strsql.toString());

            while (rst.next()) {
             Row row = new Row( );
             row.setIdPost(rst.getInt("idpost"));
             row.setIdUser(rst.getInt("iduser"));
             row.setNumpost(rst.getInt("numpost"));
             row.setReceiver(rst.getString("receiver"));
             row.setReplydate(rst.getString("replaydate"));
             row.setSender(rst.getString("sender"));
             row.setSubjectpost(rst.getString("subjectpost"));
             row.setTextPost(rst.getString("textpost"));

            // ...
             rows.add(row);
        }


       }catch(java.sql.SQLException ex){
           System.out.println("Error in authetication method");
           ex.printStackTrace();

       }
        return rows;
    } // end get list of messages




   public  List<Row>  getListPostSent(Connection conn, String sender) {

       //int totCertificados = 0;
       rst= null;
       List<Row> rows = new ArrayList<Row>();

        try{
            StringBuffer strsql = new StringBuffer();
            strsql.append(" ");

            strsql.append("select * ");
            strsql.append(" from \"WEBforum\".posts where sender = '"+ sender +"'  order by idpost DESC ;" );

            statement=conn.createStatement();
            rst = statement.executeQuery(strsql.toString());

            while (rst.next()) {
             Row row = new Row( );
             row.setIdPost(rst.getInt("idpost"));
             row.setIdUser(rst.getInt("iduser"));
             row.setNumpost(rst.getInt("numpost"));
             row.setReceiver(rst.getString("receiver"));
             row.setReplydate(rst.getString("replaydate"));
             row.setSender(rst.getString("sender"));
             row.setSubjectpost(rst.getString("subjectpost"));
             row.setTextPost(rst.getString("textpost"));

            // ...
             rows.add(row);
        }


       }catch(java.sql.SQLException ex){
           System.out.println("Error in authetication method");
           ex.printStackTrace();

       }
        return rows;
    } // end get list of messages




    public  List<Row>  getListPostbyIdpost(Connection conn, int idpost) {

       //int totCertificados = 0;
       rst= null;
       List<Row> rows = new ArrayList<Row>();

        try{
            StringBuffer strsql = new StringBuffer();
            strsql.append(" ");

            strsql.append("select * ");
            strsql.append(" from \"WEBforum\".posts where idpost = "+ idpost +" ;" );

            statement=conn.createStatement();
            rst = statement.executeQuery(strsql.toString());

            while (rst.next()) {
             Row row = new Row( );
             row.setIdPost(rst.getInt("idpost"));
             row.setIdUser(rst.getInt("iduser"));
             row.setNumpost(rst.getInt("numpost"));
             row.setReceiver(rst.getString("receiver"));
             row.setReplydate(rst.getString("replaydate"));
             row.setSender(rst.getString("sender"));
             row.setSubjectpost(rst.getString("subjectpost"));
             row.setTextPost(rst.getString("textpost").toString());

            // ...
             rows.add(row);
        }


       }catch(java.sql.SQLException ex){
           System.out.println("Error in authetication method");
           ex.printStackTrace();

       }
        return rows;
    } // end get list of messages
}


