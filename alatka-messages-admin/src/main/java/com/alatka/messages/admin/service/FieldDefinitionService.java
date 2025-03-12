package com.alatka.messages.admin.service;

import com.alatka.messages.admin.entity.FieldDefinition;
import com.alatka.messages.admin.entity.MessageDefinition;
import com.alatka.messages.admin.repository.FieldDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FieldDefinitionService {

    private FieldDefinitionRepository fieldDefinitionRepository;

    public Long create(FieldDefinition fieldDefinition) {
        return fieldDefinitionRepository.save(fieldDefinition).getId();
    }

    public void update(MessageDefinition messageDefinition) {
    }

    public void delete(Long id) {
    }

    @Autowired
    public void setFieldDefinitionRepository(FieldDefinitionRepository fieldDefinitionRepository) {
        this.fieldDefinitionRepository = fieldDefinitionRepository;
    }
}
