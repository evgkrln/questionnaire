package com.evgkrln.questionnaire.service.Impl;

import java.util.UUID;
import javax.transaction.Transactional;

import com.evgkrln.questionnaire.model.Role;
import com.evgkrln.questionnaire.model.User;
import com.evgkrln.questionnaire.repository.UserRepository;
import com.evgkrln.questionnaire.service.RoleService;
import com.evgkrln.questionnaire.service.UserService;
import com.evgkrln.questionnaire.util.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private MailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, MailSender mailSender) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Transactional
    public boolean registerNewUserAccount(User user, boolean admin) throws EmailExistsException {
        if (this.emailExist(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address:" + user.getEmail());
        } else {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            user.setActivationCode(UUID.randomUUID().toString());
            this.userRepository.save(user);
            Role role = new Role();
            if (admin) {
                role.setName("ROLE_ADMIN");
                role.setIdu(user.getIdu());
                this.roleService.save(role);
            } else {
                role.setName("ROLE_USER");
                role.setIdu(user.getIdu());
                this.roleService.save(role);
            }

            String message = String.format("Hello, %s! \nWelcome to Questionnaire. Please, visit next link: https://questionnaireproject.herokuapp.com/activate/%s", user.getFirstname(), user.getActivationCode());
            this.mailSender.send(user.getEmail(), "Activation code", message);
            return true;
        }
    }

    private boolean emailExist(String email) {
        User user = this.userRepository.findByEmail(email);
        return user != null;
    }

    @Transactional
    public boolean activateUser(String code) {
        User user = this.userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        } else {
            user.setActivationCode((String)null);
            user.setEnabled(true);
            this.userRepository.save(user);
            return true;
        }
    }

    @Transactional
    public void EditUserProfile(User user, User edituser) {
        edituser.setEmail(user.getEmail());
        edituser.setFirstname(user.getFirstname());
        edituser.setLastname(user.getLastname());
        edituser.setPhone(user.getPhone());
        this.userRepository.save(edituser);
    }

    @Transactional
    public void ChangeUserPass(User edituser, String newpass) {
        edituser.setPassword(this.passwordEncoder.encode(newpass));
        edituser.setPasswordConfirm(this.passwordEncoder.encode(newpass));
        edituser.setActivationCode(UUID.randomUUID().toString());
        edituser.setEnabled(false);
        this.userRepository.save(edituser);
        String message = String.format("Hello, %s! \nWelcome to Questionnaire. Please, visit next link: https://questionnaireproject.herokuapp.com/activate/%s", edituser.getFirstname(), edituser.getActivationCode());
        this.mailSender.send(edituser.getEmail(), "Activation code", message);
    }
}
