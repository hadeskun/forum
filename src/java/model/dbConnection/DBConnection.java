/*
 * Conexion.java
 *
 * Created on 16 de junio de 2008, 02:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package model.dbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;


/**
 *
 * @author Marta Julian Armando
 */
public class DBConnection {
     Connection conn = null;

    /** Creates a new instance of Conexion */
    public DBConnection() {
    }

    //metodo para probar la conexion
      public Connection conecta()throws SQLException{


         try{
           Context initCtx = new InitialContext();
           Context envCtx = (Context) initCtx.lookup("java:comp/env");
           DataSource ds = (DataSource) envCtx.lookup("jdbc/WEB_Forum");
           conn = ds.getConnection();

           if (conn != null)System.out.println("CONECTADO AL SERVIDOR 10.1.8.41");
           else System.out.println("NO HAY CONEXION CON EL SERVIDOR 10.1.8.41");

         } catch(javax.naming.NameNotFoundException e) {               // Hija de NamingException
  e.printStackTrace();


         }catch(javax.naming.NamingException e){
             e.printStackTrace();
         }

        return conn;
      }

      public void cerrarConexion(){
         try{
           if (!conn.isClosed()){
              conn.close();
           }
          System.out.println("DESCONECTADO DEL SERVIDOR 10.1.8.41");
          }catch(java.sql.SQLException ex){
              System.out.println(ex);
              ex.printStackTrace();

          }
      }



}