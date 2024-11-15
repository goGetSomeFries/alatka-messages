package com.alatka.messages.core.message;

import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 8583 TV报文打包/解包器
 *
 * @author ybliu
 */
public class TVSubdomainMessageBuilder extends RebuildSubdomainMessageBuilder {

    private static final int TAG_LENGTH = 3;

    public TVSubdomainMessageBuilder(MessageDefinition definition) {
        super.definition = definition;
    }

    @Override
    protected List<String> doBuildFieldDefinitions(byte[] bytes, Map<String, IsoFieldDefinition> tagMap) {
        List<String> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        while (counter.get() < bytes.length) {
            byte[] tagBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(TAG_LENGTH));
            String tag = new String(tagBytes);
            list.add(tag);

            IsoFieldDefinition fieldDefinition = tagMap.get(tag);
            counter.addAndGet(fieldDefinition.getLength());
        }
        return list;
    }
}
