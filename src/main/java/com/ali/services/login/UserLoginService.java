package com.ali.services.login;

import com.ali.beans.login.LoginBean;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserLoginService {
    /**
     * Handles user login by validating user input and confirming with the database
     * @param loginBean
     * @return
     */
    private DataBaseManagerInterface dataBaseManagerInterface = new DBManagerImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginService.class);
    public boolean handleUserLogin(Optional<LoginBean> loginBean){
        if(isValidUserInput(loginBean)){
            LOGGER.debug("User input is valid");
            return verifyUserLogin(loginBean);
        }
        return false;
    }

    /**
     * Used for server-side validation of the user input.
     * @param loginBean Bean consisting of the user inputs.
     * @return {@code boolean} true if valid user input otherwise {@code boolean} false.
     */
    private boolean isValidUserInput(Optional<LoginBean> loginBean){
        boolean isValidUser = false;
        if(loginBean.isPresent()){
            LoginBean passedLoginBean = loginBean.get();
            if(StringUtils.isNotBlank(passedLoginBean.getPassWord()) &&
                    StringUtils.isNotBlank(passedLoginBean.getUserName())) isValidUser = true;
        }
        return isValidUser;
    }

    /**
     * This connects to the database to verify user credentials.
     * @param optionalLoginBean Bean consisting of the user inputs
     * @return {@code boolean} true if the user details passed is same as the Database other {@code boolean} false.
     */
    private boolean verifyUserLogin(Optional<LoginBean> optionalLoginBean)  {
            PreparedStatement selectUsePreparedStatement = null;
            ResultSet selectUserResultSet = null;
            Optional<Connection> connectionOptional = Optional.empty();
            try{
                connectionOptional = dataBaseManagerInterface.getConnection();
                if(connectionOptional.isPresent()){
                    LoginBean loginBean = optionalLoginBean.get();
                    selectUsePreparedStatement = connectionOptional.get().prepareStatement(
                                                            DatabaseConstants.SELECT_USER_SQL);
                    selectUsePreparedStatement.setString(1,loginBean.getUserName());
                    selectUsePreparedStatement.setString(2,
                                                           DigestUtils.sha256Hex(loginBean.getPassWord()));
                    selectUserResultSet = selectUsePreparedStatement.executeQuery();
                    //The ResultSet returned from the Database will always consists of only one result.
                    if (selectUserResultSet.next()) {
                        LOGGER.debug("User details found");
                        return true;
                    }
                }
            } catch (SQLException e) {
                LOGGER.error("java.sql.SQLException while checking user login",e);
                return false;
            } catch (NamingException e) {
                LOGGER.error("javax.naming.NamingException while checking user login",e);
                return false;
            }finally {
                dataBaseManagerInterface.cleanUpResources(connectionOptional,Optional.of(selectUserResultSet),
                        Optional.of(selectUsePreparedStatement));
            }
        return false;
    }
}
