package com.alatka.messages.core.domain.processor;

import com.alatka.messages.core.context.FieldDefinition;

public interface DomainPostProcessor {

    byte[] postProcessAfterPacked(byte[] bytes, FieldDefinition fieldDefinition);

    byte[] postProcessAfterUnpacked(byte[] bytes, FieldDefinition fieldDefinition);

    String name();
}
