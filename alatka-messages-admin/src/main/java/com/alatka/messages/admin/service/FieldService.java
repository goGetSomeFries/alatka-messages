package com.alatka.messages.admin.service;

import com.alatka.messages.admin.entity.FieldDefinition;
import com.alatka.messages.admin.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldService {

    protected MessageService messageService;

    protected FieldRepository fieldRepository;

    public Long create(FieldDefinition fieldDefinition) {
        return fieldRepository.save(fieldDefinition).getId();
    }

    public void update(FieldDefinition fieldDefinition) {
        FieldDefinition entity = fieldRepository.findById(fieldDefinition.getId())
                .orElseThrow(() -> new IllegalArgumentException("FieldDefinition with id " + fieldDefinition.getId() + " does not exist"));
        if (entity.getSubdomainType() != null && fieldDefinition.getSubdomainType() != null
                && !entity.getSubdomainType().equals(fieldDefinition.getSubdomainType())) {
            throw new IllegalArgumentException("子域类型不可修改，可删除后新增");
        }
        fieldRepository.save(fieldDefinition);
    }

    public void deleteByMessageId(Long messageId) {
        FieldDefinition condition = new FieldDefinition();
        condition.setMessageId(messageId);
        List<Long> ids = fieldRepository.findAll(this.condition(condition))
                .stream().map(FieldDefinition::getId).collect(Collectors.toList());
        this.delete(ids);
    }

    public void delete(List<Long> ids) {
        ids.forEach(messageService::deleteByFieldId);
        fieldRepository.deleteAllById(ids);
    }

    public Page<FieldDefinition> queryPage(FieldDefinition condition, Pageable pageable) {
        return fieldRepository.findAll(this.condition(condition), pageable);
    }

    protected Specification<FieldDefinition> condition(FieldDefinition condition) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (condition.getMessageId() != null) {
                list.add(criteriaBuilder.equal(root.get("messageId").as(Long.class), condition.getMessageId()));
            }

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
    }

    @Autowired
    public void setFieldRepository(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
