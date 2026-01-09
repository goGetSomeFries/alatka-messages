package com.alatka.messages.core.message;

import com.alatka.messages.core.context.IsoFieldDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public abstract class FixedTLVSubdomainMessageBuilder extends RebuildSubdomainMessageBuilder {

    @Override
    protected List<String> doBuildFieldDefinitions(byte[] bytes, Map<String, IsoFieldDefinition> tagMap) {
        List<String> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        while (counter.get() < bytes.length) {
            byte[] tagBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(tagLength()));
            String tag = buildTag(tagBytes);
            list.add(tag);

            byte[] lenBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(lenLength()));

            int valueLength = buildLen(lenBytes);
            counter.addAndGet(valueLength);
        }
        return list;
    }

    protected abstract int tagLength();

    protected abstract int lenLength();

    protected abstract String buildTag(byte[] tagBytes);

    protected abstract int buildLen(byte[] lenBytes);
}
