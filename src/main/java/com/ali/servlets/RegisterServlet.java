package com.ali.servlets;

import com.ali.beans.UserBean;
import com.ali.services.UserRegistrationService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Servlet used to handle user registration.
 */
@WebServlet(urlPatterns= "/register",
			description= "Servlet used to handle user registration.")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message",request.getParameter("name"));
		request.getRequestDispatcher("/WEB-INF/views/UserRegistration.jsp").forward(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Read user inputs
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String passWord = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		UserBean userBean = new UserBean(userName,email,passWord,repeatPassword);
		UserRegistrationService registrationService = new UserRegistrationService();
		System.out.println("is valid user {}"+registrationService.isValidUser(userBean) + ","+ userBean.getName().equals(""));
		if(registrationService.isValidUser(userBean)){
			registrationService.registerUser(userBean);
            request.setAttribute("welcomeMessage",userBean.getName());
            request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request,response);
		}else{
            request.setAttribute("errorMessage","Password and repeat password do not match");
            request.getRequestDispatcher("/WEB-INF/views/UserRegistration.jsp").forward(request,response);
        }
	}

}
