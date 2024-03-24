package com.alatka.messages.field;

import com.alatka.messages.holder.UsageSubdomain;
import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

import java.util.Arrays;
import java.util.Map;

/**
 * 子域类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class UVASSubdomainFieldBuilder extends SubdomainFieldBuilder<UsageSubdomain<Object>> {

    private static final int USAGE_ID_LENGTH = 2;
    private static final String USAGE_COMBINE_KEY = "AS";

    private final ULVSubdomainFieldBuilder fieldBuilder1 = new ULVSubdomainFieldBuilder();

    private final UVSubdomainFieldBuilder fieldBuilder2 = new UVSubdomainFieldBuilder();

    @Override
    protected byte[] pack(UsageSubdomain<Object> value, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        // 考虑到新增usage以AS组合方式拼接，不支持单个usage传输，所以删除单个usage传输模式
        /*
        Map<String, Object> holder = value.getHolder();
        if (holder.size() == 1) {
            return holder.entrySet().stream()
                    .map(entry -> {
                        byte[] usageBytes = entry.getKey().getBytes();
                        byte[] valueBytes = MessageBuilder.init(usageMap.get(entry.getKey())).pack(entry.getValue());
                        return BytesUtil.concat(usageBytes, valueBytes);
                    }).reduce(new byte[0], BytesUtil::concat);
        }
        */
        byte[] valueBytes = fieldBuilder1.pack(value, fieldDefinition, usageMap);
        return BytesUtil.concat(USAGE_COMBINE_KEY.getBytes(), valueBytes);
    }

    @Override
    protected UsageSubdomain<Object> unpack(byte[] bytes, FieldDefinition fieldDefinition, Map<String, MessageDefinition> usageMap) {
        byte[] usageIdBytes = Arrays.copyOfRange(bytes, 0, USAGE_ID_LENGTH);
        String usageId = new String(usageIdBytes);
        byte[] usageValueBytes = Arrays.copyOfRange(bytes, USAGE_ID_LENGTH, bytes.length);

        if (USAGE_COMBINE_KEY.equals(usageId)) {
            return fieldBuilder1.unpack(usageValueBytes, fieldDefinition, usageMap);
        } else {
            return fieldBuilder2.unpack(bytes, fieldDefinition, usageMap);
        }
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 6;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return super.matched(messageDefinition, definition)
                && definition.getSubdomainType() == MessageDefinition.DomainType.UVAS;
    }
}
