package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.util.BytesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 8583 TLV报文打包/解包器
 *
 * @author ybliu
 */
class IsoTLVMessageBuilder extends MessageBuilder {

    protected IsoTLVMessageBuilder() {
    }

    @Override
    protected boolean filter(FieldDefinition fieldDefinition) {
        return true;
    }

    @Override
    protected void postProcess(FieldDefinition fieldDefinition, Object instance, Object value) {
        // do nothing
    }

    @Override
    protected List<FieldDefinition> buildFieldDefinitions(byte[] bytes) {
        Map<Integer, IsoFieldDefinition> tagMap = super.buildFieldDefinitions(bytes).stream()
                .map(s -> (IsoFieldDefinition) s)
                .collect(Collectors.toMap(IsoFieldDefinition::getDomainNo, Function.identity()));
        return doBuildFieldDefinitions(bytes, tagMap);
    }

    private List<FieldDefinition> doBuildFieldDefinitions(byte[] bytes, Map<Integer, IsoFieldDefinition> tagMap) {
        List<Integer> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        while (counter.get() < bytes.length) {
            int tagLength = (bytes[counter.get()] & 0x1F) == 0x1F ? 2 : 1;
            byte[] tagBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(tagLength));
            int tag = BytesUtil.bytesToInt(tagBytes);
            list.add(tag);

            byte flagByte = bytes[counter.get()];
            byte[] lenBytes = (flagByte & 128) == 0 ?
                    Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(1)) :
                    Arrays.copyOfRange(bytes, counter.addAndGet(1), counter.addAndGet(flagByte & 127));

            int valueLength = BytesUtil.bytesToInt(lenBytes);
            counter.addAndGet(valueLength);
        }

        return list.stream().filter(tagMap::containsKey).map(tagMap::get).collect(Collectors.toList());
    }
}
