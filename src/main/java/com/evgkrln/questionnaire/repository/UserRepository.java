package com.evgkrln.questionnaire.repository;


import com.evgkrln.questionnaire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByIdu(Integer idu);
    User findByActivationCode(String code);
}