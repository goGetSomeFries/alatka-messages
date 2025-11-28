package com.alatka.messages.admin.service;

import com.alatka.messages.admin.AutoConfiguration;
import com.alatka.messages.admin.entity.MessageDefinition;
import com.alatka.messages.admin.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {

    private RestTemplate restTemplate;

    private TaskExecutor taskExecutor;

    private FieldService fieldService;

    private MessageRepository messageRepository;

    public Map<String, String> build(List<String> uris, String path) {
        Map<String, String> resultMap = new HashMap<>(uris.size());

        CompletableFuture<Void> completableFuture = uris.stream()
                .map(uri -> uri.startsWith("http://") || uri.startsWith("https://") ? uri : "http://" + uri)
                .map(uri -> uri.concat(path))
                .map(url -> this.doBuild(url, resultMap))
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        list -> CompletableFuture.allOf(list.toArray(new CompletableFuture[0]))));
        completableFuture.join();
        return resultMap;
    }

    private CompletableFuture<Void> doBuild(String url, Map<String, String> resultMap) {
        return CompletableFuture.supplyAsync(() -> restTemplate.postForObject(url, null, String.class), taskExecutor)
                .handleAsync((result, ex) -> "ok".equalsIgnoreCase(result) ? "success" : "fail")
                .thenAccept(result -> resultMap.put(url, result));
    }

    public Long create(MessageDefinition messageDefinition) {
        return messageRepository.save(messageDefinition).getId();
    }

    public void update(MessageDefinition messageDefinition) {
        if (!messageRepository.existsById(messageDefinition.getId())) {
            throw new IllegalArgumentException("MessageDefinition with id " + messageDefinition.getId() + " does not exist");
        }
        messageRepository.save(messageDefinition);
    }

    public void deleteByFieldId(Long fieldId) {
        MessageDefinition condition = new MessageDefinition();
        condition.setFieldId(fieldId);
        List<Long> ids = messageRepository.findAll(this.condition(condition))
                .stream().map(MessageDefinition::getId).collect(Collectors.toList());
        this.delete(ids);
    }

    public void delete(List<Long> ids) {
        ids.forEach(fieldService::deleteByMessageId);
        messageRepository.deleteAllById(ids);
    }

    public MessageDefinition queryById(Long id) {
        MessageDefinition condition = new MessageDefinition();
        condition.setId(id);
        condition.setEnabled(true);
        return messageRepository.findOne(this.condition(condition))
                .orElseThrow(() -> new IllegalArgumentException("id " + id + " does not exist"));
    }

    public List<MessageDefinition> queryByFieldId(Long fieldId) {
        MessageDefinition condition = new MessageDefinition();
        condition.setFieldId(fieldId);
        condition.setEnabled(true);
        return messageRepository.findAll(this.condition(condition));
    }

    public Page<MessageDefinition> queryPage(MessageDefinition condition, Pageable pageable) {
        Specification<MessageDefinition> specification =
                this.condition(condition).and((root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get("kind").as(String.class), "subPayload"));
        return messageRepository.findAll(specification, pageable);
    }

    private Specification<MessageDefinition> condition(MessageDefinition condition) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (condition.getId() != null) {
                list.add(criteriaBuilder.equal(root.get("id").as(Long.class), condition.getId()));
            }
            if (condition.getType() != null) {
                list.add(criteriaBuilder.equal(root.get("type").as(String.class), condition.getType()));
            }
            if (condition.getGroup() != null) {
                list.add(criteriaBuilder.equal(root.get("group").as(String.class), condition.getGroup()));
            }
            if (condition.getCode() != null) {
                list.add(criteriaBuilder.like(root.get("code").as(String.class), condition.getCode() + "%"));
            }
            if (condition.getKind() != null) {
                list.add(criteriaBuilder.equal(root.get("kind").as(String.class), condition.getKind()));
            }
            if (condition.getFieldId() != null) {
                list.add(criteriaBuilder.equal(root.get("fieldId").as(Long.class), condition.getFieldId()));
            }
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
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Lazy
    @Autowired
    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @Autowired
    @Qualifier(AutoConfiguration.REST_TEMPLATE_NAME)
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    @Qualifier(AutoConfiguration.TASK_EXECUTOR_NAME)
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
