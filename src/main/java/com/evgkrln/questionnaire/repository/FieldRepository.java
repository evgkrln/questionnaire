package com.evgkrln.questionnaire.repository;

import com.evgkrln.questionnaire.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Integer> {
    Field findByLabel(String label);
    Field findByIdf(Integer idf);
    Iterable<Field> findAllByOrderByIdfAsc();
}

