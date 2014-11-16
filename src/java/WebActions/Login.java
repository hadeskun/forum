/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebActions;


import controller.ViewController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Marta, Julian Armando
 */

/*
 * Login.java class implements a redirect to the login page
 */

public class Login extends Action {

     public  Login(){

    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        //application scope using request method

        ViewController.nextView(req, resp,"login.jsp");
    }




}
