/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

/**
 *



    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import WebActions.Action;
import WebActions.CheckUser;
import WebActions.InsertPost;
import WebActions.Login;
import WebActions.RedirectPage;
import WebActions.ShowThread;
import WebActions.deletePost;
import WebActions.listPosts;
import WebActions.maintenancePage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import org.apache.http.client.ClientProtocolException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openid4java.consumer.ConsumerManager;


/**
 *
 * @author naomi
 */
public class ControllerServlet extends HttpServlet {

  

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    private HashMap actionMap;

    @Override
    public void init() throws ServletException {

      actionMap = new HashMap();
       // ServletContext context = getServletContext();

        actionMap.put("/loginstart.do", new Login() );
        actionMap.put("/login.do", new CheckUser() );
        actionMap.put("/insertPost.do", new InsertPost() );
        actionMap.put("/forumdisplay.do", new CheckUser());
        actionMap.put("/listSentPost.do", new listPosts() );
        actionMap.put("/listReceivedPost.do", new listPosts() );
        actionMap.put("/deletePost.do", new deletePost() );
        actionMap.put("/redirectPage.do", new RedirectPage() );
        actionMap.put("/showthread.do", new ShowThread() );
        actionMap.put("/maintenancePage.do", new maintenancePage() );
     

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {


        String userPath = req.getServletPath();
        Action action = (Action) actionMap.get(userPath);
        try {          
            action.perform(req, resp);
        } catch (Exception e) {
            System.out.println(e);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest reqs, HttpServletResponse resp)
            throws IOException, ServletException {

        doPost(reqs, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       // response.setContentType("text/html;charset=UTF-8");
       // PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally {
         //   out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */





}
