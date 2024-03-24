package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.holder.UsageSubdomain;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.util.BytesUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class ULVSubdomainFieldBuilder extends SubdomainFieldBuilder<UsageSubdomain<Object>> {

    private static final int USAGE_ID_LENGTH = 2;
    private static final int USAGE_LEN_LENGTH = 3;

    @Override
    protected byte[] pack(UsageSubdomain<Object> value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        Map<String, Object> holder = value.getHolder();
        return holder.entrySet().stream()
                .map(entry -> {
                    byte[] usageBytes = entry.getKey().getBytes();
                    byte[] valueBytes = MessageBuilder.init(usageMap.get(entry.getKey())).pack(entry.getValue());
                    byte[] lenBytes = String.format("%0" + USAGE_LEN_LENGTH + "d", valueBytes.length).getBytes();
                    return BytesUtil.concat(usageBytes, lenBytes, valueBytes);
                }).reduce(new byte[0], BytesUtil::concat);
    }

    @Override
    protected UsageSubdomain<Object> unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        AtomicInteger counter = new AtomicInteger(0);
        while (counter.get() < bytes.length) {
            byte[] usageIdBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(USAGE_ID_LENGTH));
            byte[] usageLenBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(USAGE_LEN_LENGTH));
            byte[] usageValueBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(Integer.parseInt(new String(usageLenBytes))));

            String usageId = new String(usageIdBytes);

            super.validate(fieldDefinition, usageId);

            MessageDefinition definition = usageMap.get(usageId);
            Object value = MessageBuilder.init(definition).unpack(usageValueBytes);
            usageSubdomain.put(usageId, value);
        }
        return usageSubdomain;
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 2;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition) && definition.getSubdomainType() == MessageDefinition.DomainType.ULV;
    }
}
