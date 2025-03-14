package com.alatka.messages.admin.repository;

import com.alatka.messages.admin.entity.FieldDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<FieldDefinition, Long>, JpaSpecificationExecutor<FieldDefinition> {
}
