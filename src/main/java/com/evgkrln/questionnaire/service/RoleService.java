package com.evgkrln.questionnaire.service;

import com.evgkrln.questionnaire.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllByIdu(Integer idu);
    Role save(Role role);
}
