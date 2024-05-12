package com.alatka.messages.message;

import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 8583 TLV2报文打包/解包器
 *
 * @author ybliu
 */
public class TLV2SubdomainMessageBuilder extends RebuildSubdomainMessageBuilder {

    private static final int TAG_LENGTH = 3;
    private static final int LEN_LENGTH = 3;

    public TLV2SubdomainMessageBuilder(MessageDefinition definition) {
        super.definition = definition;
    }

    @Override
    protected List<String> doBuildFieldDefinitions(byte[] bytes, Map<String, IsoFieldDefinition> tagMap) {
        List<String> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        while (counter.get() < bytes.length) {
            byte[] tagBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(TAG_LENGTH));
            list.add(new String(tagBytes));

            byte[] lenBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(LEN_LENGTH));

            int valueLength = Integer.parseInt(new String(lenBytes));
            counter.addAndGet(valueLength);
        }
        return list;
    }
}
