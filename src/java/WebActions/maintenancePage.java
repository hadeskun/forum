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
 * @author @Marta, julian Armando
 */

/*
 * maintenancePage.java class implements a redirect to maintenance page
 */

public class maintenancePage extends Action {


    public maintenancePage(){

    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        //application scope using request method

        ViewController.nextView(req, resp,"error.jsp");
    }


}
