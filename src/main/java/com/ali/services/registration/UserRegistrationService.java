package com.ali.services.registration;

import com.ali.beans.user.UserBean;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for validating user input for registration and populates the user map.
 */

public class UserRegistrationService {

    private Map<String,UserBean> RegisteredUsers = new HashMap<String, UserBean>();
    /**
     * Checks if the user details passed are valid.Currently checks if all the fields are entered and
     * if the passwords and repeat password match
     * @param userBean
     * @return if valid {@code True} else {@code false}
     */
    //TODO: Add more validations and may be strong server side validation.
    public boolean isValidUser(UserBean userBean){
        if(userBean != null){
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


    public void registerUser(UserBean userBean) {
        RegisteredUsers.put(userBean.getName(),userBean);
    }
}
