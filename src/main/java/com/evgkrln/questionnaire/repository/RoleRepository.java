package com.evgkrln.questionnaire.repository;

import java.util.List;

import com.evgkrln.questionnaire.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByIdu(Integer idu);
}
