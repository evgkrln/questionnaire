package com.evgkrln.questionnaire.service.Impl;

import java.util.List;

import com.evgkrln.questionnaire.model.Role;
import com.evgkrln.questionnaire.repository.RoleRepository;
import com.evgkrln.questionnaire.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");
    public static final Role ROLE_USER = new Role("ROLE_USER");

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllByIdu(Integer idu) {
        return this.roleRepository.findAllByIdu(idu);
    }

    public Role save(Role role) {
        return (Role)this.roleRepository.save(role);
    }
}

