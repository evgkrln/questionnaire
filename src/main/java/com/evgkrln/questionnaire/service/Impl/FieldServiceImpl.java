package com.evgkrln.questionnaire.service.Impl;

import com.evgkrln.questionnaire.model.Field;
import com.evgkrln.questionnaire.repository.FieldRepository;
import com.evgkrln.questionnaire.service.FieldService;
import com.evgkrln.questionnaire.util.exception.LabelExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl implements FieldService {
    private FieldRepository fieldRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field addNewField(Field field) throws LabelExistsException {
        if (this.labelExist(field.getLabel())) {
            throw new LabelExistsException("There is a field with label:" + field.getLabel());
        } else {
            field = (Field)this.fieldRepository.save(field);
            return field;
        }
    }

    public void saveField(Field field, String required, String active) {
        if (required != null) {
            field.setRequired(true);
        } else {
            field.setRequired(false);
        }

        if (active != null) {
            field.setActive(true);
        } else {
            field.setActive(false);
        }

        this.fieldRepository.save(field);
    }

    private boolean labelExist(String label) {
        Field user = this.fieldRepository.findByLabel(label);
        return user != null;
    }
}

