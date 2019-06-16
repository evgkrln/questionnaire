package com.evgkrln.questionnaire.service;

import com.evgkrln.questionnaire.model.User;
import com.evgkrln.questionnaire.util.exception.EmailExistsException;

public interface UserService {
    boolean registerNewUserAccount(User user, boolean admin) throws EmailExistsException;
    void EditUserProfile(User user, User edituser);
    void ChangeUserPass(User edituser, String newpass);
    boolean activateUser(String code);
}
