package com.ali.services.registration;

import com.ali.beans.user.UserBean;
import com.ali.constants.DatabaseConstants;
import com.ali.dataAccess.impl.DBManagerImpl;
import com.ali.dataAccess.interfaces.DataBaseManagerInterface;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class is responsible for validating user input for registration and Inserts the user details in the database.
 */

public class UserRegistrationService {

    static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationService.class);
    /**
     * Checks if the user details passed are valid.Currently checks if all the fields are entered and
     * if the passwords and repeat password match
     * @param userBean
     * @return if valid {@code True} else {@code false}
     */
    //TODO: Add more validations and may be strong server side validation.
    public boolean isValidUser(UserBean userBean){
        if(userBean != null){
            LOGGER.debug("Validating user");
            if(!(StringUtils.isBlank(userBean.getName()) || StringUtils.isBlank(userBean.getEmail()) ||
                StringUtils.isBlank(userBean.getPassword())||
                StringUtils.isBlank(userBean.getRepeatPassword()))){
                if(StringUtils.equals(userBean.getPassword(),userBean.getRepeatPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Connects to the Database and inserts the user record.
     * @param userBean
     * @return {code int} 0 on unsuccessfull insertion and {@code int}1 on successfull insertion
     */
    public int registerUser(UserBean userBean) {
        Optional<Connection> connection = Optional.empty();
        DataBaseManagerInterface dbInterface = new DBManagerImpl();
        PreparedStatement populateUserStatement = null;
        try{
            connection = dbInterface.getConnection();
            if(connection.isPresent()){
                LOGGER.debug("Registering user");
                Connection dbConnection = connection.get();
                populateUserStatement = dbConnection.prepareStatement(DatabaseConstants.INSERT_USER_SQL);
                populateUserStatement.setString(1,userBean.getName());
                //Stores the hash of the password rather than a plain text.
                populateUserStatement.setString(2,DigestUtils.sha256Hex(userBean.getPassword()));
                populateUserStatement.setString(3,userBean.getEmail());
                return populateUserStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("java.sql.SQLException while connecting to the database for registering user",e);
            return 0;
        } catch (NamingException e) {
            LOGGER.error("javax.naming.NamingException while connecting to the database for registering user",e);
            return 0;
        }finally {
            dbInterface.cleanUpResources(connection,Optional.empty(),Optional.ofNullable(populateUserStatement));
        }
        return 0;
    }
}
