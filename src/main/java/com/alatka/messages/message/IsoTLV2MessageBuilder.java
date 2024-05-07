package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 8583 TLV2报文打包/解包器
 *
 * @author ybliu
 */
public class IsoTLV2MessageBuilder extends MessageBuilder {

    private static final int TAG_LENGTH = 3;
    private static final int LEN_LENGTH = 3;

    public IsoTLV2MessageBuilder(MessageDefinition definition) {
        super.definition = definition;
    }

    @Override
    protected List<FieldDefinition> buildFieldDefinitions(byte[] bytes) {
        Map<String, IsoFieldDefinition> tagMap = super.buildFieldDefinitions(bytes).stream()
                .map(s -> (IsoFieldDefinition) s)
                .collect(Collectors.toMap(IsoFieldDefinition::getAliasName, Function.identity()));
        return doBuildFieldDefinitions(bytes, tagMap);
    }

    private List<FieldDefinition> doBuildFieldDefinitions(byte[] bytes, Map<String, IsoFieldDefinition> tagMap) {
        List<String> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        while (counter.get() < bytes.length) {
            byte[] tagBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(TAG_LENGTH));
            list.add(new String(tagBytes));

            byte[] lenBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(LEN_LENGTH));

            int valueLength = Integer.parseInt(new String(lenBytes));
            counter.addAndGet(valueLength);
        }

        return list.stream().filter(tagMap::containsKey).map(tagMap::get).collect(Collectors.toList());
    }
}
