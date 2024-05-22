package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.message.DefaultMessageBuilder;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.util.BytesUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 分页类型报文域解析器
 *
 * @author ybliu
 * @see SubdomainFieldBuilder
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class PageSubdomainFieldBuilder extends SubdomainFieldBuilder<List<?>> {

    @Override
    protected List<?> unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        MessageBuilder messageBuilder = new DefaultMessageBuilder(definition);
        AtomicInteger counter = new AtomicInteger(0);

        return IntStream.range(0, bytes.length / fieldDefinition.getLength())
                .mapToObj(i -> {
                    byte[] elementBytes =
                            Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(fieldDefinition.getLength()));
                    return messageBuilder.unpack(elementBytes);
                })
                .collect(Collectors.toList());
    }

    @Override
    protected byte[] pack(List<?> value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        MessageDefinition definition = usageMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT);
        MessageBuilder messageBuilder = new DefaultMessageBuilder(definition);
        return value.stream()
                .map(instance -> messageBuilder.pack(instance))
                .reduce(new byte[0], BytesUtil::concat);
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition)
                && definition.getSubdomainType() == MessageDefinition.DomainType.PAGE;
    }
}
