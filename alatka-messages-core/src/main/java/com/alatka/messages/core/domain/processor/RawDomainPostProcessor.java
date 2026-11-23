package com.alatka.messages.core.domain.processor;

import com.alatka.messages.core.context.FieldDefinition;

public class RawDomainPostProcessor implements DomainPostProcessor {

    @Override
    public byte[] postProcessAfterPacked(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public byte[] postProcessAfterUnpacked(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public String name() {
        return "raw";
    }
}
