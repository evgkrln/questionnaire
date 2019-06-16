package com.evgkrln.questionnaire.service;

import com.evgkrln.questionnaire.model.Field;
import com.evgkrln.questionnaire.util.exception.LabelExistsException;

public interface FieldService {
    Field addNewField(Field field) throws LabelExistsException;
    void saveField(Field field, String required, String active);
}
