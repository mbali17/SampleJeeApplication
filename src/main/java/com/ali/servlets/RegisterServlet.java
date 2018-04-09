package com.ali.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
		String message = "Thanks much " + userName + ", We will register you soon ";
		System.out.println(" Recieved request with value user Name: "+ userName +" and email"+ email);
		// Write the response back 
		PrintWriter out = response.getWriter();
		String responseToTheUser ="<html><body>"+message+"</body></html>";
		out.println(responseToTheUser);
	}

}
