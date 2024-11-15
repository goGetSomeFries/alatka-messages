package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.holder.UsageSubdomain;
import com.alatka.messages.core.message.*;
import com.alatka.messages.core.util.BytesUtil;

import java.util.Arrays;
import java.util.Map;

/**
 * UV(usage-value)子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class UVSubdomainFieldBuilder extends SubdomainFieldBuilder<UsageSubdomain<Object>> {

    private static final int USAGE_ID_LENGTH = 2;

    @Override
    protected byte[] pack(UsageSubdomain<Object> value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        Map<String, Object> holder = value.getHolder();
        return holder.entrySet().stream()
                .map(entry -> {
                    byte[] usageBytes = entry.getKey().getBytes();
                    byte[] valueBytes = super.validate(fieldDefinition, entry.getKey()) ?
                            this.generateMessageBuilder(usageMap.get(entry.getKey())).pack(entry.getValue()) :
                            (byte[]) entry.getValue();
                    return BytesUtil.concat(usageBytes, valueBytes);
                }).reduce(new byte[0], BytesUtil::concat);
    }

    @Override
    protected UsageSubdomain<Object> unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        byte[] usageIdBytes = Arrays.copyOfRange(bytes, 0, USAGE_ID_LENGTH);
        String usageId = new String(usageIdBytes);
        byte[] usageValueBytes = Arrays.copyOfRange(bytes, USAGE_ID_LENGTH, bytes.length);

        Object value = null;
        if (super.validate(fieldDefinition, usageId)) {
            MessageDefinition definition = usageMap.get(usageId);
            value = this.generateMessageBuilder(definition).unpack(usageValueBytes);
        } else {
            value = usageValueBytes;
        }
        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(usageId, value);
        return usageSubdomain;
    }

    private MessageBuilder generateMessageBuilder(MessageDefinition definition) {
        switch (definition.getDomainType()) {
            case TLV2:
                return new TLV2SubdomainMessageBuilder(definition);
            case TV:
                return new TVSubdomainMessageBuilder(definition);
            default:
                return new DefaultMessageBuilder(definition);
        }
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition) && definition.getSubdomainType() == MessageDefinition.DomainType.UV;
    }
}
