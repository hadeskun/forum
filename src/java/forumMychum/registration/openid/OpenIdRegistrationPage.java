/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package forumMychum.registration.openid;

import forum.mychum.model.RegistrationService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;

/**
 *
 * @author naomi
 */
public class OpenIdRegistrationPage{


    /**
     * if the @authe method is OPENId
     * a URL is used to redirect the response from OP Open Identity provider
     */
    private String returnToUrl;
 
   /* public OpenIdRegistrationPage() {
		//this(new PageParameters());
        returnToUrl = RegistrationService.getReturnToUrl();
	}
*/


	public OpenIdRegistrationPage() {
		returnToUrl = RegistrationService.getReturnToUrl();
		//
		// If this is a new call, then use the OpenIdRegistrationForm, which
		/// only allows the user to enter their OpenID.
		//

 	}



             public AuthRequest onSubmit(String OpenId,  HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ConsumerException {
	        // Delegate to Open ID code
	        String userSuppliedIdentifier =  OpenId;
	        DiscoveryInformation discoveryInformation = RegistrationService.performDiscoveryOnUserSuppliedIdentifier(userSuppliedIdentifier);

	        // Store the disovery results in session.
                 HttpSession userSession = req.getSession();
                userSession.setAttribute("parameters",discoveryInformation);
	        //MakotoOpenIdAwareSession session = (MakotoOpenIdAwareSession)owningPage.getSession();
	        //session.setDiscoveryInformation(discoveryInformation, true);

	        // Create the AuthRequest obtain shared secret
	        AuthRequest authRequest = RegistrationService.createOpenIdAuthRequest(discoveryInformation, returnToUrl);

	        // Now take the AuthRequest and forward it on to the OP                                        
                 //resp.sendRedirect(authRequest.getDestinationUrl(true));                         
	        //getRequestCycle().setRedirect(false);
	        //getResponse().redirect();
               return authRequest;
	      }

}
