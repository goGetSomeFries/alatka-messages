package com.alatka.messages.admin.service;

import com.alatka.messages.admin.entity.FieldDefinition;
import com.alatka.messages.admin.entity.MessageDefinition;
import com.alatka.messages.admin.repository.FieldDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldDefinitionService {

    private MessageDefinitionService messageDefinitionService;

    private FieldDefinitionRepository fieldDefinitionRepository;

    public Long create(FieldDefinition fieldDefinition) {
        FieldDefinition entity = fieldDefinitionRepository.save(fieldDefinition);
        if (entity.getExistSubdomain()) {
            MessageDefinition message = messageDefinitionService.queryById(entity.getMessageId());

            MessageDefinition subMessage = new MessageDefinition();
            subMessage.setType(message.getType());
            subMessage.setGroup(message.getGroup());
            subMessage.setCode(message.getCode());
            subMessage.setKind("subPayload");
            subMessage.setDomain(null);
            subMessage.setUsage(null);
            subMessage.setDomainType(null);
            subMessage.setHolder(null);
            subMessage.setCharset(message.getCharset());
            subMessage.setRemark(null);
            subMessage.setEnabled(true);
            messageDefinitionService.create(subMessage);
        }
        return entity.getId();
    }

    public void update(FieldDefinition fieldDefinition) {
        if (!fieldDefinitionRepository.existsById(fieldDefinition.getId())) {
            throw new IllegalArgumentException("FieldDefinition with id " + fieldDefinition.getId() + " does not exist");
        }
        fieldDefinitionRepository.save(fieldDefinition);
    }

    public void deleteByMessageId(Long messageId) {
        FieldDefinition condition = new FieldDefinition();
        condition.setMessageId(messageId);
        List<Long> ids = fieldDefinitionRepository.findAll(this.condition(condition))
                .stream().map(FieldDefinition::getId).collect(Collectors.toList());
        this.delete(ids);
    }

    public void delete(List<Long> ids) {
        ids.forEach(messageDefinitionService::deleteByFieldId);
        fieldDefinitionRepository.deleteAllById(ids);
    }

    public List<FieldDefinition> queryByMessageId(Long messageId) {
        FieldDefinition condition = new FieldDefinition();
        condition.setMessageId(messageId);
        return fieldDefinitionRepository.findAll(this.condition(condition), Sort.by("domainNo"));
    }

    public Page<FieldDefinition> queryPage(FieldDefinition condition, Pageable pageable) {
        return fieldDefinitionRepository.findAll(this.condition(condition), pageable);
    }

    private Specification<FieldDefinition> condition(FieldDefinition condition) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (condition.getMessageId() != null) {
                list.add(criteriaBuilder.equal(root.get("messageId").as(Long.class), condition.getMessageId()));
            }

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
    }

    @Autowired
    public void setFieldDefinitionRepository(FieldDefinitionRepository fieldDefinitionRepository) {
        this.fieldDefinitionRepository = fieldDefinitionRepository;
    }

    @Autowired
    public void setMessageDefinitionService(MessageDefinitionService messageDefinitionService) {
        this.messageDefinitionService = messageDefinitionService;
    }
}
