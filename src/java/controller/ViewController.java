/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;



import WebActions.Action;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author @Marta, Julian, Armando
 */

/*
 * ViewController.java class implements the rediction to the corresponding pages
 * once the operation wer executed  in the  model
 *
 */
public class ViewController {




    public ViewController(){

    }

    public static void nextView(HttpServletRequest req, HttpServletResponse resp, String jsp) {

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            System.out.println("Error at dispatcher in nextView " + e);
        }

}}
