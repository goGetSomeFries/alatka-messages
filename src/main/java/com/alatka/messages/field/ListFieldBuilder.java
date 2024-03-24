package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.util.BytesUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * List类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class ListFieldBuilder extends SubdomainFieldBuilder<List<?>> {

    @Override
    protected List<?> unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        super.validate(fieldDefinition, FieldDefinition.SUBFIELD_KEY_DEFAULT);

        AtomicInteger counter = new AtomicInteger(0);
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return IntStream.range(0, bytes.length / fieldDefinition.getLength())
                .mapToObj(i -> {
                    byte[] elementBytes =
                            Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(fieldDefinition.getLength()));
                    return MessageBuilder.init(definition).unpack(elementBytes);
                }).collect(Collectors.toList());
    }

    @Override
    protected byte[] pack(List<?> value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        return value.stream()
                .map(instance -> MessageBuilder.init(definition).pack(instance))
                .reduce(new byte[0], BytesUtil::concat);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 5;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition) && definition.getSubdomainType() == MessageDefinition.DomainType.LIST;
    }
}
