package com.alatka.messages.admin.service;

import com.alatka.messages.admin.entity.MessageDefinition;
import com.alatka.messages.admin.repository.MessageDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageDefinitionService {

    private MessageDefinitionRepository messageDefinitionRepository;

    public Long create(MessageDefinition messageDefinition) {
        return messageDefinitionRepository.save(messageDefinition).getId();
    }

    public void update(MessageDefinition messageDefinition) {
        if (!messageDefinitionRepository.existsById(messageDefinition.getId())) {
            throw new IllegalArgumentException("Message definition with id " + messageDefinition.getId() + " does not exist");
        }
        messageDefinitionRepository.save(messageDefinition);
    }

    public void delete(Long id) {
        MessageDefinition entity = messageDefinitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id: " + id + " not found"));
        // TODO
        messageDefinitionRepository.delete(entity);
    }

    public Page<MessageDefinition> queryPage(MessageDefinition condition, Pageable pageable) {

        return messageDefinitionRepository.findAll(this.condition(condition), pageable);
    }

    private Specification<MessageDefinition> condition(MessageDefinition condition) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (condition.getType() != null) {
                list.add(criteriaBuilder.equal(root.get("type").as(String.class), condition.getType()));
            }
            if (condition.getGroup() != null) {
                list.add(criteriaBuilder.equal(root.get("group").as(String.class), condition.getGroup()));
            }
            if (condition.getCode() != null) {
                list.add(criteriaBuilder.equal(root.get("code").as(String.class), condition.getCode()));
            }
            if (condition.getKind() != null) {
                list.add(criteriaBuilder.equal(root.get("kind").as(String.class), condition.getKind()));
            }
/*
            if (condition.getDomain() != null) {
                list.add(criteriaBuilder.like(root.get("domain").as(String.class), "%" + condition.getDomain() + "%"));
            }
            if (condition.getUsage() != null) {
                list.add(criteriaBuilder.equal(root.get("usage").as(String.class), condition.getUsage()));
            }
*/
            if (condition.getRemark() != null) {
                list.add(criteriaBuilder.like(root.get("remark").as(String.class), "%" + condition.getRemark() + "%"));
            }
            if (condition.getEnabled() != null) {
                list.add((criteriaBuilder.equal(root.get("enabled").as(Boolean.class), condition.getEnabled())));
            }

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
    }

    @Autowired
    public void setMessageRepository(MessageDefinitionRepository messageDefinitionRepository) {
        this.messageDefinitionRepository = messageDefinitionRepository;
    }
}
