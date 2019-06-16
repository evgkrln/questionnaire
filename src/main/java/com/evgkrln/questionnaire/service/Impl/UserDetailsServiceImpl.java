package com.evgkrln.questionnaire.service.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;

import com.evgkrln.questionnaire.model.Role;
import com.evgkrln.questionnaire.model.User;
import com.evgkrln.questionnaire.repository.UserRepository;
import com.evgkrln.questionnaire.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        } else {
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList();
            List<Role> roles = this.roleService.getAllByIdu(user.getIdu());
            Iterator var5 = roles.iterator();

            while(var5.hasNext()) {
                Role i = (Role)var5.next();
                Objects.requireNonNull(i);
                GrantedAuthority grantedAuthority = i::getName;
                grantedAuthorities.add(grantedAuthority);
            }

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
        }
    }
}

