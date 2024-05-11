package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.holder.UsageSubdomain;
import com.alatka.messages.message.DefaultMessageBuilder;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.message.TLVSubdomainMessageBuilder;
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
public abstract class AbstractULVSubdomainFieldBuilder extends SubdomainFieldBuilder<UsageSubdomain<Object>> {

    @Override
    protected byte[] pack(UsageSubdomain<Object> value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        Map<String, Object> holder = value.getHolder();
        return holder.entrySet().stream()
                .map(entry -> {
                    byte[] usageBytes = this.usageId(entry.getKey());
                    byte[] valueBytes = super.validate(fieldDefinition, entry.getKey()) ?
                            this.generateMessageBuilder(usageMap.get(entry.getKey())).pack(entry.getValue()) :
                            (byte[]) entry.getValue();
                    byte[] lenBytes = this.usageLen(valueBytes.length);
                    return BytesUtil.concat(usageBytes, lenBytes, valueBytes);
                }).reduce(new byte[0], BytesUtil::concat);
    }

    @Override
    protected UsageSubdomain<Object> unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        AtomicInteger counter = new AtomicInteger(0);
        while (counter.get() < bytes.length) {
            byte[] usageIdBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(this.usageIdLength()));
            byte[] usageLenBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(this.usageLenLength()));
            byte[] usageValueBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(this.usageLen(usageLenBytes)));

            String usageId = this.usageId(usageIdBytes);

            Object value = null;
            if (super.validate(fieldDefinition, usageId)) {
                MessageDefinition definition = usageMap.get(usageId);
                value = this.generateMessageBuilder(definition).unpack(usageValueBytes);
            } else {
                value = usageValueBytes;
            }
            usageSubdomain.put(usageId, value);
        }
        return usageSubdomain;
    }

    private MessageBuilder generateMessageBuilder(MessageDefinition definition) {
        return definition.getDomainType() == MessageDefinition.DomainType.TLV ?
                new TLVSubdomainMessageBuilder(definition) :
                new DefaultMessageBuilder(definition);
    }

    protected abstract int usageIdLength();

    protected abstract int usageLenLength();

    protected abstract String usageId(byte[] bytes);

    protected abstract int usageLen(byte[] bytes);

    protected abstract byte[] usageId(String id);

    protected abstract byte[] usageLen(int length);

}
