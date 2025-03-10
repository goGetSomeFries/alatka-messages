package com.alatka.messages.admin.service;

import com.alatka.messages.admin.entity.MessageDefinition;
import com.alatka.messages.admin.repository.MessageDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageDefinitionService {

    private MessageDefinitionRepository messageDefinitionRepository;

    public Long create(MessageDefinition messageDefinition) {
        return messageDefinitionRepository.save(messageDefinition).getId();
    }

    @Autowired
    public void setMessageRepository(MessageDefinitionRepository messageDefinitionRepository) {
        this.messageDefinitionRepository = messageDefinitionRepository;
    }
}
