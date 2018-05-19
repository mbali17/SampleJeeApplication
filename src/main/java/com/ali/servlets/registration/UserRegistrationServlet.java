package com.ali.servlets.registration;

import com.ali.beans.user.UserBean;
import com.ali.constants.ApplicationConstants;
import com.ali.constants.JSPUrlConstants;
import com.ali.constants.ServletUrlConstants;
import com.ali.services.registration.UserRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *Servlet used to handle user registration.
 */
@WebServlet(urlPatterns= {ServletUrlConstants.REGISTRATIONSERVLET_URL},
			description= "Servlet used to handle user registration.")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationServlet.class);
	/**
	 * Redirects the initial request to the User Registration page.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Redirecting to registeration");
		request.getRequestDispatcher(JSPUrlConstants.REGISTRATION_JSP_URL).forward(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read user inputs
		String userName = request.getParameter(ApplicationConstants.USER_NAME);
		String email = request.getParameter(ApplicationConstants.EMAIL);
		String passWord = request.getParameter(ApplicationConstants.PASSWORD);
		String repeatPassword = request.getParameter(ApplicationConstants.REPEAT_PASSWORD);
		UserBean userBean = new UserBean(userName,email,passWord,repeatPassword);
		UserRegistrationService registrationService = new UserRegistrationService();
		LOGGER.debug("The user name is {}",userBean.getName());
		if(registrationService.isValidUser(userBean)){
            LOGGER.debug("The user name is {0} is valid user",userBean.getName());
			handleRedirection(registrationService.registerUser(userBean),request,response);
		}else{
            request.setAttribute("errorMessage","Password and repeat password do not match");
            request.getRequestDispatcher(JSPUrlConstants.REGISTRATION_JSP_URL).forward(request,response);
        }
	}

	/**
	 * Redirects to appropriate JSP based on successfull user registration.
	 * @param executeUpdateReturnValue
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void handleRedirection(int executeUpdateReturnValue, HttpServletRequest request,
								   HttpServletResponse response) throws ServletException, IOException {
		if(executeUpdateReturnValue == 1){
            LOGGER.debug("The user is sucessfully registered {}",executeUpdateReturnValue);
			request.getRequestDispatcher(JSPUrlConstants.LOGIN_JSP_URL).forward(request,response);
		}else{
			request.setAttribute("errorMessage","Unable to register, Please try again in some time");
			request.getRequestDispatcher(JSPUrlConstants.REGISTRATION_JSP_URL).forward(request,response);
		}
	}

}
