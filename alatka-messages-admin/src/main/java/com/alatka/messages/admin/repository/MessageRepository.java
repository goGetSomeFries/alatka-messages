package com.alatka.messages.admin.repository;

import com.alatka.messages.admin.entity.MessageDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageDefinition, Long>, JpaSpecificationExecutor<MessageDefinition> {
}
