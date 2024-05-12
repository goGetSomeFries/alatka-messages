package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.domain.DomainParsed;
import com.alatka.messages.domain.DomainParsedFactory;
import com.alatka.messages.field.FieldBuilder;
import com.alatka.messages.field.FieldBuilderFactory;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 8583 不定长子域报文打包/解包器
 *
 * @author ybliu
 */
public class UnfixedSubdomainMessageBuilder extends MessageBuilder {

    public UnfixedSubdomainMessageBuilder(MessageDefinition definition) {
        super.definition = definition;
    }

    private Boolean isNull = null;

    @Override
    protected <T> Wrapper doPack(T instance, MessageDefinition definition, FieldDefinition fieldDefinition) {
        // 对象解析为字节数组
        FieldBuilder fieldBuilder = FieldBuilderFactory.getInstance(instance, definition, fieldDefinition);
        byte[] bytes = fieldBuilder.serialize(instance, fieldDefinition);
        if (bytes.length == 0 && (isNull == null || isNull.booleanValue())) {
            isNull = Boolean.TRUE;
            return new Wrapper(bytes, fieldDefinition);
        }

        isNull = Boolean.FALSE;
        DomainParsed domainParsed = DomainParsedFactory.getInstance(instance, definition, fieldDefinition);
        byte[] packed = domainParsed.pack(bytes, fieldDefinition);
        return new Wrapper(packed, fieldDefinition);
    }

    @Override
    protected List<FieldDefinition> buildFieldDefinitions() {
        List<FieldDefinition> fieldDefinitions = super.buildFieldDefinitions().stream()
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return fieldDefinitions;
    }

    @Override
    protected byte[] concatBytes(byte[] bytes1, byte[] bytes2) {
        return super.concatBytes(bytes2, bytes1);
    }

    @Override
    protected <T> Wrapper doUnpack(T instance, MessageDefinition definition,
                                   FieldDefinition fieldDefinition, byte[] bytes, AtomicInteger counter) {
        return counter.get() >= bytes.length ? new Wrapper(null, fieldDefinition) :
                super.doUnpack(instance, definition, fieldDefinition, bytes, counter);
    }
}

