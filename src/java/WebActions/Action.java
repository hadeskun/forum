/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebActions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marta, Julian, Armando
 */

/*
 * Action.jav class implements  the perform method to receive  the reuqest
 * from controller, this is a abtract class, it's means any class can extend from ti
 *
 */
public abstract class Action {

    public abstract void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}