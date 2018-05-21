package com.ali.servlets.login;

import com.ali.beans.login.LoginBean;
import com.ali.constants.ApplicationConstants;
import com.ali.constants.JSPUrlConstants;
import com.ali.constants.ServletUrlConstants;
import com.ali.services.login.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * This servlet handles the user login.
 *@author Ali
 */
@WebServlet(name = "LoginServlet",urlPatterns = ServletUrlConstants.LOGINSERVLET_URL)
public class LoginServlet extends HttpServlet {

    static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("Serving login page");
        request.getRequestDispatcher(JSPUrlConstants.LOGIN_JSP_URL).forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("Handling user login");
        UserLoginService userLoginService = new UserLoginService();
        LoginBean loginBean = new LoginBean(request.getParameter(ApplicationConstants.USER_NAME),
                                    request.getParameter(ApplicationConstants.PASSWORD));
        LOGGER.info("User name is {}",loginBean.getUserName());
        if(userLoginService.handleUserLogin(Optional.of(loginBean))) {
            LOGGER.debug("User successfully logged in ");
            //TODO: Make the following line response.sendredirect.
            request.getRequestDispatcher(JSPUrlConstants.TODO_LISTING_JSP_URL).forward(request, response);
        }else{
            request.setAttribute("errormessage","invalid UserName or Password");
            request.getRequestDispatcher(JSPUrlConstants.LOGIN_JSP_URL).forward(request, response);
        }
    }

}
