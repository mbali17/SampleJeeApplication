package com.ali.servlets;

import com.ali.dataAccess.impl.DBManagerImpl;
import com.ali.dataAccess.interfaces.DataBaseManagerInterface;
import com.ali.dataAccess.pooling.ConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet to check the status of the application.
 */
@WebServlet("/getStatus")
public class ApplicationTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationTestServlet() {
        super();
        System.out.println("Application status invoked");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DataBaseManagerInterface dbInterface = new DBManagerImpl();
            Optional<Connection> connection =   dbInterface.getConnection();
            if(connection.isPresent()){
                response.getWriter().append("the details of connection are"+connection.get().toString());
            }else{
                response.getWriter().append("the details of connection are"+connection.get().toString());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().append("Application is running ");
	}

}
