package com.alatka.messages.admin.service;

import com.alatka.messages.admin.entity.FieldDefinition;
import com.alatka.messages.admin.entity.MessageDefinition;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubfieldService extends FieldService {

    public void create(MessageDefinition subMessage, List<FieldDefinition> subfields) {
        Long subMessageId = messageService.create(subMessage);

        subfields.forEach(subfield -> subfield.setMessageId(subMessageId));
        fieldRepository.saveAll(subfields);
    }

    public Map<MessageDefinition, List<FieldDefinition>> queryByFieldId(Long fieldId) {
        List<MessageDefinition> subMessages = messageService.queryByFieldId(fieldId);
        return subMessages.stream().collect(Collectors.toMap(Function.identity(),
                subMessage -> {
                    FieldDefinition condition = new FieldDefinition();
                    condition.setMessageId(subMessage.getId());
                    return fieldRepository.findAll(this.condition(condition), Sort.by("domainNo"));
                }
        ));
    }

}
