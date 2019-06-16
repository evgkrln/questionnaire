package com.evgkrln.questionnaire.repository;

import com.evgkrln.questionnaire.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Integer> {
}

