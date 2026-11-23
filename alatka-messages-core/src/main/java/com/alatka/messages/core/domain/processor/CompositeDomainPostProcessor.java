package com.alatka.messages.core.domain.processor;

import com.alatka.messages.core.context.FieldDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeDomainPostProcessor implements DomainPostProcessor {

    private List<DomainPostProcessor> processors = new ArrayList<>();

    public void add(DomainPostProcessor... processors) {
        this.processors.addAll(Arrays.stream(processors).collect(Collectors.toList()));
    }

    /**
     * 正序{@link #processors}处理
     */
    @Override
    public byte[] postProcessAfterPacked(byte[] bytes, FieldDefinition fieldDefinition) {
        byte[] result = bytes;
        for (DomainPostProcessor processor : processors) {
            result = processor.postProcessAfterPacked(result, fieldDefinition);
        }
        return result;
    }

    /**
     * 倒序{@link #processors}处理
     */
    @Override
    public byte[] postProcessAfterUnpacked(byte[] bytes, FieldDefinition fieldDefinition) {
        byte[] result = bytes;
        for (int i = processors.size() - 1; i >= 0; i--) {
            result = processors.get(i).postProcessAfterUnpacked(result, fieldDefinition);
        }
        return result;
    }

    @Override
    public String name() {
        return "composite";
    }
}
