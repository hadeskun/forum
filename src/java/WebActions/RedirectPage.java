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
 * author @Marta, Julian, Armando
 */

/*
 * RedirectPage.java class implements aredirect to the
 * main page of the forum
 */

public class RedirectPage extends Action {


      public  RedirectPage(){

    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        //application scope using request method

         ViewController.nextView(req, resp,"view/forumdisplay.jsp");
    }



}
