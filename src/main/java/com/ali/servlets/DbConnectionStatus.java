package com.ali.servlets;

import com.ali.constants.ServletUrlConstants;
import com.ali.dataAccess.impl.DBManagerImpl;
import com.ali.dataAccess.interfaces.DataBaseManagerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * Servlet to check the status of the application.
 */
@WebServlet(ServletUrlConstants.DBCONNECTIONSERVLET_URL)
public class DbConnectionStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = LoggerFactory.getLogger(DbConnectionStatus.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbConnectionStatus() {
        super();
        LOGGER.info("Application status invoked");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Connection> connection = Optional.empty();
        DataBaseManagerInterface dbInterface = new DBManagerImpl();
	    try {
            LOGGER.info("requesting connection");
            dbInterface = new DBManagerImpl();
            connection =   dbInterface.getConnection();
            if(connection.isPresent()){
                LOGGER.info("Obtained connection");
                response.getWriter().append("the details of connection are"+connection.get().toString());
            }else{
                response.getWriter().append("the details of connection are"+connection.get().toString());
            }
        } catch (NamingException e) {
            LOGGER.error("javax.naming.NamingException while requesting connection",e);
        } catch (SQLException e) {
            LOGGER.error("java.sql.SQLException while requesting connection",e);
        }finally {
	        dbInterface.closeConnection(connection);
        }
        response.getWriter().append("\t Application is running ");
	}

}
