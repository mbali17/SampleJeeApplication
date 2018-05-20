package com.ali.services.login;

import com.ali.beans.login.LoginBean;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class UserLoginService {
    /**
     * Used for server-side validation of the user input.
     * @param loginBean
     * @return {@code boolean} true if valid user input otherwise {@code boolean} false.
     */
    public boolean isValidUserInput(Optional<LoginBean> loginBean){
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
     * @param loginBean
     * @return
     */
    public boolean verifyUserLogin(Optional<LoginBean> loginBean){

    }
}
