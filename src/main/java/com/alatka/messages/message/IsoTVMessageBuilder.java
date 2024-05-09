package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 8583 TV报文打包/解包器
 *
 * @author ybliu
 */
public class IsoTVMessageBuilder extends MessageBuilder {

    private static final int TAG_LENGTH = 3;

    public IsoTVMessageBuilder(MessageDefinition definition) {
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
            String tag = new String(tagBytes);
            list.add(tag);

            IsoFieldDefinition fieldDefinition = tagMap.get(tag);
            counter.addAndGet(fieldDefinition.getLength());
        }

        return list.stream().filter(tagMap::containsKey).map(tagMap::get).collect(Collectors.toList());
    }
}
