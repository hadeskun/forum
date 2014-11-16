/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

/**
 *
 * @author naomi
 */



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.jboss.web.tomcat.security.login.WebAuthentication;
import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
public class  controllerOPENID extends javax.servlet.http.HttpServlet {

 final static String YAHOO_ENDPOINT = "https://me.yahoo.com";
 final static String GOOGLE_ENDPOINT = "https://www.google.com/accounts/o8/id";

      //Updated version of example code from :https://crisdev.wordpress.com/2011/03/23/openid4java-login-example/

       //Add your servlet script path here - so if auth fails orsucceeds it will carry out actions - check below in doGet
       public String scr="/servlets/MyServlet";

 private ServletContext context;
 private ConsumerManager manager;
 private ConsumerManager mag;

 //Code updated by Vahid Hedayati  http://pro.org.uk
 //Removed config init - moved post to doGet - since previous coderequired it to be  a post but also to include identifier as part of
//url
 //identifier was also the same variable used for Identifier code -
 //cleaned up to make different variable and less confusion
 //doGet identifer changed to openid_identifier and it also now looks
//for openid_username which are the default variables returned from openid-selector
 //http://groups.google.com/group/openid4java/browse_thread/thread/5e8f24f51f54dc2c
 //After reading above post - store the manager in the session objectand failing with Yahoo authentication I changed code for the manager
 //manage

 public void doPost(HttpServletRequest req,HttpServletResponse
response) throws ServletException,IOException {
 doGet(req, response);
 }


 protected void doGet(HttpServletRequest req, HttpServletResponse
resp) throws ServletException, IOException {
       //New variable
       String ouser=(String)req.getParameter("OpenID");
       if (ouser==null) { ouser="";}
  //Mage is the session value of openid_consumer_manager if it is null it will generate it once
 //And where ever manager is called within code it first returns managers value by looking up session value

mag=(ConsumerManager)req.getSession().getAttribute("open_id_consumer_manager");
       if (mag==null) {
               this.manager = new ConsumerManager();

req.getSession().setAttribute("open_id_consumer_manager", manager);
       }

       String identify=(String)req.getParameter("OpenID");
       if (identify==null) { identify="";}
       if (!identify.equals("")) {
               this.authRequest(identify,ouser, req, resp);
       }else{

       //If they have succeeded it will return them to welcome
       //welcome looks up if NEWUSER = yes in the session value below and if so
       //scr now has the ip city/country/postcode  so it finalisesuser additiion by adding users ip country/city/ip as their sign up

       // if not new well they are already logged in from the relevant session values this code has put in so updats records and returns they my accoount

       //if authentication here failed or they rejected sharing their email then login page is returned

               Identifier identifier = this.verifyResponse(req);
               if (identifier != null) {
                       resp.sendRedirect(scr+"?act=welcome");
               } else {
                       resp.sendRedirect(scr+"?act=login");
               }
        }
 }

 // --- placing the authentication request ---
 public String authRequest(String userSuppliedString,String Ouser,
HttpServletRequest httpReq, HttpServletResponse httpResp) throws
IOException {
 try {
       // configure the return_to URL where your application willreceive
       // the authentication responses from the OpenID provider
       String returnToUrl = "http://localhost:8088/WEB_Forum/forumdisplay.do"; //httpReq.getRequestURL().toString();

       // --- Forward proxy setup (only if needed) ---
       // ProxyProperties proxyProps = new ProxyProperties();
       // proxyProps.setProxyName("proxy.example.com");
       // proxyProps.setProxyPort(8080);
       // HttpClientFactory.setProxyProperties(proxyProps);

       // perform discovery on the user-supplied identifier

       //Modified - Look up manager value from session
        manager = (ConsumerManager)
httpReq.getSession().getAttribute("open_id_consumer_manager");

       List discoveries = manager.discover(userSuppliedString);

       // attempt to associate with the OpenID provider
       // and retrieve one service endpoint for authentication
       DiscoveryInformation discovered = manager.associate(discoveries);

       // store the discovery information in the user's session
       httpReq.getSession().setAttribute("openid-disc", discovered);

       // obtain a AuthRequest message to be sent to the OpenID provider
       AuthRequest authReq = manager.authenticate(discovered,returnToUrl);

       FetchRequest fetch = FetchRequest.createFetchRequest();
       if (userSuppliedString.startsWith(GOOGLE_ENDPOINT)) {
               fetch.addAttribute("email", "http://axschema.org/ contact/email", true);
               fetch.addAttribute("firstName", "http://axschema.org/ namePerson/first", true);
               fetch.addAttribute("lastName", "http://axschema.org/ namePerson/last", true);
       } else if (userSuppliedString.startsWith(YAHOO_ENDPOINT)) {
               fetch.addAttribute("email", "http://axschema.org/ contact/email", true);
               fetch.addAttribute("fullname", "http://axschema.org/namePerson", true);
       } else {
               // works for myOpenID
               fetch.addAttribute("fullname", "http:// schema.openid.net/namePerson", true);
               fetch.addAttribute("email", "http://schema.openid.net/ contact/email", true);
       }
       httpReq.getSession().setAttribute("Ouser",Ouser);

       // attach the extension to the authentication request
       authReq.addExtension(fetch);
       httpResp.sendRedirect(authReq.getDestinationUrl(true));

 } catch (OpenIDException e) {
       // present error to the user
 }
 return null;
  }

  // --- processing the authentication response ---
  public Identifier verifyResponse(HttpServletRequest httpReq) {
 try {
       // extract the parameters from the authentication response
       // (which comes in as a HTTP request from the OpenID provider)
       ParameterList response = new ParameterList(httpReq.getParameterMap());

       // retrieve the previously stored discovery information
       DiscoveryInformation discovered = (DiscoveryInformation) httpReq.getSession().getAttribute("openid-disc");

       // extract the receiving URL from the HTTP request
       StringBuffer receivingURL = httpReq.getRequestURL();
       String queryString = httpReq.getQueryString();
       if (queryString != null && queryString.length() > 0)
       receivingURL.append("?").append(httpReq.getQueryString());

       // verify the response; ConsumerManager needs to be the same
       // (static) instance used to place the authentication request

       //Modified - look up session value before running verification result

       manager = (ConsumerManager)  httpReq.getSession().getAttribute("open_id_consumer_manager");
       VerificationResult verification = manager.verify(receivingURL.toString(), response, discovered);

       // examine the verification result and extract the verified
       // identifier
       Identifier verified = verification.getVerifiedId();
       String id=verified.getIdentifier();
       if (verified != null) {
          AuthSuccess authSuccess = (AuthSuccess)
verification.getAuthResponse();
if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
          FetchResponse fetchResp = (FetchResponse)
          authSuccess.getExtension(AxMessage.OPENID_NS_AX);
      List emails =fetchResp.getAttributeValues("email");
      String email = (String) emails.get(0);

                                ////////////////////////////////////////////////////////////////////////////////
                       //Custom bit each person needs to implement to interact with their application:

                       //Authenticate the user, send email verify if user exists on local system
                       //If it does {
                                       //
//httpReq.getSession().setAttribute("USERNAME",usern);

httpReq.getSession().setAttribute("LOGGEDIN", "on");
//}else{
String firstName =fetchResp.getAttributeValue("firstName");
String lastName = fetchResp.getAttributeValue("lastName");
String fullname=fetchResp.getAttributeValue("fullname");
if (fullname==null){fullname="";}

if (firstName==null){ firstName="";}

if (lastName==null) { lastName="";}

if (!fullname.equals("")) {

    if (fullname.indexOf(",")>-1)
    {

        firstName=fullname.substring(0,fullname.indexOf(","));
        lastName=fullname.substring(fullname.indexOf(","),fullname.length());
    }else if (fullname.indexOf(" ")>-1){

        firstName=fullname.substring(0,fullname.indexOf(" "));
        lastName=fullname.substring(fullname.indexOf(" "),fullname.length());
     }
 }
//This is username returned from the various services that ask for a username - it is returned as openid_username
//When using openid-selector it uses openid_identifier and openid_username - which is what this program now looks for

String ouser=(String)httpReq.getSession().getValue("Ouser");
if (ouser==null) {ouser="";}
//Adduser -- pass email address and ouser
//In Adduser class - if ouser is blank split email from 0 to substring.indexOf("@")
// generate a random number - look up current user - if exist add random number to end
//and add user with email and new username
//return bac the newuser and log in like above.

httpReq.getSession().setAttribute("NEWUSER","YES");
                               //
//httpReq.getSession().setAttribute("USERNAME",usern);

httpReq.getSession().setAttribute("LOGGEDIN", "on");

                       //}

return verified; // success
}


               }
       } catch (OpenIDException e) {
               // present error to the user
       }

       return null;
 }
}
